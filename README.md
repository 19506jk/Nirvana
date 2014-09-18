# Current Progress

- Improve Class System
    - Add range classes (i.e Mages, Archer)
    - Add area skills
- Implement Inventory System
    - Add some in-game items
- Floor rework
- Race rework
- **GUI** (see [graphics_infra branch](https://github.com/19506jk/CC3K-new/tree/graphics_infra))
   - Resource scavenging
   - Map Scrolling
   - Character Moving Animation
   - Implement new command system



#Change Log

### Version 0.1.3
- Add max HP/MP for player
- Added inventory and potion features
- Added equipment system
- Began to implement GUI


### Version 0.1.2
- Floor system rework with basic architecture finished
- Removed passive skills from class system
- Added inn system in town for recovering player's HP / MP
- Added MP system for player
- Fixed a bug in Swordman's concentrate skill


### Version 0.1.1
- Added Acolyte class and associated skills
- Renamed Knight to Swordsman
- Added skills for Rogue
- Updated job info display
- Disabled potion system (need to be reworked)
- Updated variable names to follow camel-case
- Rework player buff system
- Added debuff system for monsters
- Added game version to welcome menu


### Version 0.1.0
- Basic game system
- Added player class system and Knight, Rogue class
- Added player attribute system
   


# Instructions

### Move
- no = move north
- ea = move east
- so = move south
- we = move west
- nw = move northwest
- ne = move northeast
- se = move southeast
- sw = move southwest

### Attack
- input 'a' followed by direction <br>
- Example: a no = attack north

### Town
- input "town" to visit the town and modify player's attributes

### Class
- input 'job' to see detail information about the current class<br>
- input 's1' followed by direction to cast skill 1, etc<br>

### Equips
- input 'equip' to use the equipment system for player<br>

### Potions
- input 'inv' to use the inventory system for player<br>
- On inventory screen, input 1,2,3 to use the corresponding potion. Input 87 for extra potions.
