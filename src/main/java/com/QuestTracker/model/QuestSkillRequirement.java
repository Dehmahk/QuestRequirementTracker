package com.QuestTracker.model;

import net.runelite.api.Skill;

public class QuestSkillRequirement
{
    private final Skill skill;
    private final int requiredLevel;
    private final boolean boostable;
    private final String boostItem;
    private final int boostAmount;
    private final boolean ironmanUnobtainable;

    // Full constructor
    public QuestSkillRequirement(
            Skill skill,
            int requiredLevel,
            boolean boostable,
            String boostItem,
            int boostAmount,
            boolean ironmanUnobtainable)
    {
        this.skill = skill;
        this.requiredLevel = requiredLevel;
        this.boostable = boostable;
        this.boostItem = boostItem;
        this.boostAmount = boostAmount;
        this.ironmanUnobtainable = ironmanUnobtainable;
    }

    // Existing entries default to ironmanUnobtainable = false
    public QuestSkillRequirement(
            Skill skill,
            int requiredLevel,
            boolean boostable,
            String boostItem,
            int boostAmount)
    {
        this(skill, requiredLevel, boostable, boostItem, boostAmount, false);
    }

    public Skill getSkill()                 { return skill; }
    public int getRequiredLevel()           { return requiredLevel; }
    public boolean isBoostable()            { return boostable; }
    public String getBoostItem()            { return boostItem; }
    public int getBoostAmount()             { return boostAmount; }
    public int getBoostFromLevel()          { return requiredLevel - boostAmount; }
    public boolean isIronmanUnobtainable()  { return ironmanUnobtainable; }
}