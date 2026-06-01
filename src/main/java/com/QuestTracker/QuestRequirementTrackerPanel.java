package com.QuestTracker;

import com.QuestTracker.data.QuestRequirementDatabase;
import com.QuestTracker.model.QuestRequirement;
import com.QuestTracker.model.QuestSkillRequirement;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Quest;
import net.runelite.api.QuestState;
import net.runelite.api.Skill;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class QuestRequirementTrackerPanel extends PluginPanel
{
    private java.awt.event.MouseWheelListener scrollForwarder;
    private SortOrder sortOrder = SortOrder.LEVELS_NEEDED;
    private Skill skillFilter = null;
    private String questlineFilter = "All";
    private QuestRequirement.Difficulty difficultyFilter = null;
    private final net.runelite.client.config.ConfigManager configManager;
    private final Set<String> expandedQuests = new java.util.HashSet<>();
    private final Set<String> pinnedQuests = new java.util.HashSet<>();
    private final Set<String> previouslyCompleted = new java.util.HashSet<>();
    private final List<String> completionLog = new ArrayList<>();
    private final QuestRequirementTrackerPluginConfig config;
    private String searchText = "";
    private final Client client;
    private final ClientThread clientThread;
    private final JPanel contentPanel;
    private boolean showCompletionLog = false;

    // Cached client data — rebuilt on full populate(), reused on rebuildUI()
    private Set<String> cachedCompleted = new java.util.HashSet<>();
    private Map<Skill, Integer> cachedLevels = new java.util.EnumMap<>(Skill.class);
    private boolean cachedLoggedIn = false;

    private static final DateTimeFormatter LOG_FORMAT = DateTimeFormatter.ofPattern("MM/dd HH:mm");

    private String getDifficultyAbbrev(QuestRequirement.Difficulty difficulty)
    {
        switch (difficulty)
        {
            case NOVICE:        return "NOV";
            case INTERMEDIATE:  return "INT";
            case EXPERIENCED:   return "EXP";
            case MASTER:        return "MAS";
            case GRANDMASTER:   return "GM";
            case SPECIAL:       return "SPC";
            default:            return "?";
        }
    }

    private Color getDifficultyColor(QuestRequirement.Difficulty difficulty)
    {
        switch (difficulty)
        {
            case NOVICE:        return new Color(100, 200, 100);
            case INTERMEDIATE:  return new Color(100, 180, 220);
            case EXPERIENCED:   return new Color(220, 200, 80);
            case MASTER:        return new Color(220, 120, 60);
            case GRANDMASTER:   return new Color(220, 60, 60);
            case SPECIAL:       return new Color(180, 100, 220);
            default:            return new Color(180, 180, 180);
        }
    }

    private Color getQuestlineColor(String questline)
    {
        switch (questline)
        {
            case "Elf":           return new Color(100, 220, 140);
            case "Gnome":         return new Color(80, 200, 80);
            case "Desert":        return new Color(220, 190, 80);
            case "Myreque":       return new Color(180, 60, 60);
            case "Fremennik":     return new Color(100, 160, 220);
            case "Troll":         return new Color(160, 120, 80);
            case "Karamja":       return new Color(220, 140, 60);
            case "Dwarf":         return new Color(180, 140, 100);
            case "Camelot":       return new Color(180, 180, 60);
            case "Kourend":       return new Color(140, 100, 200);
            case "Pirate":        return new Color(60, 180, 200);
            case "Zanaris":       return new Color(200, 100, 220);
            case "Goblin":        return new Color(120, 180, 80);
            case "Dragon Slayer": return new Color(220, 80, 80);
            case "Miniquest":     return new Color(160, 200, 255);
            case "Standalone":    return new Color(130, 130, 130);
            default:              return new Color(180, 180, 180);
        }
    }

    public QuestRequirementTrackerPanel(Client client, ClientThread clientThread, QuestRequirementTrackerPluginConfig config, net.runelite.client.config.ConfigManager configManager)
    {
        this.client = client;
        this.clientThread = clientThread;
        this.config = config;
        this.configManager = configManager;

        String saved = configManager.getConfiguration("questrequirementtracker", "expandedQuests");
        if (saved != null && !saved.isEmpty())
        {
            for (String name : saved.split("\\|"))
                if (!name.isEmpty()) expandedQuests.add(name);
        }

        String savedPinned = configManager.getConfiguration("questrequirementtracker", "pinnedQuests");
        if (savedPinned != null && !savedPinned.isEmpty())
        {
            for (String name : savedPinned.split("\\|"))
                if (!name.isEmpty()) pinnedQuests.add(name);
        }

        String savedLog = configManager.getConfiguration("questrequirementtracker", "completionLog");
        if (savedLog != null && !savedLog.isEmpty())
        {
            for (String entry : savedLog.split("\\|"))
                if (!entry.isEmpty()) completionLog.add(entry);
        }

        setLayout(new BorderLayout());
        setBackground(new Color(40, 40, 40));

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.setBackground(new Color(40, 40, 40));

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBackground(new Color(40, 40, 40));
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(new Color(40, 40, 40));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        scrollForwarder = e ->
                scrollPane.getVerticalScrollBar().setValue(
                        scrollPane.getVerticalScrollBar().getValue() + (e.getUnitsToScroll() * 16)
                );
        contentPanel.addMouseWheelListener(scrollForwarder);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFocusPainted(false);
        refreshButton.setBackground(new Color(60, 60, 60));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        refreshButton.addActionListener(e -> populate());

        JButton collapseButton = new JButton("Collapse All");
        collapseButton.setFocusPainted(false);
        collapseButton.setBackground(new Color(60, 60, 60));
        collapseButton.setForeground(Color.WHITE);
        collapseButton.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        collapseButton.addActionListener(e -> {
            expandedQuests.clear();
            configManager.setConfiguration("questrequirementtracker", "expandedQuests", "");
            rebuildUI();
        });

        JButton logButton = new JButton("Log");
        logButton.setFocusPainted(false);
        logButton.setBackground(new Color(60, 60, 60));
        logButton.setForeground(Color.WHITE);
        logButton.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        logButton.addActionListener(e -> {
            showCompletionLog = !showCompletionLog;
            rebuildUI();
        });

        JPanel buttonRow = new JPanel(new GridLayout(1, 3, 4, 0));
        buttonRow.setBackground(new Color(40, 40, 40));
        buttonRow.add(refreshButton);
        buttonRow.add(collapseButton);
        buttonRow.add(logButton);

        JTextField searchField = new JTextField();
        searchField.setBackground(new Color(60, 60, 60));
        searchField.setForeground(Color.WHITE);
        searchField.setCaretColor(Color.WHITE);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 80)),
                BorderFactory.createEmptyBorder(4, 6, 4, 6)
        ));
        searchField.setToolTipText("Search quests...");
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener()
        {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { searchText = searchField.getText(); rebuildUI(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { searchText = searchField.getText(); rebuildUI(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { searchText = searchField.getText(); rebuildUI(); }
        });

        JComboBox<String> skillDropdown = new JComboBox<>();
        skillDropdown.setBackground(new Color(60, 60, 60));
        skillDropdown.setForeground(Color.WHITE);
        skillDropdown.addItem("All skills");
        for (Skill s : Skill.values())
        {
            if (s != Skill.OVERALL)
                skillDropdown.addItem(s.getName());
        }
        skillDropdown.addActionListener(e -> {
            String selected = (String) skillDropdown.getSelectedItem();
            if (selected == null || selected.equals("All skills"))
                skillFilter = null;
            else
            {
                for (Skill s : Skill.values())
                {
                    if (s.getName().equals(selected))
                    {
                        skillFilter = s;
                        break;
                    }
                }
            }
            rebuildUI();
        });

        JComboBox<String> questlineDropdown = new JComboBox<>();
        questlineDropdown.setBackground(new Color(60, 60, 60));
        questlineDropdown.setForeground(Color.WHITE);
        questlineDropdown.addItem("All questlines");
        for (String ql : new String[]{"Camelot","Desert","Dragon Slayer","Dwarf","Elf","Fremennik","Gnome","Goblin","Karamja","Kourend","Miniquest","Myreque","Pirate","Standalone","Troll","Zanaris"})
            questlineDropdown.addItem(ql);
        questlineDropdown.addActionListener(e -> {
            String selected = (String) questlineDropdown.getSelectedItem();
            questlineFilter = (selected == null || selected.equals("All questlines")) ? "All" : selected;
            rebuildUI();
        });

        JComboBox<String> difficultyDropdown = new JComboBox<>();
        difficultyDropdown.setBackground(new Color(60, 60, 60));
        difficultyDropdown.setForeground(Color.WHITE);
        difficultyDropdown.addItem("All difficulties");
        for (QuestRequirement.Difficulty d : QuestRequirement.Difficulty.values())
            difficultyDropdown.addItem(d.name().charAt(0) + d.name().substring(1).toLowerCase());
        difficultyDropdown.addActionListener(e -> {
            String selected = (String) difficultyDropdown.getSelectedItem();
            if (selected == null || selected.equals("All difficulties"))
            {
                difficultyFilter = null;
            }
            else
            {
                for (QuestRequirement.Difficulty d : QuestRequirement.Difficulty.values())
                {
                    if ((d.name().charAt(0) + d.name().substring(1).toLowerCase()).equals(selected))
                    {
                        difficultyFilter = d;
                        break;
                    }
                }
            }
            rebuildUI();
        });

        JComboBox<SortOrder> sortDropdown = new JComboBox<>(SortOrder.values());
        sortDropdown.setBackground(new Color(60, 60, 60));
        sortDropdown.setForeground(Color.WHITE);
        sortDropdown.setSelectedItem(SortOrder.LEVELS_NEEDED);
        sortDropdown.addActionListener(e -> {
            sortOrder = (SortOrder) sortDropdown.getSelectedItem();
            rebuildUI();
        });

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(new Color(40, 40, 40));
        topPanel.add(buttonRow);
        topPanel.add(Box.createVerticalStrut(4));
        topPanel.add(searchField);
        topPanel.add(Box.createVerticalStrut(4));
        topPanel.add(skillDropdown);
        topPanel.add(Box.createVerticalStrut(4));
        topPanel.add(questlineDropdown);
        topPanel.add(Box.createVerticalStrut(4));
        topPanel.add(difficultyDropdown);
        topPanel.add(Box.createVerticalStrut(4));
        topPanel.add(sortDropdown);
        topPanel.add(Box.createVerticalStrut(4));

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        populate();
    }

    private void forwardScrollEvents(JComponent component, java.awt.event.MouseWheelListener listener)
    {
        for (java.awt.event.MouseWheelListener existing : component.getMouseWheelListeners())
            component.removeMouseWheelListener(existing);
        component.addMouseWheelListener(listener);
        for (Component child : component.getComponents())
        {
            if (child instanceof JComponent)
                forwardScrollEvents((JComponent) child, listener);
        }
    }

    private ImageIcon getSkillIcon(Skill skill)
    {
        try
        {
            BufferedImage img = ImageUtil.loadImageResource(
                    getClass(), "/skill_icons/" + skill.getName().toLowerCase() + ".png"
            );
            if (img != null)
            {
                Image scaled = img.getScaledInstance(14, 14, Image.SCALE_SMOOTH);
                return new ImageIcon(scaled);
            }
        }
        catch (Exception e) { /* fall through */ }
        return null;
    }

    private JPanel makeRow(String text, Color color, Font font, Insets insets)
    {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(new Color(40, 40, 40));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        JLabel label = new JLabel("<html>" + text + "</html>");
        label.setForeground(color);
        if (font != null) label.setFont(font);
        label.setBorder(BorderFactory.createEmptyBorder(insets.top, insets.left, insets.bottom, insets.right));

        row.add(label, BorderLayout.WEST);
        return row;
    }

    private JPanel makeSkillRow(Skill skill, String text, Color color, Insets insets)
    {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 2));
        row.setBackground(new Color(40, 40, 40));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));
        row.setBorder(BorderFactory.createEmptyBorder(insets.top, insets.left, insets.bottom, insets.right));

        ImageIcon icon = getSkillIcon(skill);
        if (icon != null)
            row.add(new JLabel(icon));

        JLabel label = new JLabel(text);
        label.setForeground(color);
        row.add(label);
        return row;
    }

    private void checkForNewCompletions(Set<String> completed)
    {
        for (Quest quest : Quest.values())
        {
            String name = quest.getName();
            boolean nowComplete = completed.contains(name);
            boolean wasTracked = previouslyCompleted.contains(name);

            if (nowComplete && !wasTracked)
            {
                previouslyCompleted.add(name);
                String entry = LocalDateTime.now().format(LOG_FORMAT) + " \u2014 " + name;
                completionLog.add(0, entry);
                if (completionLog.size() > 50) completionLog.remove(completionLog.size() - 1);
                configManager.setConfiguration("questrequirementtracker", "completionLog",
                        String.join("|", completionLog));
            }
        }
    }

    public void populate()
    {
        clientThread.invokeLater(() ->
        {
            cachedLoggedIn = client.getGameState() == GameState.LOGGED_IN;
            cachedCompleted.clear();
            cachedLevels.clear();

            if (cachedLoggedIn)
            {
                for (Quest q : Quest.values())
                {
                    if (q.getState(client) == QuestState.FINISHED)
                        cachedCompleted.add(q.getName());
                }
                for (Skill s : Skill.values())
                {
                    if (s != Skill.OVERALL)
                        cachedLevels.put(s, client.getRealSkillLevel(s));
                }
            }

            checkForNewCompletions(cachedCompleted);
            SwingUtilities.invokeLater(() -> buildUI(cachedLoggedIn, cachedCompleted, cachedLevels));
        });
    }

    private void rebuildUI()
    {
        SwingUtilities.invokeLater(() -> buildUI(cachedLoggedIn, cachedCompleted, cachedLevels));
    }

    private void buildUI(boolean loggedIn, Set<String> completed, Map<Skill, Integer> levels)
    {
        contentPanel.removeAll();
        List<QuestRequirement> quests = new ArrayList<>(QuestRequirementDatabase.getQuests());

        // Miniquests only shown when the Miniquest questline filter is active
        if (!questlineFilter.equals("Miniquest"))
            quests.removeIf(QuestRequirement::isMiniquest);

        int currentQP = 0;
        int earnableQP = 0;
        for (QuestRequirement q : quests)
        {
            if (loggedIn && completed.contains(q.getQuestName()))
                currentQP += q.getQuestPoints();
            else
                earnableQP += q.getQuestPoints();
        }

        int totalQuests = 0;
        int completableQuests = 0;
        for (QuestRequirement quest : quests)
        {
            if (loggedIn && completed.contains(quest.getQuestName())) continue;
            totalQuests++;
            if (getTotalLevelsNeeded(quest, loggedIn, levels) == 0) completableQuests++;
        }

        JLabel headerLabel = new JLabel(totalQuests + " quests remaining  |  " + completableQuests + " ready");
        headerLabel.setForeground(new Color(180, 180, 180));
        headerLabel.setFont(headerLabel.getFont().deriveFont(13f));
        JPanel headerWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 2));
        headerWrapper.setBackground(new Color(40, 40, 40));
        headerWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));
        headerWrapper.add(headerLabel);
        contentPanel.add(headerWrapper);

        JLabel qpLabel = new JLabel("QP: " + currentQP + " earned  |  " + earnableQP + " earnable");
        qpLabel.setForeground(new Color(200, 180, 0));
        qpLabel.setFont(qpLabel.getFont().deriveFont(13f));
        JPanel qpWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 2));
        qpWrapper.setBackground(new Color(40, 40, 40));
        qpWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));
        qpWrapper.add(qpLabel);
        contentPanel.add(qpWrapper);

        int totalQP = currentQP + earnableQP;
        int pct = totalQP == 0 ? 0 : (int) ((currentQP / (double) totalQP) * 100);
        JLabel progressLabel = new JLabel("Overall progress: " + pct + "% (" + currentQP + " / " + totalQP + " QP)");
        progressLabel.setForeground(new Color(100, 180, 255));
        progressLabel.setFont(progressLabel.getFont().deriveFont(13f));
        JPanel overallProgressWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 2));
        overallProgressWrapper.setBackground(new Color(40, 40, 40));
        overallProgressWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));
        overallProgressWrapper.add(progressLabel);
        contentPanel.add(overallProgressWrapper);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(80, 80, 80));
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        contentPanel.add(separator);

        if (showCompletionLog)
        {
            JLabel logHeader = new JLabel("Recently Completed");
            logHeader.setForeground(new Color(200, 180, 0));
            logHeader.setFont(logHeader.getFont().deriveFont(Font.BOLD, 12f));
            logHeader.setBorder(BorderFactory.createEmptyBorder(8, 6, 4, 4));
            logHeader.setAlignmentX(LEFT_ALIGNMENT);
            contentPanel.add(logHeader);

            if (completionLog.isEmpty())
            {
                contentPanel.add(makeRow("No quests completed yet.", new Color(150, 150, 150),
                        contentPanel.getFont().deriveFont(11f), new Insets(2, 8, 2, 4)));
            }
            else
            {
                for (String entry : completionLog)
                {
                    contentPanel.add(makeRow(entry, new Color(0, 200, 0),
                            contentPanel.getFont().deriveFont(11f), new Insets(2, 8, 2, 4)));
                }
            }

            forwardScrollEvents(contentPanel, scrollForwarder);
            contentPanel.revalidate();
            contentPanel.repaint();
            return;
        }

        switch (sortOrder)
        {
            case ALPHABETICAL:
                quests.sort((a, b) -> a.getQuestName().compareToIgnoreCase(b.getQuestName()));
                break;
            case REQUIREMENTS_MET:
                quests.sort((a, b) -> {
                    int metA = (int) a.getRequirements().stream()
                            .filter(r -> levels.getOrDefault(r.getSkill(), 1) >= r.getRequiredLevel())
                            .count();
                    int metB = (int) b.getRequirements().stream()
                            .filter(r -> levels.getOrDefault(r.getSkill(), 1) >= r.getRequiredLevel())
                            .count();
                    int totalA = a.getRequirements().size();
                    int totalB = b.getRequirements().size();
                    double pctA = totalA == 0 ? 1.0 : (double) metA / totalA;
                    double pctB = totalB == 0 ? 1.0 : (double) metB / totalB;
                    return Double.compare(pctB, pctA);
                });
                break;
            case DIFFICULTY:
                quests.sort((a, b) -> {
                    int cmp = a.getDifficulty().compareTo(b.getDifficulty());
                    if (cmp != 0) return cmp;
                    return a.getQuestName().compareToIgnoreCase(b.getQuestName());
                });
                break;
            case QUESTLINE:
                quests.sort((a, b) -> {
                    int cmp = a.getQuestline().compareToIgnoreCase(b.getQuestline());
                    if (cmp != 0) return cmp;
                    return a.getQuestName().compareToIgnoreCase(b.getQuestName());
                });
                break;
            default:
                quests.sort((a, b) -> Integer.compare(
                        getTotalLevelsNeeded(a, loggedIn, levels),
                        getTotalLevelsNeeded(b, loggedIn, levels)));
                break;
        }

        quests.sort((a, b) -> {
            boolean pinnedA = pinnedQuests.contains(a.getQuestName());
            boolean pinnedB = pinnedQuests.contains(b.getQuestName());
            return Boolean.compare(!pinnedA, !pinnedB);
        });

        String filter = searchText.toLowerCase().trim();
        if (!filter.isEmpty())
            quests.removeIf(q -> !q.getQuestName().toLowerCase().contains(filter));

        if (config.hideCompletable())
            quests.removeIf(q -> getTotalLevelsNeeded(q, loggedIn, levels) == 0);

        if (config.boostableOnly())
        {
            quests.removeIf(q -> q.getRequirements().stream()
                    .filter(req -> levels.getOrDefault(req.getSkill(), 1) < req.getRequiredLevel())
                    .allMatch(req -> !req.isBoostable()));
        }

        if (config.hideMembersQuests())
            quests.removeIf(QuestRequirement::isMembers);

        if (config.hideFreeQuests())
            quests.removeIf(q -> !q.isMembers());

        if (config.hideUnmetPrereqs())
        {
            quests.removeIf(q -> q.getQuestPrerequisites().stream()
                    .anyMatch(prereq -> !completed.contains(prereq)));
        }

        if (skillFilter != null)
        {
            final Skill sf = skillFilter;
            quests.removeIf(q -> q.getRequirements().stream().noneMatch(req -> req.getSkill() == sf));
        }

        if (!questlineFilter.equals("All"))
            quests.removeIf(q -> !q.getQuestline().equals(questlineFilter));

        if (difficultyFilter != null)
            quests.removeIf(q -> q.getDifficulty() != difficultyFilter);

        for (QuestRequirement quest : quests)
        {
            if (loggedIn && completed.contains(quest.getQuestName())) continue;

            int levelsNeeded = getTotalLevelsNeeded(quest, loggedIn, levels);
            boolean allMet = levelsNeeded == 0;
            boolean expanded = expandedQuests.contains(quest.getQuestName());
            boolean pinned = pinnedQuests.contains(quest.getQuestName());
            boolean isMiniquest = quest.isMiniquest();
            String questName = quest.getQuestName();

            List<String> unmetPrereqs = new ArrayList<>();
            for (String prereq : quest.getQuestPrerequisites())
            {
                if (!completed.contains(prereq)) unmetPrereqs.add(prereq);
            }

            String questLabelText = questName;

            JPanel questHeader = new JPanel(new BorderLayout());
            Color headerBg;
            if (!unmetPrereqs.isEmpty())
                headerBg = new Color(50, 30, 70);
            else if (allMet)
                headerBg = new Color(0, 70, 0);
            else if (levelsNeeded <= 10)
                headerBg = new Color(80, 60, 0);
            else if (levelsNeeded <= 30)
                headerBg = new Color(70, 30, 0);
            else
                headerBg = new Color(70, 0, 0);

            questHeader.setBackground(headerBg);
            questHeader.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
            questHeader.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            questHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

            JLabel questLabel = new JLabel(questLabelText);
            questLabel.setForeground(allMet ? new Color(0, 220, 0) : Color.YELLOW);
            questLabel.setFont(questLabel.getFont().deriveFont(Font.BOLD, 17f));

            JLabel toggleLabel = new JLabel(expanded ? " \u25b2" : " \u25bc");
            toggleLabel.setForeground(new Color(150, 150, 150));
            toggleLabel.setFont(toggleLabel.getFont().deriveFont(9f));

            JLabel pinLabel = new JLabel(pinned ? "[P]" : "[ ]");
            pinLabel.setForeground(pinned ? new Color(200, 180, 0) : new Color(100, 100, 100));
            pinLabel.setFont(pinLabel.getFont().deriveFont(14f));
            pinLabel.setToolTipText("Pin this quest");
            pinLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pinLabel.addMouseListener(new java.awt.event.MouseAdapter()
            {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e)
                {
                    e.consume();
                    if (pinnedQuests.contains(questName))
                        pinnedQuests.remove(questName);
                    else
                        pinnedQuests.add(questName);
                    configManager.setConfiguration("questrequirementtracker", "pinnedQuests",
                            String.join("|", pinnedQuests));
                    rebuildUI();
                }
                @Override public void mousePressed(java.awt.event.MouseEvent e) { e.consume(); }
                @Override public void mouseReleased(java.awt.event.MouseEvent e) { e.consume(); }
            });

            JLabel wikiLabel = new JLabel("[W]");
            wikiLabel.setForeground(new Color(100, 180, 255));
            wikiLabel.setFont(wikiLabel.getFont().deriveFont(12f));
            wikiLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            wikiLabel.setToolTipText("Open Wiki Page");
            wikiLabel.addMouseListener(new java.awt.event.MouseAdapter()
            {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e)
                {
                    e.consume();
                    String url = "https://oldschool.runescape.wiki/w/" + questName.replace(" ", "_");
                    try { java.awt.Desktop.getDesktop().browse(new java.net.URI(url)); }
                    catch (Exception ex) { log.error("Failed to open wiki URL", ex); } //NOSONAR
                }
                @Override public void mousePressed(java.awt.event.MouseEvent e) { e.consume(); }
                @Override public void mouseReleased(java.awt.event.MouseEvent e) { e.consume(); }
            });

            JPanel westPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
            westPanel.setOpaque(false);
            westPanel.add(pinLabel);
            westPanel.add(wikiLabel);

            JPanel eastPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 0));
            eastPanel.setOpaque(false);
            eastPanel.add(toggleLabel);

            String questlineText = quest.getQuestline().equals("Standalone") ? "Standalone" : quest.getQuestline() + " Questline";
            JLabel questlineLabel = new JLabel(questlineText);
            questlineLabel.setForeground(getQuestlineColor(quest.getQuestline()));
            questlineLabel.setFont(questlineLabel.getFont().deriveFont(13f));

            String badgeText = getDifficultyAbbrev(quest.getDifficulty()) + "  |  " + quest.getQuestPoints() + " QP"
                    + (isMiniquest ? "  |  [MQ]" : "");
            JLabel diffBadgeLabel = new JLabel(badgeText);
            diffBadgeLabel.setForeground(isMiniquest ? new Color(160, 200, 255) : getDifficultyColor(quest.getDifficulty()));
            diffBadgeLabel.setFont(diffBadgeLabel.getFont().deriveFont(Font.BOLD, 12f));

            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
            centerPanel.setOpaque(false);
            centerPanel.add(questLabel);
            if (!allMet)
            {
                JLabel levelsLabel = new JLabel("-" + levelsNeeded + " levels needed");
                levelsLabel.setForeground(new Color(200, 120, 120));
                levelsLabel.setFont(levelsLabel.getFont().deriveFont(Font.BOLD, 13f));
                centerPanel.add(levelsLabel);
            }
            centerPanel.add(questlineLabel);
            centerPanel.add(diffBadgeLabel);

            questHeader.add(westPanel, BorderLayout.WEST);
            questHeader.add(centerPanel, BorderLayout.CENTER);
            questHeader.add(eastPanel, BorderLayout.EAST);

            java.awt.event.MouseAdapter expandListener = new java.awt.event.MouseAdapter()
            {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e)
                {
                    if (expandedQuests.contains(questName))
                        expandedQuests.remove(questName);
                    else
                        expandedQuests.add(questName);
                    configManager.setConfiguration("questrequirementtracker", "expandedQuests",
                            String.join("|", expandedQuests));
                    rebuildUI();
                }
            };

            questHeader.addMouseListener(expandListener);
            questLabel.addMouseListener(expandListener);
            eastPanel.addMouseListener(expandListener);
            toggleLabel.addMouseListener(expandListener);

            if (pinned)
            {
                questHeader.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 180, 0), 1),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)
                ));
            }

            contentPanel.add(questHeader);

            int metCount = 0;
            int totalCount = quest.getRequirements().size();
            for (QuestSkillRequirement req : quest.getRequirements())
            {
                int current = levels.getOrDefault(req.getSkill(), 1);
                if (current >= req.getRequiredLevel()) metCount++;
            }

            JProgressBar progressBar = new JProgressBar(0, totalCount);
            progressBar.setValue(metCount);
            progressBar.setString(metCount + " / " + totalCount + " reqs met");
            progressBar.setStringPainted(true);
            progressBar.setUI(new javax.swing.plaf.basic.BasicProgressBarUI()
            {
                @Override
                protected Point getStringPlacement(Graphics g, String progressString, int x, int y, int width, int height)
                {
                    FontMetrics fm = g.getFontMetrics();
                    int stringWidth = fm.stringWidth(progressString);
                    int stringHeight = fm.getAscent();
                    return new Point(x + (width - stringWidth) / 2, y + (height + stringHeight) / 2 - 1);
                }
            });
            progressBar.setForeground(allMet ? new Color(0, 200, 0) : new Color(180, 100, 0));
            progressBar.setBackground(new Color(60, 60, 60));
            progressBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 18));
            progressBar.setAlignmentX(LEFT_ALIGNMENT);

            StringBuilder tooltip = new StringBuilder("<html>");
            for (QuestSkillRequirement req : quest.getRequirements())
            {
                int current = levels.getOrDefault(req.getSkill(), 1);
                int gap = req.getRequiredLevel() - current;
                if (gap > 0)
                {
                    String iconPath = QuestRequirementTrackerPanel.class
                            .getResource("/skill_icons/" + req.getSkill().getName().toLowerCase() + ".png") != null
                            ? "<img src='" + QuestRequirementTrackerPanel.class
                            .getResource("/skill_icons/" + req.getSkill().getName().toLowerCase() + ".png") + "'>&nbsp;"
                            : "";
                    tooltip.append(iconPath)
                            .append("\u2717 ").append(req.getSkill().getName())
                            .append(": ").append(current).append(" / ").append(req.getRequiredLevel())
                            .append(" (need ").append(gap).append(")<br>");
                }
            }
            if (allMet) tooltip.append("All requirements met!");
            tooltip.append("</html>");
            progressBar.setToolTipText(tooltip.toString());

            if (!config.compactMode())
            {
                JPanel progressWrapper = new JPanel(new BorderLayout());
                progressWrapper.setBackground(new Color(40, 40, 40));
                progressWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 18));
                progressWrapper.add(progressBar, BorderLayout.CENTER);
                contentPanel.add(progressWrapper);
            }

            if (expanded)
            {
                if (!quest.getQuestPrerequisites().isEmpty())
                {
                    contentPanel.add(makeRow("Quest Requirements:",
                            new Color(180, 180, 100),
                            contentPanel.getFont().deriveFont(Font.BOLD, 11f),
                            new Insets(4, 8, 2, 4)));

                    for (String prereq : quest.getQuestPrerequisites())
                    {
                        boolean prereqDone = loggedIn && completed.contains(prereq);
                        contentPanel.add(makeRow(
                                (prereqDone ? "\u2713 " : "\u2717 ") + prereq,
                                prereqDone ? new Color(0, 200, 0) : new Color(220, 100, 100),
                                contentPanel.getFont().deriveFont(11f),
                                new Insets(1, 16, 1, 4)
                        ));
                    }

                    JSeparator prereqSep = new JSeparator();
                    prereqSep.setForeground(new Color(70, 70, 70));
                    prereqSep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
                    contentPanel.add(prereqSep);
                }

                if (allMet && unmetPrereqs.isEmpty())
                {
                    contentPanel.add(makeRow(
                            "\u2713 Ready to complete!",
                            new Color(0, 220, 0),
                            contentPanel.getFont().deriveFont(11f),
                            new Insets(2, 8, 4, 4)
                    ));
                }
                else
                {
                    for (QuestSkillRequirement req : quest.getRequirements())
                    {
                        int currentLevel = levels.getOrDefault(req.getSkill(), 1);
                        int required = req.getRequiredLevel();
                        int gap = required - currentLevel;

                        if (gap <= 0)
                        {
                            contentPanel.add(makeSkillRow(
                                    req.getSkill(),
                                    req.getSkill().getName() + ": " + currentLevel + " / " + required + " \u2713",
                                    new Color(0, 200, 0),
                                    new Insets(1, 8, 1, 4)
                            ));
                        }
                        else
                        {
                            contentPanel.add(makeSkillRow(
                                    req.getSkill(),
                                    req.getSkill().getName() + ": " + currentLevel + " / " + required + " (need " + gap + ")",
                                    new Color(220, 60, 60),
                                    new Insets(1, 8, 0, 4)
                            ));

                            if (req.isBoostable())
                            {
                                int boostFromLevel = req.getBoostFromLevel();
                                boolean canBoost = currentLevel >= boostFromLevel;
                                boolean unobtainable = config.ironmanMode() && req.isIronmanUnobtainable();

                                String boostText = unobtainable
                                        ? "\u2717 " + req.getBoostItem() + " (+" + req.getBoostAmount() + ") \u2014 not obtainable on ironman"
                                        : "\u2191 " + req.getBoostItem()
                                          + " (+" + req.getBoostAmount() + ")"
                                          + (canBoost ? " \u2014 boost now!" : " \u2014 need " + boostFromLevel);

                                contentPanel.add(makeRow(
                                        boostText,
                                        unobtainable ? new Color(180, 50, 50) : (canBoost ? new Color(100, 220, 100) : new Color(180, 140, 255)),
                                        contentPanel.getFont().deriveFont(11f),
                                        new Insets(0, 16, 2, 4)
                                ));
                            }
                        }
                    }
                }
            }

            contentPanel.add(Box.createVerticalStrut(4));
        }

        forwardScrollEvents(contentPanel, scrollForwarder);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private int getTotalLevelsNeeded(QuestRequirement quest, boolean loggedIn, Map<Skill, Integer> levels)
    {
        int total = 0;
        for (QuestSkillRequirement req : quest.getRequirements())
        {
            int current = loggedIn ? levels.getOrDefault(req.getSkill(), 1) : 1;
            int gap = req.getRequiredLevel() - current;
            if (gap > 0) total += gap;
        }
        return total;
    }

    public boolean isPinned(String questName)
    {
        return pinnedQuests.contains(questName);
    }
}