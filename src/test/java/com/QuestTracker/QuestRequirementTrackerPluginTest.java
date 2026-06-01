package com.QuestTracker;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class QuestRequirementTrackerPluginTest
{
    public static void main(String[] args) throws Exception
    {
        ExternalPluginManager.loadBuiltin(
                QuestRequirementTrackerPlugin.class
        );

        RuneLite.main(args);
    }
}