package com.QuestTracker.data;

import com.QuestTracker.model.QuestRequirement;
import com.QuestTracker.model.QuestRequirement.Difficulty;
import com.QuestTracker.model.QuestSkillRequirement;
import net.runelite.api.Skill;

import java.util.ArrayList;
import java.util.List;

public class QuestRequirementDatabase
{
    public static List<QuestRequirement> getQuests()
    {
        List<QuestRequirement> quests = new ArrayList<>();

        // === MINIQUESTS ===

        quests.add(new QuestRequirement("Barbarian Training", List.of(
                new QuestSkillRequirement(Skill.FISHING, 55, true, "Fish Pie", 3),
                new QuestSkillRequirement(Skill.FIREMAKING, 35, false, null, 0),
                new QuestSkillRequirement(Skill.STRENGTH, 35, false, null, 0),
                new QuestSkillRequirement(Skill.AGILITY, 15, false, null, 0),
                new QuestSkillRequirement(Skill.FARMING, 15, false, null, 0),
                new QuestSkillRequirement(Skill.CRAFTING, 11, false, null, 0),
                new QuestSkillRequirement(Skill.SMITHING, 5, false, null, 0),
                new QuestSkillRequirement(Skill.HERBLORE, 4, false, null, 0)
        ), true, List.of(), 0, Difficulty.EXPERIENCED, "Miniquest", true));

        quests.add(new QuestRequirement("Mage Arena I", List.of(
                new QuestSkillRequirement(Skill.MAGIC, 60, false, null, 0)
        ), true, List.of(), 0, Difficulty.EXPERIENCED, "Miniquest", true));

        quests.add(new QuestRequirement("Mage Arena II", List.of(
                new QuestSkillRequirement(Skill.MAGIC, 75, false, null, 0)
        ), true, List.of("Mage Arena I"), 0, Difficulty.MASTER, "Miniquest", true));

        quests.add(new QuestRequirement("The Frozen Door", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 70, false, null, 0),
                new QuestSkillRequirement(Skill.HITPOINTS, 70, false, null, 0),
                new QuestSkillRequirement(Skill.RANGED, 70, false, null, 0),
                new QuestSkillRequirement(Skill.STRENGTH, 70, false, null, 0)
        ), true, List.of("Desert Treasure I"), 0, Difficulty.MASTER, "Miniquest", true));

        // === GRANDMASTER ===

        quests.add(new QuestRequirement("Dragon Slayer II", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 60, false, null, 0),
                new QuestSkillRequirement(Skill.MINING, 68, false, null, 0),
                new QuestSkillRequirement(Skill.CRAFTING, 62, false, null, 0),
                new QuestSkillRequirement(Skill.MAGIC, 75, false, null, 0),
                new QuestSkillRequirement(Skill.SMITHING, 70, false, null, 0),
                new QuestSkillRequirement(Skill.THIEVING, 60, false, null, 0),
                new QuestSkillRequirement(Skill.CONSTRUCTION, 50, false, null, 0),
                new QuestSkillRequirement(Skill.HITPOINTS, 50, false, null, 0)
        ), true, List.of("Dragon Slayer I", "Legends' Quest", "Shilo Village", "Sins of the Father"), 5, Difficulty.GRANDMASTER, "Dragon Slayer"));

        quests.add(new QuestRequirement("Monkey Madness II", List.of(
                new QuestSkillRequirement(Skill.SLAYER, 69, false, null, 0),
                new QuestSkillRequirement(Skill.CRAFTING, 70, false, null, 0),
                new QuestSkillRequirement(Skill.HUNTER, 60, false, null, 0),
                new QuestSkillRequirement(Skill.AGILITY, 55, false, null, 0),
                new QuestSkillRequirement(Skill.THIEVING, 55, false, null, 0),
                new QuestSkillRequirement(Skill.FIREMAKING, 60, false, null, 0)
        ), true, List.of("Monkey Madness I", "Enlightened Journey", "Troll Stronghold", "Watchtower", "The Eyes of Glouphrie"), 4, Difficulty.GRANDMASTER, "Gnome"));

        quests.add(new QuestRequirement("Song of the Elves", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 70, false, null, 0),
                new QuestSkillRequirement(Skill.CONSTRUCTION, 70, false, null, 0),
                new QuestSkillRequirement(Skill.FARMING, 70, false, null, 0),
                new QuestSkillRequirement(Skill.HERBLORE, 70, false, null, 0),
                new QuestSkillRequirement(Skill.HUNTER, 70, false, null, 0),
                new QuestSkillRequirement(Skill.MINING, 70, false, null, 0),
                new QuestSkillRequirement(Skill.SMITHING, 70, false, null, 0),
                new QuestSkillRequirement(Skill.WOODCUTTING, 70, false, null, 0)
        ), true, List.of("Mourning's End Part II", "Making History"), 4, Difficulty.GRANDMASTER, "Elf"));

        quests.add(new QuestRequirement("Desert Treasure II", List.of(
                new QuestSkillRequirement(Skill.MAGIC, 75, false, null, 0),
                new QuestSkillRequirement(Skill.FIREMAKING, 75, false, null, 0),
                new QuestSkillRequirement(Skill.THIEVING, 70, false, null, 0),
                new QuestSkillRequirement(Skill.HERBLORE, 62, false, null, 0),
                new QuestSkillRequirement(Skill.CONSTRUCTION, 60, false, null, 0),
                new QuestSkillRequirement(Skill.RUNECRAFT, 60, false, null, 0)
        ), true, List.of("Desert Treasure I", "Secrets of the North"), 5, Difficulty.GRANDMASTER, "Mahjarrat"));

        quests.add(new QuestRequirement("While Guthix Sleeps", List.of(
                new QuestSkillRequirement(Skill.THIEVING, 72, false, null, 0),
                new QuestSkillRequirement(Skill.AGILITY, 66, false, null, 0),
                new QuestSkillRequirement(Skill.HERBLORE, 65, false, null, 0),
                new QuestSkillRequirement(Skill.MAGIC, 67, false, null, 0),
                new QuestSkillRequirement(Skill.FARMING, 65, false, null, 0),
                new QuestSkillRequirement(Skill.HUNTER, 62, false, null, 0),
                new QuestSkillRequirement(Skill.FIREMAKING, 49, false, null, 0)

        ), true, List.of("Defender of Varrock", "The Path of Glouphrie", "Fight Arena", "Dream Mentor", "The Hand in the Sand", "Wanted!", "Temple of the Eye", "Tears of Guthix", "Nature Spirit", "A Tail of Two Cats"), 5, Difficulty.GRANDMASTER, "Mahjarrat"));

        // === MASTER ===

        quests.add(new QuestRequirement("Sins of the Father", List.of(
                new QuestSkillRequirement(Skill.WOODCUTTING, 62, false, null, 0),
                new QuestSkillRequirement(Skill.FLETCHING, 60, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.CRAFTING, 56, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.AGILITY, 52, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.ATTACK, 50, false, null, 0),
                new QuestSkillRequirement(Skill.SLAYER, 50, false, null, 0),
                new QuestSkillRequirement(Skill.MAGIC, 49, false, null, 0)
        ), true, List.of("A Taste of Hope"), 2, Difficulty.MASTER, "Myreque"));

        quests.add(new QuestRequirement("Legends' Quest", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 50, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.CRAFTING, 50, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.HERBLORE, 45, true, "Botanical Pie", 4),
                new QuestSkillRequirement(Skill.MAGIC, 56, false, null, 0),
                new QuestSkillRequirement(Skill.MINING, 52, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.PRAYER, 42, false, null, 0),
                new QuestSkillRequirement(Skill.SMITHING, 50, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.STRENGTH, 50, false, null, 0),
                new QuestSkillRequirement(Skill.THIEVING, 50, true, "Sq'irk Juice", 5),
                new QuestSkillRequirement(Skill.WOODCUTTING, 50, false, null, 0)
        ), true, List.of("Family Crest", "Heroes' Quest", "Shilo Village", "Underground Pass", "Waterfall Quest"), 4, Difficulty.MASTER, "Standalone"));

        quests.add(new QuestRequirement("Recipe for Disaster", List.of(
                new QuestSkillRequirement(Skill.COOKING, 70, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.FISHING, 53, true, "Fish Pie", 3),
                new QuestSkillRequirement(Skill.FIREMAKING, 50, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.MAGIC, 59, false, null, 0),
                new QuestSkillRequirement(Skill.MINING, 50, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.SMITHING, 40, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.THIEVING, 53, true, "Sq'irk Juice", 5),
                new QuestSkillRequirement(Skill.AGILITY, 48, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.RANGED, 40, false, null, 0),
                new QuestSkillRequirement(Skill.CRAFTING, 40, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.FLETCHING, 10, true, "Spicy Stew", 5)
        ), true, List.of("Cook's Assistant"), 10, Difficulty.MASTER, "Standalone"));

        quests.add(new QuestRequirement("Mourning's End Part II", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 60, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.RANGED, 60, false, null, 0)
        ), true, List.of("Mourning's End Part I"), 2, Difficulty.MASTER, "Elf"));

        quests.add(new QuestRequirement("The Fremennik Exiles", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 65, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.HERBLORE, 60, true, "Botanical Pie", 4),
                new QuestSkillRequirement(Skill.MINING, 60, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.RUNECRAFT, 55, false, null, 0),
                new QuestSkillRequirement(Skill.SMITHING, 60, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.SLAYER, 60, false, null, 0)
        ), true, List.of("The Fremennik Exiles", "Lunar Diplomacy"), 2, Difficulty.MASTER, "Fremennik"));

        quests.add(new QuestRequirement("Swan Song", List.of(
                new QuestSkillRequirement(Skill.COOKING, 62, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.CRAFTING, 40, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.FISHING, 62, true, "Fish Pie", 3),
                new QuestSkillRequirement(Skill.FIREMAKING, 42, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.MAGIC, 66, false, null, 0),
                new QuestSkillRequirement(Skill.SMITHING, 45, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.WOODCUTTING, 42, false, null, 0)
        ), true, List.of("One Small Favour", "Garden of Tranquillity"), 2, Difficulty.MASTER, "Standalone"));

        quests.add(new QuestRequirement("Grim Tales", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 59, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.FARMING, 45, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.HERBLORE, 52, true, "Botanical Pie", 4),
                new QuestSkillRequirement(Skill.MINING, 45, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.THIEVING, 58, true, "Sq'irk Juice", 5),
                new QuestSkillRequirement(Skill.WOODCUTTING, 71, false, null, 0)
        ), true, List.of("Witchaven Dungeon", "Horror from the Deep"), 2, Difficulty.MASTER, "Standalone"));

        quests.add(new QuestRequirement("A Kingdom Divided", List.of(
                new QuestSkillRequirement(Skill.WOODCUTTING, 52, false, null, 0),
                new QuestSkillRequirement(Skill.FARMING, 50, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.HERBLORE, 50, true, "Botanical Pie", 4),
                new QuestSkillRequirement(Skill.THIEVING, 46, true, "Sq'irk Juice", 5),
                new QuestSkillRequirement(Skill.MINING, 42, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.MAGIC, 40, false, null, 0),
                new QuestSkillRequirement(Skill.CRAFTING, 38, true, "Mushroom Pie", 4)
        ), true, List.of("Architectural Alliance", "Client of Kourend"), 2, Difficulty.MASTER, "Kourend"));

        // === EXPERIENCED ===

        quests.add(new QuestRequirement("Cabin Fever", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 42, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.CRAFTING, 45, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.RANGED, 40, false, null, 0),
                new QuestSkillRequirement(Skill.SMITHING, 50, true, "Dwarven Stout (m)", 3, true)
        ), true, List.of("Pirate's Treasure", "Rum Deal", "Tai Bwo Wannai Trio"), 2, Difficulty.EXPERIENCED, "Pirate"));

        quests.add(new QuestRequirement("Darkness of Hallowvale", List.of(
                new QuestSkillRequirement(Skill.MINING, 20, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.CRAFTING, 32, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.MAGIC, 33, false, null, 0),
                new QuestSkillRequirement(Skill.STRENGTH, 40, false, null, 0),
                new QuestSkillRequirement(Skill.CONSTRUCTION, 5, false, null, 0),
                new QuestSkillRequirement(Skill.AGILITY, 26, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.THIEVING, 22, true, "Sq'irk Juice", 5)
        ), true, List.of("In Aid of the Myreque"), 2, Difficulty.EXPERIENCED, "Myreque"));

        quests.add(new QuestRequirement("Mourning's End Part I", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 60, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.RANGED, 60, false, null, 0)
        ), true, List.of("Roving Elves"), 2, Difficulty.EXPERIENCED, "Elf"));

        quests.add(new QuestRequirement("Devious Minds", List.of(
                new QuestSkillRequirement(Skill.SMITHING, 65, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.RUNECRAFT, 50, false, null, 0),
                new QuestSkillRequirement(Skill.FLETCHING, 50, true, "Spicy Stew", 5)
        ), true, List.of("Wanted!", "Troll Stronghold"), 1, Difficulty.EXPERIENCED, "Standalone"));

        quests.add(new QuestRequirement("The Fremennik Isles", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 40, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.CONSTRUCTION, 20, false, null, 0),
                new QuestSkillRequirement(Skill.CRAFTING, 46, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.FLETCHING, 56, true, "Spicy Stew", 5)
        ), true, List.of("The Fremennik Trials"), 1, Difficulty.EXPERIENCED, "Fremennik"));

        quests.add(new QuestRequirement("Lunar Diplomacy", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 61, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.DEFENCE, 40, false, null, 0),
                new QuestSkillRequirement(Skill.FIREMAKING, 49, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.HERBLORE, 5, true, "Botanical Pie", 4),
                new QuestSkillRequirement(Skill.MAGIC, 65, false, null, 0),
                new QuestSkillRequirement(Skill.MINING, 60, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.WOODCUTTING, 55, false, null, 0)
        ), true, List.of("The Fremennik Trials", "The Hand in the Sand"), 2, Difficulty.EXPERIENCED, "Fremennik"));

        quests.add(new QuestRequirement("Dream Mentor", List.of(
                new QuestSkillRequirement(Skill.HERBLORE, 10, true, "Botanical Pie", 4),
                new QuestSkillRequirement(Skill.MAGIC, 65, false, null, 0)
        ), true, List.of("Lunar Diplomacy", "Eadgar's Ruse"), 2, Difficulty.EXPERIENCED, "Fremennik"));

        quests.add(new QuestRequirement("One Small Favour", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 36, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.CRAFTING, 25, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.HERBLORE, 18, true, "Botanical Pie", 4),
                new QuestSkillRequirement(Skill.SMITHING, 30, true, "Dwarven Stout (m)", 3, true)
        ), true, List.of("Rune Mysteries", "Shilo Village"), 2, Difficulty.EXPERIENCED, "Standalone"));

        quests.add(new QuestRequirement("Desert Treasure I", List.of(
                new QuestSkillRequirement(Skill.MAGIC, 50, false, null, 0),
                new QuestSkillRequirement(Skill.THIEVING, 53, true, "Sq'irk Juice", 5),
                new QuestSkillRequirement(Skill.FIREMAKING, 50, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.SLAYER, 10, false, null, 0)
        ), true, List.of("The Dig Site", "Temple of Ikov", "Icthlarin's Little Helper", "Waterfall Quest"), 3, Difficulty.EXPERIENCED, "Desert"));

        quests.add(new QuestRequirement("Fairytale II - Cure a Queen", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 40, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.FARMING, 49, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.HERBLORE, 57, true, "Botanical Pie", 4),
                new QuestSkillRequirement(Skill.THIEVING, 40, true, "Sq'irk Juice", 5)
        ), true, List.of("Fairytale I - Growing Pains"), 2, Difficulty.EXPERIENCED, "Zanaris"));

        quests.add(new QuestRequirement("Underground Pass", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 25, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.RANGED, 25, false, null, 0)
        ), true, List.of("Biohazard"), 5, Difficulty.EXPERIENCED, "Elf"));

        quests.add(new QuestRequirement("Regicide", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 56, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.CRAFTING, 10, true, "Mushroom Pie", 4)
        ), true, List.of("Underground Pass"), 3, Difficulty.EXPERIENCED, "Elf"));

        quests.add(new QuestRequirement("Roving Elves", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 56, true, "Summer Pie", 5)
        ), true, List.of("Regicide", "Waterfall Quest"), 1, Difficulty.EXPERIENCED, "Elf"));

        quests.add(new QuestRequirement("A Taste of Hope", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 45, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.ATTACK, 40, false, null, 0),
                new QuestSkillRequirement(Skill.CRAFTING, 48, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.HERBLORE, 40, true, "Botanical Pie", 4),
                new QuestSkillRequirement(Skill.SLAYER, 38, false, null, 0)
        ), true, List.of("Darkness of Hallowvale"), 2, Difficulty.EXPERIENCED, "Myreque"));

        quests.add(new QuestRequirement("Rum Deal", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 42, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.FARMING, 40, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.FISHING, 50, true, "Fish Pie", 3),
                new QuestSkillRequirement(Skill.PRAYER, 47, false, null, 0),
                new QuestSkillRequirement(Skill.SLAYER, 42, false, null, 0)
        ), true, List.of("Zogre Flesh Eaters"), 2, Difficulty.EXPERIENCED, "Pirate"));

        quests.add(new QuestRequirement("The Path of Glouphrie", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 56, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.RANGED, 47, false, null, 0),
                new QuestSkillRequirement(Skill.SLAYER, 56, false, null, 0),
                new QuestSkillRequirement(Skill.STRENGTH, 56, false, null, 0),
                new QuestSkillRequirement(Skill.THIEVING, 56, true, "Sq'irk Juice", 5)
        ), true, List.of("The Eyes of Glouphrie", "Waterfall Quest"), 2, Difficulty.EXPERIENCED, "Gnome"));

        // === INTERMEDIATE ===

        quests.add(new QuestRequirement("The Fremennik Trials", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 40, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.FLETCHING, 25, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.WOODCUTTING, 40, false, null, 0)
        ), true, List.of(), 3, Difficulty.INTERMEDIATE, "Fremennik"));

        quests.add(new QuestRequirement("Haunted Mine", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 15, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.CRAFTING, 35, true, "Mushroom Pie", 4)
        ), true, List.of("Priest in Peril"), 2, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("Shades of Mort'ton", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 20, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.FIREMAKING, 5, false, null, 0),
                new QuestSkillRequirement(Skill.HERBLORE, 15, true, "Botanical Pie", 4)
        ), true, List.of("Priest in Peril"), 3, Difficulty.INTERMEDIATE, "Myreque"));

        quests.add(new QuestRequirement("Tai Bwo Wannai Trio", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 15, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.COOKING, 30, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.FISHING, 5, false, null, 0)
        ), true, List.of("Jungle Potion"), 2, Difficulty.INTERMEDIATE, "Karamja"));

        quests.add(new QuestRequirement("Zogre Flesh Eaters", List.of(
                new QuestSkillRequirement(Skill.RANGED, 30, false, null, 0),
                new QuestSkillRequirement(Skill.SMITHING, 4, false, null, 0),
                new QuestSkillRequirement(Skill.HERBLORE, 8, true, "Botanical Pie", 4)
        ), true, List.of("Big Chompy Bird Hunting", "Jungle Potion"), 2, Difficulty.INTERMEDIATE, "Karamja"));

        quests.add(new QuestRequirement("The Dig Site", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 10, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.HERBLORE, 10, true, "Botanical Pie", 4),
                new QuestSkillRequirement(Skill.THIEVING, 25, true, "Sq'irk Juice", 5)
        ), true, List.of(), 2, Difficulty.INTERMEDIATE, "Desert"));

        quests.add(new QuestRequirement("Enakhra's Lament", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 50, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.FIREMAKING, 45, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.MAGIC, 39, false, null, 0),
                new QuestSkillRequirement(Skill.PRAYER, 43, false, null, 0)
        ), true, List.of(), 2, Difficulty.INTERMEDIATE, "Desert"));

        quests.add(new QuestRequirement("Icthlarin's Little Helper", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 10, true, "Summer Pie", 5)
        ), true, List.of("Gertrude's Cat"), 2, Difficulty.INTERMEDIATE, "Desert"));

   //     quests.add(new QuestRequirement("Dealing with Scabaras", List.of(
   //             new QuestSkillRequirement(Skill.AGILITY, 30, true, "Summer Pie", 5),
   //             new QuestSkillRequirement(Skill.CONSTRUCTION, 5, false, null, 0),
   //             new QuestSkillRequirement(Skill.MINING, 45, true, "Dwarven Stout (m)", 3, true),
   //             new QuestSkillRequirement(Skill.THIEVING, 30, true, "Sq'irk Juice", 5)
   //     ), true, List.of("Icthlarin's Little Helper"), 1, Difficulty.INTERMEDIATE, "Desert"));

        quests.add(new QuestRequirement("Contact!", List.of(
                new QuestSkillRequirement(Skill.THIEVING, 10, true, "Sq'irk Juice", 5)
        ), true, List.of("Icthlarin's Little Helper"), 1, Difficulty.INTERMEDIATE, "Desert"));

        quests.add(new QuestRequirement("Ratcatchers", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 40, true, "Summer Pie", 5)
        ), true, List.of("Icthlarin's Little Helper"), 2, Difficulty.INTERMEDIATE, "Desert"));

        quests.add(new QuestRequirement("Spirits of the Elid", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 37, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.MINING, 37, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.RANGED, 37, false, null, 0),
                new QuestSkillRequirement(Skill.THIEVING, 37, true, "Sq'irk Juice", 5)
        ), true, List.of(), 2, Difficulty.INTERMEDIATE, "Desert"));

        quests.add(new QuestRequirement("Fairytale I - Growing Pains", List.of(
                new QuestSkillRequirement(Skill.FARMING, 17, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.HERBLORE, 57, true, "Botanical Pie", 4)
        ), true, List.of("Lost City", "Nature Spirit"), 2, Difficulty.INTERMEDIATE, "Zanaris"));

        quests.add(new QuestRequirement("In Aid of the Myreque", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 25, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.MAGIC, 7, false, null, 0),
                new QuestSkillRequirement(Skill.MINING, 15, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.SMITHING, 20, true, "Dwarven Stout (m)", 3, true)
        ), true, List.of("In Search of the Myreque"), 2, Difficulty.INTERMEDIATE, "Myreque"));

        quests.add(new QuestRequirement("In Search of the Myreque", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 25, true, "Summer Pie", 5)
        ), true, List.of(), 2, Difficulty.INTERMEDIATE, "Myreque"));

        quests.add(new QuestRequirement("Holy Grail", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 30, false, null, 0)
        ), true, List.of("Merlin's Crystal"), 2, Difficulty.INTERMEDIATE, "Camelot"));

        quests.add(new QuestRequirement("The Giant Dwarf", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 12, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.FIREMAKING, 16, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.MAGIC, 33, false, null, 0),
                new QuestSkillRequirement(Skill.THIEVING, 14, true, "Sq'irk Juice", 5)
        ), true, List.of(), 2, Difficulty.INTERMEDIATE, "Dwarf"));

        quests.add(new QuestRequirement("Forgettable Tale...", List.of(
                new QuestSkillRequirement(Skill.COOKING, 22, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.FARMING, 17, true, "Spicy Stew", 5)
        ), true, List.of("The Giant Dwarf", "Fishing Contest"), 2, Difficulty.INTERMEDIATE, "Dwarf"));

        quests.add(new QuestRequirement("Between a Rock...", List.of(
                new QuestSkillRequirement(Skill.DEFENCE, 30, false, null, 0),
                new QuestSkillRequirement(Skill.MINING, 40, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.SMITHING, 50, true, "Dwarven Stout (m)", 3, true)
        ), true, List.of("Dwarf Cannon", "Fishing Contest"), 2, Difficulty.INTERMEDIATE, "Dwarf"));

        quests.add(new QuestRequirement("Elemental Workshop II", List.of(
                new QuestSkillRequirement(Skill.MAGIC, 20, false, null, 0),
                new QuestSkillRequirement(Skill.MINING, 30, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.SMITHING, 30, true, "Dwarven Stout (m)", 3, true)
        ), true, List.of("Elemental Workshop I"), 1, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("The Slug Menace", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 30, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.RUNECRAFT, 30, false, null, 0),
                new QuestSkillRequirement(Skill.THIEVING, 30, true, "Sq'irk Juice", 5)
        ), true, List.of("Wanted!"), 1, Difficulty.INTERMEDIATE, "Camelot"));

        quests.add(new QuestRequirement("Throne of Miscellania", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 40, true, "Summer Pie", 5)
        ), true, List.of("Heroes' Quest", "The Fremennik Trials"), 1, Difficulty.INTERMEDIATE, "Fremennik"));

        quests.add(new QuestRequirement("Royal Trouble", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 40, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.SLAYER, 40, false, null, 0)
        ), true, List.of("Throne of Miscellania"), 1, Difficulty.INTERMEDIATE, "Fremennik"));

        quests.add(new QuestRequirement("The Eyes of Glouphrie", List.of(
                new QuestSkillRequirement(Skill.CONSTRUCTION, 5, false, null, 0),
                new QuestSkillRequirement(Skill.MAGIC, 46, false, null, 0),
                new QuestSkillRequirement(Skill.RUNECRAFT, 45, false, null, 0)
        ), true, List.of("The Grand Tree"), 2, Difficulty.INTERMEDIATE, "Gnome"));

        quests.add(new QuestRequirement("Wanted!", List.of(
                new QuestSkillRequirement(Skill.SLAYER, 32, false, null, 0)
        ), true, List.of("Recruitment Drive", "The Slug Menace"), 1, Difficulty.INTERMEDIATE, "Camelot"));

        quests.add(new QuestRequirement("Recruitment Drive", List.of(
                new QuestSkillRequirement(Skill.PRAYER, 20, false, null, 0)
        ), true, List.of("Black Knights' Fortress", "Doric's Quest"), 1, Difficulty.INTERMEDIATE, "Camelot"));

        quests.add(new QuestRequirement("Troll Romance", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 28, true, "Summer Pie", 5)
        ), true, List.of("Troll Stronghold"), 2, Difficulty.INTERMEDIATE, "Troll"));

        quests.add(new QuestRequirement("Eadgar's Ruse", List.of(
                new QuestSkillRequirement(Skill.HERBLORE, 31, true, "Botanical Pie", 4)
        ), true, List.of("Troll Stronghold"), 1, Difficulty.INTERMEDIATE, "Troll"));

        quests.add(new QuestRequirement("My Arm's Big Adventure", List.of(
                new QuestSkillRequirement(Skill.FARMING, 29, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.WOODCUTTING, 10, false, null, 0)
        ), true, List.of("Eadgar's Ruse", "Jungle Potion"), 1, Difficulty.INTERMEDIATE, "Troll"));

        quests.add(new QuestRequirement("Rag and Bone Man II", List.of(
                new QuestSkillRequirement(Skill.SLAYER, 40, false, null, 0),
                new QuestSkillRequirement(Skill.SMITHING, 40, true, "Dwarven Stout (m)", 3, true)
        ), true, List.of("Rag and Bone Man I"), 1, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("Watchtower", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 15, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.MAGIC, 15, false, null, 0),
                new QuestSkillRequirement(Skill.MINING, 40, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.HERBLORE, 14, true, "Botanical Pie", 4),
                new QuestSkillRequirement(Skill.THIEVING, 15, true, "Sq'irk Juice", 5)
        ), true, List.of(), 4, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("Land of the Goblins", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 40, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.FISHING, 40, true, "Fish Pie", 3),
                new QuestSkillRequirement(Skill.HERBLORE, 38, true, "Botanical Pie", 4),
                new QuestSkillRequirement(Skill.THIEVING, 45, true, "Sq'irk Juice", 5)
        ), true, List.of("Goblin Diplomacy", "Waterfall Quest"), 1, Difficulty.INTERMEDIATE, "Goblin"));

        quests.add(new QuestRequirement("Temple of Ikov", List.of(
                new QuestSkillRequirement(Skill.RANGED, 40, false, null, 0),
                new QuestSkillRequirement(Skill.THIEVING, 42, true, "Sq'irk Juice", 5)
        ), true, List.of(), 1, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("Making History", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 10, true, "Mushroom Pie", 4)
        ), true, List.of("Priest in Peril", "The Restless Ghost"), 3, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("Enlightened Journey", List.of(
                new QuestSkillRequirement(Skill.FARMING, 30, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.FIREMAKING, 20, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.CRAFTING, 36, true, "Mushroom Pie", 4)
        ), true, List.of(), 1, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("Mountain Daughter", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 20, true, "Summer Pie", 5)
        ), true, List.of(), 2, Difficulty.INTERMEDIATE, "Fremennik"));

        quests.add(new QuestRequirement("Curse of the Empty Lord", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 31, true, "Summer Pie", 5)
        ), true, List.of(), 1, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("Observatory Quest", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 10, true, "Mushroom Pie", 4)
        ), true, List.of(), 2, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("Ghosts Ahoy", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 25, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.COOKING, 20, true, "Spicy Stew", 5)
        ), true, List.of("Priest in Peril"), 2, Difficulty.INTERMEDIATE, "Myreque"));

        quests.add(new QuestRequirement("Shilo Village", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 32, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.CRAFTING, 20, true, "Mushroom Pie", 4)
        ), true, List.of("Jungle Potion"), 2, Difficulty.INTERMEDIATE, "Karamja"));

        quests.add(new QuestRequirement("The Feud", List.of(
                new QuestSkillRequirement(Skill.THIEVING, 30, true, "Sq'irk Juice", 5)
        ), true, List.of(), 2, Difficulty.INTERMEDIATE, "Desert"));


        quests.add(new QuestRequirement("Skippy and the Mogres", List.of(
                new QuestSkillRequirement(Skill.FISHING, 20, false, null, 0)
        ), true, List.of(), 1, Difficulty.INTERMEDIATE, "Standalone"));

        // === NOVICE ===

        quests.add(new QuestRequirement("Priest in Peril", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), true, List.of(), 1, Difficulty.NOVICE, "Myreque"));

        quests.add(new QuestRequirement("Nature Spirit", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 18, true, "Mushroom Pie", 4)
        ), true, List.of("Priest in Peril", "The Restless Ghost"), 2, Difficulty.NOVICE, "Myreque"));

        quests.add(new QuestRequirement("Merlin's Crystal", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), true, List.of(), 6, Difficulty.NOVICE, "Camelot"));

        quests.add(new QuestRequirement("Fishing Contest", List.of(
                new QuestSkillRequirement(Skill.FISHING, 10, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Dwarf"));

        quests.add(new QuestRequirement("Elemental Workshop I", List.of(
                new QuestSkillRequirement(Skill.MINING, 20, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.SMITHING, 20, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.CRAFTING, 20, true, "Mushroom Pie", 4)
        ), true, List.of(), 1, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Troll Stronghold", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 15, true, "Summer Pie", 5)
        ), true, List.of("Death Plateau"), 1, Difficulty.NOVICE, "Troll"));

        quests.add(new QuestRequirement("Death Plateau", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), true, List.of(), 1, Difficulty.NOVICE, "Troll"));

        quests.add(new QuestRequirement("Jungle Potion", List.of(
                new QuestSkillRequirement(Skill.HERBLORE, 3, false, null, 0)
        ), true, List.of(), 1, Difficulty.NOVICE, "Karamja"));

        quests.add(new QuestRequirement("Prince Ali Rescue", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 1, false, null, 0)
        ), false, List.of(), 3, Difficulty.NOVICE, "Desert"));

        quests.add(new QuestRequirement("Vampyre Slayer", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 3, false, null, 0)
        ), false, List.of(), 3, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Romeo & Juliet", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 1, false, null, 0)
        ), false, List.of(), 5, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("The Restless Ghost", List.of(
                new QuestSkillRequirement(Skill.PRAYER, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Rune Mysteries", List.of(
                new QuestSkillRequirement(Skill.RUNECRAFT, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Learning the Ropes", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Cook's Assistant", List.of(
                new QuestSkillRequirement(Skill.COOKING, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Demon Slayer", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 3, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Sheep Shearer", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Shield of Arrav", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Ernest the Chicken", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 4, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Imp Catcher", List.of(
                new QuestSkillRequirement(Skill.MAGIC, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Doric's Quest", List.of(
                new QuestSkillRequirement(Skill.MINING, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Black Knights' Fortress", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 3, Difficulty.NOVICE, "Camelot"));

        quests.add(new QuestRequirement("Witch's Potion", List.of(
                new QuestSkillRequirement(Skill.MAGIC, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("The Knight's Sword", List.of(
                new QuestSkillRequirement(Skill.MINING, 10, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Camelot"));

        quests.add(new QuestRequirement("Goblin Diplomacy", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 5, Difficulty.NOVICE, "Goblin"));

        quests.add(new QuestRequirement("Dragon Slayer I", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 2, Difficulty.NOVICE, "Dragon Slayer"));

        quests.add(new QuestRequirement("Misthalin Mystery", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("The Corsair Curse", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("X Marks the Spot", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Below Ice Mountain", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("The Ides of Milk", List.of(
                new QuestSkillRequirement(Skill.COOKING, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Standalone"));
// ============================================================
// MISSING QUESTS — paste these blocks into QuestRequirementDatabase.java
// inside the getQuests() method, grouped by difficulty.
// ============================================================

// ===== GRANDMASTER (add to GRANDMASTER section) =====

        // (None newly missing from Grandmaster — your existing ones cover it)

// ===== MASTER (add to MASTER section) =====

        quests.add(new QuestRequirement("Monkey Madness I", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 1, false, null, 0)
        ), true, List.of("Tree Gnome Village", "The Grand Tree"), 3, Difficulty.MASTER, "Gnome"));

        quests.add(new QuestRequirement("The Great Brain Robbery", List.of(
                new QuestSkillRequirement(Skill.PRAYER, 50, false, null, 0),
                new QuestSkillRequirement(Skill.CRAFTING, 16, true, "Mushroom Pie", 4)
        ), true, List.of("Cabin Fever", "Creature of Fenkenstrain"), 2, Difficulty.MASTER, "Pirate"));

        quests.add(new QuestRequirement("King's Ransom", List.of(
                new QuestSkillRequirement(Skill.MAGIC, 45, false, null, 0),
                new QuestSkillRequirement(Skill.DEFENCE, 65, false, null, 0)
        ), true, List.of("Black Knights' Fortress", "Holy Grail", "Murder Mystery", "One Small Favour"), 1, Difficulty.MASTER, "Camelot"));

        quests.add(new QuestRequirement("Making Friends with My Arm", List.of(
                new QuestSkillRequirement(Skill.MINING, 72, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.FIREMAKING, 66, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.CONSTRUCTION, 35, false, null, 0),
                new QuestSkillRequirement(Skill.AGILITY, 68, true, "Summer Pie", 5)
        ), true, List.of("My Arm's Big Adventure", "Swan Song", "The Fremennik Exiles", "Cold War"), 2, Difficulty.MASTER, "Troll"));

        quests.add(new QuestRequirement("The Curse of Arrav", List.of(
                new QuestSkillRequirement(Skill.RANGED, 62, false, null, 0),
                new QuestSkillRequirement(Skill.AGILITY, 61, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.THIEVING, 61, true, "Sq'irk Juice", 5),
                new QuestSkillRequirement(Skill.STRENGTH, 61, false, null, 0),
                new QuestSkillRequirement(Skill.MINING, 61, true, "Dwarven Stout (m)", 3, true)
        ), true, List.of("Defender of Varrock", "Troll Romance"), 2, Difficulty.MASTER, "Mahjarrat"));

// ===== EXPERIENCED (add to EXPERIENCED section) =====

        quests.add(new QuestRequirement("Heroes' Quest", List.of(
                new QuestSkillRequirement(Skill.COOKING, 53, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.FISHING, 53, true, "Fish Pie", 3),
                new QuestSkillRequirement(Skill.HERBLORE, 25, true, "Botanical Pie", 4),
                new QuestSkillRequirement(Skill.MINING, 50, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.AGILITY, 50, true, "Summer Pie", 5)
        ), true, List.of("Shield of Arrav", "Lost City", "Merlin's Crystal", "Dragon Slayer I", "Druidic Ritual"), 1, Difficulty.EXPERIENCED, "Standalone"));

        quests.add(new QuestRequirement("Family Crest", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 40, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.MINING, 40, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.SMITHING, 40, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.MAGIC, 59, false, null, 0)
        ), true, List.of(), 1, Difficulty.EXPERIENCED, "Standalone"));

        quests.add(new QuestRequirement("Animal Magnetism", List.of(
                new QuestSkillRequirement(Skill.SLAYER, 18, false, null, 0),
                new QuestSkillRequirement(Skill.CRAFTING, 19, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.RANGED, 30, false, null, 0),
                new QuestSkillRequirement(Skill.WOODCUTTING, 35, false, null, 0)
        ), true, List.of("Icthlarin's Little Helper", "Priest in Peril", "The Restless Ghost"), 1, Difficulty.EXPERIENCED, "Standalone"));

        quests.add(new QuestRequirement("Horror from the Deep", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 35, true, "Summer Pie", 5)
        ), true, List.of("Alfred Grimhand's Barcrawl"), 1, Difficulty.EXPERIENCED, "Standalone"));

        quests.add(new QuestRequirement("Bone Voyage", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 1, false, null, 0)
        ), true, List.of("The Dig Site"), 2, Difficulty.EXPERIENCED, "Standalone"));

        quests.add(new QuestRequirement("What Lies Below", List.of(
                new QuestSkillRequirement(Skill.RUNECRAFT, 35, false, null, 0),
                new QuestSkillRequirement(Skill.MINING, 30, true, "Dwarven Stout (m)", 3, true)
        ), true, List.of("Rune Mysteries"), 1, Difficulty.EXPERIENCED, "Standalone"));

        quests.add(new QuestRequirement("Biohazard", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), true, List.of("Plague City"), 3, Difficulty.EXPERIENCED, "Elf"));

        quests.add(new QuestRequirement("Cold War", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 30, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.CONSTRUCTION, 34, false, null, 0),
                new QuestSkillRequirement(Skill.CRAFTING, 30, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.HUNTER, 10, true, "Hunter Potion", 4),
                new QuestSkillRequirement(Skill.THIEVING, 15, true, "Sq'irk Juice", 5)
        ), true, List.of(), 1, Difficulty.EXPERIENCED, "Standalone"));

        quests.add(new QuestRequirement("Sea Slug", List.of(
                new QuestSkillRequirement(Skill.FIREMAKING, 30, false, null, 0)
        ), true, List.of(), 1, Difficulty.EXPERIENCED, "Camelot"));

        quests.add(new QuestRequirement("Throne of Miscellania", List.of(  // override your existing one if QP/prereqs differ
                new QuestSkillRequirement(Skill.AGILITY, 40, true, "Summer Pie", 5)
        ), true, List.of("Heroes' Quest", "The Fremennik Trials"), 1, Difficulty.EXPERIENCED, "Fremennik"));

// ===== INTERMEDIATE (add to INTERMEDIATE section) =====

        quests.add(new QuestRequirement("Druidic Ritual", List.of(
                new QuestSkillRequirement(Skill.HERBLORE, 1, false, null, 0)
        ), false, List.of(), 4, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Lost City", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 31, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.WOODCUTTING, 36, false, null, 0)
        ), true, List.of(), 3, Difficulty.INTERMEDIATE, "Zanaris"));

        quests.add(new QuestRequirement("Witch's House", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 4, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("Scorpion Catcher", List.of(
                new QuestSkillRequirement(Skill.PRAYER, 31, false, null, 0)
        ), true, List.of(), 1, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("Tribal Totem", List.of(
                new QuestSkillRequirement(Skill.THIEVING, 21, true, "Sq'irk Juice", 5)
        ), true, List.of(), 1, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("Monk's Friend", List.of(
                new QuestSkillRequirement(Skill.WOODCUTTING, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Clock Tower", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Tree Gnome Village", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 2, Difficulty.INTERMEDIATE, "Gnome"));

        quests.add(new QuestRequirement("Fight Arena", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 2, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("Hazeel Cult", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Mahjarrat"));

        quests.add(new QuestRequirement("Sheep Herder", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 4, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Plague City", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Elf"));

        quests.add(new QuestRequirement("Waterfall Quest", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("The Grand Tree", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 25, true, "Summer Pie", 5)
        ), true, List.of("Tree Gnome Village"), 5, Difficulty.INTERMEDIATE, "Gnome"));

        quests.add(new QuestRequirement("Big Chompy Bird Hunting", List.of(
                new QuestSkillRequirement(Skill.RANGED, 30, false, null, 0),
                new QuestSkillRequirement(Skill.COOKING, 30, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.FLETCHING, 5, false, null, 0)
        ), true, List.of(), 2, Difficulty.INTERMEDIATE, "Karamja"));

        quests.add(new QuestRequirement("Dwarf Cannon", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Dwarf"));

        quests.add(new QuestRequirement("Murder Mystery", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 1, false, null, 0)
        ), false, List.of(), 3, Difficulty.NOVICE, "Camelot"));

        quests.add(new QuestRequirement("Gertrude's Cat", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Creature of Fenkenstrain", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 20, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.THIEVING, 25, true, "Sq'irk Juice", 5)
        ), true, List.of("Priest in Peril", "The Restless Ghost"), 2, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("The Lost Tribe", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 13, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.MINING, 17, true, "Dwarven Stout (m)", 3, true),
                new QuestSkillRequirement(Skill.THIEVING, 13, true, "Sq'irk Juice", 5)
        ), true, List.of("Goblin Diplomacy"), 1, Difficulty.INTERMEDIATE, "Goblin"));

        quests.add(new QuestRequirement("Death to the Dorgeshuun", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 23, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.THIEVING, 23, true, "Sq'irk Juice", 5)
        ), true, List.of("The Lost Tribe"), 1, Difficulty.INTERMEDIATE, "Goblin"));

        quests.add(new QuestRequirement("Tears of Guthix", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 20, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.FIREMAKING, 49, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.MINING, 20, true, "Dwarven Stout (m)", 3, true)
        ), true, List.of("Lumbridge & Draynor Diary (?)"), 1, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("The Tourist Trap", List.of(
                new QuestSkillRequirement(Skill.FLETCHING, 10, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.SMITHING, 20, true, "Dwarven Stout (m)", 3, true)
        ), true, List.of(), 2, Difficulty.INTERMEDIATE, "Desert"));

        quests.add(new QuestRequirement("A Soul's Bane", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("Rag and Bone Man I", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Garden of Tranquillity", List.of(
                new QuestSkillRequirement(Skill.FARMING, 25, true, "Spicy Stew", 5)
        ), true, List.of("Creature of Fenkenstrain"), 2, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("A Tail of Two Cats", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 1, false, null, 0)
        ), false, List.of("Ichlarin's Little Helper"), 2, Difficulty.INTERMEDIATE, "Kharidian"));

        quests.add(new QuestRequirement("Shadow of the Storm", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 30, true, "Mushroom Pie", 4)
        ), true, List.of("Demon Slayer"), 1, Difficulty.INTERMEDIATE, "Demon Slayer"));

        quests.add(new QuestRequirement("The Golem", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 20, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.THIEVING, 25, true, "Sq'irk Juice", 5)
        ), true, List.of(), 1, Difficulty.INTERMEDIATE, "Desert"));

        quests.add(new QuestRequirement("The Hand in the Sand", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 49, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.THIEVING, 17, true, "Sq'irk Juice", 5)
        ), true, List.of(), 1, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("Another Slice of H.A.M.", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 15, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.ATTACK, 15, false, null, 0)
        ), true, List.of("Death to the Dorgeshuun", "The Dig Site"), 1, Difficulty.INTERMEDIATE, "Goblin"));


// ===== NOVICE (add to NOVICE section) =====

        quests.add(new QuestRequirement("Pirate's Treasure", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 2, Difficulty.NOVICE, "Standalone"));

        // NOTE: "Druidic Ritual" is a Novice quest — see block above near INTERMEDIATE section

// ============================================================
// NEWER / KOUREND QUESTS
// ============================================================

// ===== MASTER =====

        quests.add(new QuestRequirement("Secrets of the North", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 56, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.HUNTER, 56, true, "Hunter Potion", 4),
                new QuestSkillRequirement(Skill.SLAYER, 55, false, null, 0),
                new QuestSkillRequirement(Skill.PRAYER, 43, false, null, 0)
        ), true, List.of("Desert Treasure I", "The Fremennik Exiles"), 2, Difficulty.MASTER, "Mahjarrat"));

