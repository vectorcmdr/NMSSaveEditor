# No Man's Sky - Save Editor (vector_cmdr hotfix version)

<br>

## Introduction

No Man's Sky Save Editor is a java based save editor for No Man's Sky (duh! 😉).

This is a hotfix fork of the original (_now unmaintained?_) project by [goatfungus](https://github.com/goatfungus/NMSSaveEditor) that aims to keep the program up to date when possible.

_It is no longer contributing PRs back to the original due to it's current frozen state._

## Future Goals:

A modernized C# port is currently underway that will be **completely open source** and encourage the community to help keep it up to date as there are sadly little to no up-to-date open source save editors, only closed source or semi source available ones.

## Compatibility

Requirements:
- No Man's Sky (PC/Xbox/PS4)
- <a href="https://www.savewizard.net/" target="_blank">Save Wizard</a> (PS4 only)
- <a href="https://java.com/en/download/manual.jsp" target="_blank">Java Runtime Environment 8</a>

## Installation

### Grab the latest release

1. Head to the [releases page](https://github.com/vectorcmdr/NMSSaveEditor/releases) and grab the latest version.
2. Use the zip file to extract the program to a folder of your choice. Don't try to run it from within the zip folder!
3. Run the batch (bat) file (or jar file if the file extension is associated to java).
4. When the main window opens, locate the folder that your saves are in, and choose the most recent.

**_OR:_**

### Option 2 - Manual Zip Download

Grab a copy of the zip file from the repo directly as a "code download" or raw file the way you used to from the original repo.
Though going via the [releases page](https://github.com/vectorcmdr/NMSSaveEditor/releases) is more sensible...

## Features

- Easy to use UI.
  - Ability to customise Look & Feel (Light / Dark modes, inventory scaling)
- Change data values:
  - Currencies (Units, Nanites and Quicksilver)
  - Base stats for Exosuit, Multitools, Ships and Freighter (Health, Shield, Type, Class, Seed, etc.)
  - Squadron Wingmans (NPC Race, NPC Seed, Ship Type, Ship Seed, Pilot Rank, etc.)
  - Frigates Info and Stats
  - Companions / Pets (Name, Seed, Biome, Type, etc.)
  - Settlements (Population, Happiness, Production, Upkeep, Debt, etc.)
  - Milestones / Reputation
- Organize and manage inventories (Exosuit, Multitools, Ships, Freighter, Vehicles, and Base Storages)
  - Move items and technology around.
    - Drag & drop to move in the same inventory
    - Right-click menu to move to another inventory
  - Add items and technology.
    - CTRL + Drag & drop to clone same item / technology
    - Right-click menu to choose specific item / technology
  - View more information about each of the items in your inventory, such as in-game description, names, etc.
  - Repair, Recharge and Refill items / technologies (right-click menu)
  - Resize inventories (top button), Enable or Enable All additional slots (right-click menu)
  - Vanilla inventory limits (Cargo - 10x12 / Technology - 10x6 / Base Storage - 10x5) (**WARNING! You CAN go above this, but the game might BREAK!**)
- Edit the raw JSON in the save file (for advanced users only).
- Automatic backup and recovery of all saves (in case you screw up something).
- Backup/Restore of Base/Freighter Structures (Usable across different systems or saves)
- Delete or Export/Import Multitools, Ships and Frigates.
- Edit and toggle:
  - Known Technologies, Products, Words and Glyphs.
  - Your account data and unlocks.
- Currently supports Steam/GOG, PS4 (via Save Wizard), and MS Game Pass (Xbox).

## Acknowledgements & Contributions

This tool will always be released publicly for free just like the original!

If you would like to donate to me to say thanks for the updates, please head to my [Sponsor](https://github.com/sponsors/vectorcmdr) page or my [Ko-fi](https://ko-fi.com/vector_cmdr).


A massive thanks to [goatfungus for creating the original editor](https://github.com/goatfungus/NMSSaveEditor).

**The updates would not exist without it (obviously!).**

If you want to donate to him to say thank you, please head to his [Patreon](https://www.patreon.com/goatfungus)!