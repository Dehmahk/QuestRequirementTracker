package com.QuestTracker.model;

import java.util.List;

public class QuestRequirement
{
    public enum Difficulty
    {
        NOVICE, INTERMEDIATE, EXPERIENCED, MASTER, GRANDMASTER, SPECIAL
    }

    private final String questName;
    private final List<QuestSkillRequirement> requirements;
    private final boolean members;
    private final List<String> questPrerequisites;
    private final int questPoints;
    private final Difficulty difficulty;
    private final String questline;
    private final boolean miniquest;

    // Full constructor
    public QuestRequirement(
            String questName,
            List<QuestSkillRequirement> requirements,
            boolean members,
            List<String> questPrerequisites,
            int questPoints,
            Difficulty difficulty,
            String questline,
            boolean miniquest)
    {
        this.questName = questName;
        this.requirements = requirements;
        this.members = members;
        this.questPrerequisites = questPrerequisites;
        this.questPoints = questPoints;
        this.difficulty = difficulty;
        this.questline = questline;
        this.miniquest = miniquest;
    }

    // Backwards-compatible constructors — miniquest defaults to false
    public QuestRequirement(
            String questName,
            List<QuestSkillRequirement> requirements,
            boolean members,
            List<String> questPrerequisites,
            int questPoints,
            Difficulty difficulty,
            String questline)
    {
        this(questName, requirements, members, questPrerequisites, questPoints, difficulty, questline, false);
    }

    public QuestRequirement(
            String questName,
            List<QuestSkillRequirement> requirements,
            boolean members,
            List<String> questPrerequisites,
            int questPoints)
    {
        this(questName, requirements, members, questPrerequisites, questPoints, Difficulty.NOVICE, "Standalone", false);
    }

    public QuestRequirement(
            String questName,
            List<QuestSkillRequirement> requirements,
            boolean members,
            List<String> questPrerequisites)
    {
        this(questName, requirements, members, questPrerequisites, 1, Difficulty.NOVICE, "Standalone", false);
    }

    public QuestRequirement(
            String questName,
            List<QuestSkillRequirement> requirements,
            boolean members)
    {
        this(questName, requirements, members, List.of(), 1, Difficulty.NOVICE, "Standalone", false);
    }

    public QuestRequirement(
            String questName,
            List<QuestSkillRequirement> requirements)
    {
        this(questName, requirements, true, List.of(), 1, Difficulty.NOVICE, "Standalone", false);
    }

    public String getQuestName()            { return questName; }
    public List<QuestSkillRequirement> getRequirements() { return requirements; }
    public boolean isMembers()              { return members; }
    public List<String> getQuestPrerequisites() { return questPrerequisites; }
    public int getQuestPoints()             { return questPoints; }
    public Difficulty getDifficulty()       { return difficulty; }
    public String getQuestline()            { return questline; }
    public boolean isMiniquest()            { return miniquest; }
}