// ===== EXPERIENCED =====

        quests.add(new QuestRequirement("Beneath Cursed Sands", List.of(
                new QuestSkillRequirement(Skill.FIREMAKING, 55, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.CRAFTING, 55, true, "Mushroom Pie", 4)
        ), true, List.of("Contact!", "Icthlarin's Little Helper"), 2, Difficulty.EXPERIENCED, "Desert"));

        quests.add(new QuestRequirement("Children of the Sun", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Varlamore"));

        quests.add(new QuestRequirement("Twilight's Promise", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 32, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.HUNTER, 29, true, "Hunter Potion", 4)
        ), true, List.of("Children of the Sun"), 2, Difficulty.INTERMEDIATE, "Varlamore"));

        quests.add(new QuestRequirement("Perilous Moons", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 48, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.SLAYER, 48, false, null, 0),
                new QuestSkillRequirement(Skill.FARMING, 42, true, "Spicy Stew", 5)
        ), true, List.of("Children of the Sun"), 2, Difficulty.INTERMEDIATE, "Varlamore"));

        quests.add(new QuestRequirement("The Garden of Death", List.of(
                new QuestSkillRequirement(Skill.FARMING, 35, true, "Spicy Stew", 5),
                new QuestSkillRequirement(Skill.HUNTER, 31, true, "Hunter Potion", 4)
        ), true, List.of("Twilight's Promise"), 1, Difficulty.INTERMEDIATE, "Varlamore"));

        quests.add(new QuestRequirement("The Heart of Darkness", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 50, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.HERBLORE, 48, true, "Botanical Pie", 4),
                new QuestSkillRequirement(Skill.THIEVING, 46, true, "Sq'irk Juice", 5),
                new QuestSkillRequirement(Skill.CRAFTING, 38, true, "Mushroom Pie", 4)
        ), true, List.of("The Garden of Death", "Perilous Moons"), 2, Difficulty.EXPERIENCED, "Varlamore"));

        quests.add(new QuestRequirement("Death on the Isle", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 58, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.THIEVING, 52, true, "Sq'irk Juice", 5),
                new QuestSkillRequirement(Skill.SLAYER, 46, false, null, 0)
        ), true, List.of("Twilight's Promise", "The Fremennik Exiles"), 2, Difficulty.EXPERIENCED, "Fremennik"));

