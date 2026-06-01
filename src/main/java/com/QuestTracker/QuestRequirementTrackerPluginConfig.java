package com.QuestTracker;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("questrequirementtracker")
public interface QuestRequirementTrackerPluginConfig extends Config
{
	// -------------------------------------------------------------------------
	// Sections
	// -------------------------------------------------------------------------

	@ConfigSection(
			name = "Login Notifications",
			description = "Settings for the login eligibility notification",
			position = 0
	)
	String loginSection = "loginSection";

	@ConfigSection(
			name = "Level-Up Notifications",
			description = "Settings for skill level-up notifications",
			position = 1
	)
	String notificationSection = "notificationSection";

	@ConfigSection(
			name = "Filters",
			description = "Filter which quests are shown in the panel",
			position = 2
	)
	String filterSection = "filterSection";

	@ConfigSection(
			name = "Display",
			description = "Panel display options",
			position = 3
	)
	String displaySection = "displaySection";

	// -------------------------------------------------------------------------
	// Login Notifications
	// -------------------------------------------------------------------------

	@ConfigItem(
			keyName = "notifyOnLogin",
			name = "Notify on login",
			description = "Show a notification when you log in if you have quests you are now eligible to complete.",
			section = loginSection,
			position = 0
	)
	default boolean notifyOnLogin()
	{
		return true;
	}

	@ConfigItem(
			keyName = "loginNotifyPinnedOnly",
			name = "Pinned quests only",
			description = "When enabled, the login notification only fires if one of your pinned quests became eligible.",
			section = loginSection,
			position = 1
	)
	default boolean loginNotifyPinnedOnly()
	{
		return false;
	}

	@ConfigItem(
			keyName = "loginNotifyNewOnly",
			name = "Only newly eligible quests",
			description = "When enabled, only quests that became eligible since your last login session are included in the notification. "
					+ "When disabled, all currently eligible quests are listed every login.",
			section = loginSection,
			position = 2
	)
	default boolean loginNotifyNewOnly()
	{
		return true;
	}

	// -------------------------------------------------------------------------
	// Level-Up Notifications
	// -------------------------------------------------------------------------

	@ConfigItem(
			keyName = "notifyOnQuestReady",
			name = "Notify when quest is ready",
			description = "Show a notification when all skill requirements for a quest are met after a level-up.",
			section = notificationSection,
			position = 0
	)
	default boolean notifyOnQuestReady()
	{
		return true;
	}

	@ConfigItem(
			keyName = "notifyOnRequirementMet",
			name = "Notify when a requirement is met",
			description = "Show a notification when a single skill requirement for a quest is met (even if others remain).",
			section = notificationSection,
			position = 1
	)
	default boolean notifyOnRequirementMet()
	{
		return false;
	}

	@ConfigItem(
			keyName = "notifyPinnedOnly",
			name = "Pinned quests only (level-up)",
			description = "When enabled, level-up notifications only fire for pinned quests.",
			section = notificationSection,
			position = 2
	)
	default boolean notifyPinnedOnly()
	{
		return false;
	}

	// -------------------------------------------------------------------------
	// Filters
	// -------------------------------------------------------------------------

	@ConfigItem(
			keyName = "hideCompletable",
			name = "Hide completable quests",
			description = "Hide quests where all skill requirements are already met.",
			section = filterSection,
			position = 0
	)
	default boolean hideCompletable()
	{
		return false;
	}

	@ConfigItem(
			keyName = "hideMembersQuests",
			name = "Hide members quests",
			description = "Hide members-only quests from the panel.",
			section = filterSection,
			position = 1
	)
	default boolean hideMembersQuests()
	{
		return false;
	}

	@ConfigItem(
			keyName = "hideFreeQuests",
			name = "Hide free-to-play quests",
			description = "Hide free-to-play quests from the panel.",
			section = filterSection,
			position = 2
	)
	default boolean hideFreeQuests()
	{
		return false;
	}

	@ConfigItem(
			keyName = "hideUnmetPrereqs",
			name = "Hide quests with unmet prerequisites",
			description = "Hide quests where you have not yet completed the required prerequisite quests.",
			section = filterSection,
			position = 3
	)
	default boolean hideUnmetPrereqs()
	{
		return false;
	}

	@ConfigItem(
			keyName = "boostableOnly",
			name = "Boostable requirements only",
			description = "Only show quests where all unmet skill requirements are boostable.",
			section = filterSection,
			position = 4
	)
	default boolean boostableOnly()
	{
		return false;
	}

	// -------------------------------------------------------------------------
	// Display
	// -------------------------------------------------------------------------

	@ConfigItem(
			keyName = "compactMode",
			name = "Compact mode",
			description = "Hide the per-quest progress bar to make the panel more compact.",
			section = displaySection,
			position = 0
	)
	default boolean compactMode()
	{
		return false;
	}

	@ConfigItem(
			keyName = "ironmanMode",
			name = "Ironman mode",
			description = "Flag boost items that are not obtainable on ironman accounts.",
			section = displaySection,
			position = 1
	)
	default boolean ironmanMode()
	{
		return false;
	}
}