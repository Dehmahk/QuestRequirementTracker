# QuestRequirementTracker
# Quest Requirement Tracker

A RuneLite plugin that tracks skill requirements for every OSRS quest and shows exactly what you need to complete them — with live level comparison, boost suggestions, quest filters, and more.

---

## Features

### 📋 Quest Overview
- Displays all quests you haven't completed yet, sorted by how many levels you still need
- Shows a **live level comparison** against your current stats when logged in
- Each quest header shows:
  - Quest name with levels needed at a glance
  - Questline subtitle (e.g. *Myreque Questline*, *Desert Questline*)
  - Difficulty badge (`NOV` / `INT` / `EXP` / `MAS` / `GM`) with colour coding
  - Quest point value

### 🔍 Filtering & Sorting
- **Search bar** — instantly filter quests by name
- **Skill filter** — show only quests that require a specific skill
- **Questline filter** — filter by questline (Elf, Fremennik, Kourend, Myreque, etc.)
- **Difficulty filter** — filter by Novice, Intermediate, Experienced, Master, or Grandmaster
- **Sort options:**
  - Levels needed (default)
  - Alphabetical
  - Requirements met %
  - Difficulty
  - Questline
- **Miniquest support** — toggle miniquests via the questline filter

### ✅ Requirement Details
Expand any quest to see a full breakdown:
- Each skill requirement with your current level vs. required level
- ✓ / ✗ indicators for met and unmet requirements
- **Boost suggestions** — shows which items can boost you over the threshold and whether you can boost right now
- **Ironman mode** — flags boost items that are unobtainable on ironman accounts
- **Quest prerequisites** — lists required quests with ✓ / ✗ completion status
- **"Ready to complete!"** indicator when all requirements are met

### 📊 Progress Tracking
- Header bar showing **quests remaining** and **quests ready to start**
- **QP tracker** — earned quest points vs. earnable quest points
- **Overall progress bar** — percentage of total quest points earned
- **Colour-coded headers** — green (ready), yellow (close), orange (moderate), red (far)

### 🔧 Quality of Life
- **Pin quests** — pin priority quests to the top of the list
- **Wiki button** — `[W]` opens the OSRS Wiki page for any quest in your browser
- **Collapse All** button — collapse all expanded quests at once
- **Completion log** — tracks when you completed each quest with timestamps
- **Compact mode** — hides progress bars for a cleaner, denser view
- **Persistent state** — pinned quests, expanded quests, and completion log are saved between sessions
- **Fast UI updates** — filter and sort changes are instant; only Refresh hits the game client

### ⚙️ Config Options
- Hide completable quests (all reqs met)
- Show only boostable quests
- Hide members quests
- Hide free-to-play quests
- Hide quests with unmet prerequisites
- Ironman mode (flags unobtainable boosts)
- Compact mode

---

## Screenshots
<img width="225" height="639" alt="image" src="https://github.com/user-attachments/assets/6789a705-5f3a-4fa3-b013-a3ffd69a612e" />


<img width="216" height="370" alt="image" src="https://github.com/user-attachments/assets/f155e714-8d83-4006-9eca-afddcba2bfc8" />

<img width="240" height="679" alt="image" src="https://github.com/user-attachments/assets/7244778e-a854-4e40-a9b1-60e75a0afb87" />



---

## Installation

Install via the RuneLite **Plugin Hub** — search for **Quest Requirement Tracker**.

---
## Support
https://discord.gg/AJkV59UZNS

---
## Credits

Developed by **Dehmahk**. Quest data sourced from the [OSRS Wiki](https://oldschool.runescape.wiki).
