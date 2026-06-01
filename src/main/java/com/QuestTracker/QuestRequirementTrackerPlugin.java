package com.QuestTracker;

import com.QuestTracker.data.QuestRequirementDatabase;
import com.QuestTracker.model.QuestRequirement;
import com.QuestTracker.model.QuestSkillRequirement;
import com.google.inject.Provides;
import javax.inject.Inject;
import javax.swing.SwingUtilities;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Quest;
import net.runelite.api.QuestState;
import net.runelite.api.Skill;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.StatChanged;
import net.runelite.client.Notifier;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

@Slf4j
@PluginDescriptor(
		name = "Quest Requirement Tracker"
)
public class QuestRequirementTrackerPlugin extends Plugin
{
	@Inject
	private ConfigManager configManager;

	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private ClientToolbar clientToolbar;

	@Inject
	private QuestRequirementTrackerPluginConfig config;

	@Inject
	private Notifier notifier;

	private NavigationButton navButton;
	private QuestRequirementTrackerPanel panel;

	private final Map<Skill, Integer> previousLevels = new EnumMap<>(Skill.class);
	private final Set<String> previouslyEligible = new HashSet<>();

	@Override
	protected void startUp()
	{
		panel = new QuestRequirementTrackerPanel(client, clientThread, config, configManager);

		BufferedImage icon;
		try {
			icon = ImageUtil.loadImageResource(getClass(), "/skill_icons/overall.png");
			if (icon == null) throw new Exception("null image");
		} catch (Exception e) {
			log.warn("Quest Requirement Tracker: icon load failed, using fallback");
			icon = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		}

		navButton = NavigationButton.builder()
				.tooltip("Quest Requirement Tracker")
				.icon(icon)
				.priority(5)
				.panel(panel)
				.build();

		clientToolbar.addNavigation(navButton);
		log.info("Quest Requirement Tracker started!");
	}

