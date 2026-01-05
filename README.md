# ItemsAdderBetterCommands

A tiny addon for ItemsAdder that replaces scattered `/ia*` commands with a single, easy-to-remember `/itemsadder` command tree.
Non-breaking: all old `/ia...` commands remain available as aliases.

## Features
- Single, consistent `/itemsadder` command tree
- Preserves all legacy `/ia*` aliases for backward compatibility
- No configuration required - drop the jar and restart
- Small and focused: only reorganizes commands, not behavior

## Commands
```
/itemsadder debug block
/itemsadder debug cleancache
/itemsadder debug custommodeldata
/itemsadder debug liquid
/itemsadder debug sha1
/itemsadder debug tag
/itemsadder drop
/itemsadder durability
/itemsadder give
/itemsadder info
/itemsadder inventory
/itemsadder recipe
/itemsadder reload
/itemsadder remove
/itemsadder repair
```

## Quick install
1. Download the jar from the [Releases](https://github.com/PuppyTransGirl/ItemsAdderBetterCommands/releases/latest) page.
2. Put the jar in your server's `plugins` folder.
3. Restart the server.

## Usage examples
- Give an item: `/itemsadder give <player> <item> [amount]`
- Reload ItemsAdder-related data: `/itemsadder reload`
- Run debug checks: `/itemsadder debug custommodeldata`

## Requirements & notes
- Requires ItemsAdder to be installed (this addon only changes command structure).

## Feedback & issues
Report bugs or request small tweaks on the repository Issues page: https://github.com/PuppyTransGirl/ItemsAdderBetterCommands/issues

---

<img width="960" height="150" alt="itemsadder_addon" src="https://github.com/user-attachments/assets/10854c7e-a437-4a30-ba43-bfe53abdfd28" />
