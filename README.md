# Angry Birds-Inspired Game

## Overview
This game is inspired by the **Angry Birds** series and implemented using **Object-Oriented Programming (OOP)** principles with a focus on good coding practices. It features basic gameplay mechanics, serialization for saving game states, and engaging components like multiple levels, birds, pigs, and destructible structures.

### Basic Gameplay:
- Finite birds are available to shoot at a structure containing pigs.
- Drag and aim birds from the catapult. The trajectory is determined by the angle and speed.
- Structures are made of materials like wood, glass, and steel, which break based on hits.
- Collapsing structures deal cascading hits to blocks or pigs below.
- **Win a level** by eliminating all pigs. **Lose** if you run out of birds.
- **Levels:** 3 distinct levels with unique structures, pigs, and birds.

---

## Game Features
### Levels
Play through multiple levels with varying difficulty.

### Game Mechanics
Launch birds with realistic physics to defeat the pigs.

### Menus
- **Front Page:** Displays a button to start the game, which then initiates a loading sequence.
- **How to Play:** Learn the basic game mechanics.
- **Pause Menu:** Pause the game, resume, or exit at any point.
- **Main Screen:** Options for "Play," "How to Play," and "Exit."
- **Winning/Losing Screens:** Displayed when a player wins or loses a level.
- **Level Select:** Choose which level to play.

### JUnit Testing
Includes unit tests to ensure functionality, readability, and correctness of code components.

### Serialization
Features to save and load the game state.

---

## How to Play
1. **Start the Game:** Launch the game from the start menu.
2. **Select a Level:** Pick a level to start playing.
3. **Launch Birds:** Use the slingshot to launch birds at the pigs.
4. **Complete Levels:** Win levels by defeating all pigs, or lose if you run out of birds.

---

## Setup & Installation
1. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA or Eclipse).
2. Ensure **Java 8 or above** is installed on your system.
3. Compile and run the project.

---

## Team Members & Contributions

### **1. Avdhesh Kumar Verma (2023158)**
**Role:** Game levels and core functionality.

**Contributions:**
- Level 1, Level 2, Level 3.
- Birds: Redbird, Bluebird, Yellowbird.
- Structures: Woodenbox, Glassbox, Steelbox.
- Level select menu.
- Main game logic.
- Main screen interface.
- JUnit Testing.

---

### **2. Divyank (2023206)**
**Role:** User interface development and game state management.

**Contributions:**
- Winning screen and losing screen.
- Pig types: Pig1, Pig2, Pig3.
- Block collisions and hits.
- Serialization implementation.
- Pause menu.
- Front page interface.
- "How to Play" section.