	@Override
	protected void shutDown()
	{
		clientToolbar.removeNavigation(navButton);
		panel = null;
		navButton = null;
		previousLevels.clear();
		previouslyEligible.clear();
		log.info("Quest Requirement Tracker stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			clientThread.invokeLater(() ->
			{
				previousLevels.clear();
				for (Skill s : Skill.values())
				{
					if (s != Skill.OVERALL)
						previousLevels.put(s, client.getRealSkillLevel(s));
				}
				if (config.notifyOnLogin())
					checkLoginEligibility();
			});
			SwingUtilities.invokeLater(() -> panel.populate());
		}
		else if (gameStateChanged.getGameState() == GameState.LOGIN_SCREEN
				|| gameStateChanged.getGameState() == GameState.HOPPING)
		{
			previouslyEligible.clear();
		}
	}

	@Subscribe
	public void onStatChanged(StatChanged statChanged)
	{
		Skill skill = statChanged.getSkill();
		if (skill == Skill.OVERALL) return;

		int newLevel = statChanged.getLevel();
		int oldLevel = previousLevels.getOrDefault(skill, 0);
		if (newLevel <= oldLevel) return;

		previousLevels.put(skill, newLevel);
		SwingUtilities.invokeLater(() -> panel.populate());
		clientThread.invokeLater(() -> checkNotifications(skill, newLevel));
	}

	private void checkLoginEligibility()
	{
		Set<String> completed = new HashSet<>();
		for (Quest q : Quest.values())
		{
			if (q.getState(client) == QuestState.FINISHED)
				completed.add(q.getName());
		}

		Map<Skill, Integer> levels = new EnumMap<>(Skill.class);
		for (Skill s : Skill.values())
		{
			if (s != Skill.OVERALL)
				levels.put(s, client.getRealSkillLevel(s));
		}

		List<String> newlyEligible = new ArrayList<>();
		for (QuestRequirement quest : QuestRequirementDatabase.getQuests())
		{
			String name = quest.getQuestName();
			if (completed.contains(name)) continue;

			boolean skillsMet = quest.getRequirements().stream()
					.allMatch(r -> levels.getOrDefault(r.getSkill(), 1) >= r.getRequiredLevel());
			if (!skillsMet) continue;

			boolean prereqsMet = quest.getQuestPrerequisites().stream()
					.allMatch(completed::contains);
			if (!prereqsMet) continue;

			if (!previouslyEligible.contains(name))
				newlyEligible.add(name);
		}

		previouslyEligible.clear();
		for (QuestRequirement quest : QuestRequirementDatabase.getQuests())
		{
			if (completed.contains(quest.getQuestName())) continue;
			boolean skillsMet = quest.getRequirements().stream()
					.allMatch(r -> levels.getOrDefault(r.getSkill(), 1) >= r.getRequiredLevel());
			boolean prereqsMet = quest.getQuestPrerequisites().stream()
					.allMatch(completed::contains);
			if (skillsMet && prereqsMet)
				previouslyEligible.add(quest.getQuestName());
		}

		if (newlyEligible.isEmpty()) return;

		if (config.loginNotifyPinnedOnly())
		{
			List<String> pinnedEligible = new ArrayList<>();
			for (String name : newlyEligible)
				if (panel.isPinned(name)) pinnedEligible.add(name);
			if (!pinnedEligible.isEmpty())
				fireLoginNotification(pinnedEligible);
		}
		else
		{
			fireLoginNotification(newlyEligible);
		}
	}

	private void fireLoginNotification(List<String> eligibleQuests)
	{
		int count = eligibleQuests.size();
		if (count == 1)
		{
			notifier.notify("\u2705 Quest now available: " + eligibleQuests.get(0));
		}
		else if (count <= 5)
		{
			notifier.notify("\u2705 " + count + " quests now available: " + String.join(", ", eligibleQuests));
		}
		else
		{
			String preview = eligibleQuests.get(0) + ", " + eligibleQuests.get(1) + ", " + eligibleQuests.get(2);
			notifier.notify("\u2705 " + count + " quests now available! Including: " + preview + "...");
		}
	}

	private void checkNotifications(Skill skill, int newLevel)
	{
		for (QuestRequirement quest : QuestRequirementDatabase.getQuests())
		{
			boolean isComplete = false;
			for (Quest q : Quest.values())
			{
				if (q.getName().equals(quest.getQuestName()))
				{
					isComplete = q.getState(client) == QuestState.FINISHED;
					break;
				}
			}
			if (isComplete) continue;

			boolean isPinned = panel.isPinned(quest.getQuestName());

			for (QuestSkillRequirement req : quest.getRequirements())
			{
				if (req.getSkill() != skill) continue;

				int required = req.getRequiredLevel();
				int oldLevel = previousLevels.getOrDefault(skill, 0);
				if (newLevel >= required && oldLevel < required)
				{
					StringBuilder remaining = new StringBuilder();
					int unmetCount = 0;
					for (QuestSkillRequirement other : quest.getRequirements())
					{
						int currentOther = other.getSkill() == skill
								? newLevel
								: client.getRealSkillLevel(other.getSkill());
						if (currentOther < other.getRequiredLevel())
						{
							if (unmetCount > 0) remaining.append(", ");
							remaining.append(other.getSkill().getName())
									.append(" ").append(other.getRequiredLevel());
							unmetCount++;
						}
					}

					if (unmetCount == 0 && isPinned)
					{
						if (config.notifyOnQuestReady())
							notifier.notify("\u2605 PINNED QUEST READY: " + quest.getQuestName()
									+ " \u2014 all requirements met!");
					}
					else if (unmetCount == 0)
					{
						if (config.notifyOnQuestReady() && !config.notifyPinnedOnly())
							notifier.notify(quest.getQuestName() + " is now ready to complete!");
					}
					else
					{
						if (config.notifyOnRequirementMet() && (!config.notifyPinnedOnly() || isPinned))
							notifier.notify(skill.getName() + " " + required + " reached for "
									+ quest.getQuestName() + "! Still needed: " + remaining + ".");
					}
				}
			}
		}
	}

	@Provides
	QuestRequirementTrackerPluginConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(QuestRequirementTrackerPluginConfig.class);
	}
}