// ===== KOUREND series =====

        quests.add(new QuestRequirement("Client of Kourend", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of(), 1, Difficulty.NOVICE, "Kourend"));

        quests.add(new QuestRequirement("The Depths of Despair", List.of(
                new QuestSkillRequirement(Skill.AGILITY, 18, true, "Summer Pie", 5)
        ), true, List.of("Client of Kourend"), 1, Difficulty.NOVICE, "Kourend"));

        quests.add(new QuestRequirement("The Forsaken Tower", List.of(
                new QuestSkillRequirement(Skill.ATTACK, 1, false, null, 0)
        ), false, List.of("Client of Kourend"), 1, Difficulty.NOVICE, "Kourend"));

        quests.add(new QuestRequirement("The Ascent of Arceuus", List.of(
                new QuestSkillRequirement(Skill.HUNTER, 12, true, "Hunter Potion", 4)
        ), true, List.of("Client of Kourend"), 1, Difficulty.NOVICE, "Kourend"));

        quests.add(new QuestRequirement("Tale of the Righteous", List.of(
                new QuestSkillRequirement(Skill.STRENGTH, 16, false, null, 0),
                new QuestSkillRequirement(Skill.MINING, 10, true, "Dwarven Stout (m)", 3, true)
        ), true, List.of("Client of Kourend"), 1, Difficulty.NOVICE, "Kourend"));

        quests.add(new QuestRequirement("A Porcine of Interest", List.of(
                new QuestSkillRequirement(Skill.SLAYER, 8, false, null, 0)
        ), true, List.of(), 1, Difficulty.NOVICE, "Standalone"));

        quests.add(new QuestRequirement("Getting Ahead", List.of(
                new QuestSkillRequirement(Skill.CRAFTING, 30, true, "Mushroom Pie", 4),
                new QuestSkillRequirement(Skill.CONSTRUCTION, 26, false, null, 0)
        ), true, List.of(), 2, Difficulty.INTERMEDIATE, "Standalone"));

        quests.add(new QuestRequirement("Land of the Goblins", List.of(   // already in your DB — skip if duplicate
                new QuestSkillRequirement(Skill.AGILITY, 40, true, "Summer Pie", 5),
                new QuestSkillRequirement(Skill.FISHING, 40, true, "Fish Pie", 3),
                new QuestSkillRequirement(Skill.HERBLORE, 38, true, "Botanical Pie", 4),
                new QuestSkillRequirement(Skill.THIEVING, 45, true, "Sq'irk Juice", 5)
        ), true, List.of("Goblin Diplomacy", "Waterfall Quest"), 1, Difficulty.INTERMEDIATE, "Goblin"));
        return quests;
    }
}