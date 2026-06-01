package com.QuestTracker;

public enum SortOrder
{
    LEVELS_NEEDED("Closest to completion"),
    ALPHABETICAL("Alphabetical"),
    REQUIREMENTS_MET("Most requirements met"),
    DIFFICULTY("Difficulty"),
    QUESTLINE("Questline");

    private final String name;

    SortOrder(String name) { this.name = name; }

    @Override
    public String toString() { return name; }
}