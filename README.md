Yoyo - A JavaFX Stick Hero Game Overview
Yoyo is a JavaFX-based desktop game inspired by the popular Stick Hero mobile game. The objective is to guide the hero across various platforms using a dynamically extendable stick. This game challenges players' timing and precision, offering an engaging and interactive gaming experience.
Project Structure
The project is structured into multiple classes, each with a specific role in the game's functionality and user interface.
Key Classes
1. **yoyo (`yoyo.java`)**:
- The main application class.
- Initializes the game window and scenes. - Manages game events and interactions.
2. **Hero (`Hero.java`)** and **InvertedHero (`InvertedHero.java`)**: - Represent different states of the hero character.
- Handle hero-specific animations and properties.
3. **Cherry (`Cherry.java`)**:
- Manages cherry objects for collecting bonus points. - Implements cherry positioning logic on the platforms.
4. **Pillar (`Pillar.java`)**:
- Represents the pillars on which the hero walks. - Handles creation and positioning of new pillars.
5. **Stick (`Stick.java`)**:
- Manages the stick's behavior including extension and rotation.
6. **BaseHero (`BaseHero.java`)**:
- An abstract class defining the common attributes of hero characters.
7. **MediaManager (`MediaManager.java`)**:
- A singleton class for handling all media-related actions like background music and sound
effects.
8. **GameData (`GameData.java`)**:
- Stores and serializes game data like scores and cherry counts.
Game Scenes
- **Home Scene**: The initial view where players start the game. - **Game Scene**: The main gameplay area.
 - **Game Over Scene**: Displayed upon game completion, showing scores and options to restart or exit.
Functionality
- **Dynamic Stick Mechanics**: Players extend the stick to bridge gaps between platforms.
- **Hero Animation**: The hero walks and flips based on player interactions.
- **Platform Generation**: Randomized platform creation for varied gameplay.
- **Cherry Collection**: Strategically placed cherries add an additional challenge for players.
- **Audio Management**: Background music and sound effects enhance the gaming experience. - **Persistence**: Game state is saved for future sessions.
Assumptions
- When the user has released the mouse after creating the stick , he should not press the mouse again until the stickhero reaches the next pillar.
- Players are familiar with basic game controls and objectives.
- The game is run on a system with JavaFX support.
OOPS Concepts Used
1. **Inheritance**: Shared features among hero characters are inherited from a base class. 2. **Polymorphism**: Different behaviors of game elements under various conditions.
3. **Abstraction**: Abstract classes and methods simplify the complexity of hero actions.
4. **Encapsulation**: Internal state of game elements is protected through private access modifiers.
5. **Design Patterns**:
- **Singleton Pattern** in `MediaManager` ensures a single instance manages all media
resources.
- **Iterator Pattern** used for iterating over platform widths.
Running the Game
To run the game:
- Ensure JavaFX SDK (version 11 or higher) is installed.
- Compile and execute `yoyo.java`.
- Interact with the game using mouse clicks for stick extension and SPACEBAR for flipping the hero's orientation.
Development and Contributions
- **Version Control**: Git for tracking changes and collaborative development.

- **Testing**: Unit tests for critical game functionalities.
Future Enhancements
- **New Levels and Challenges**: Introduction of varied difficulty levels. - **Multiplayer Mode**: Enabling gameplay with multiple players.
- **Leaderboards**: Online leaderboards for competitive play.
---
This README provides an extensive overview of the `Yoyo` game, its structure, gameplay, and the technicalities behind it. It can be expanded with additional details or modified as per the future developments of the game.
Points to note:
- We have added 3 different music for gameplay , stick growth , game over. - We have created and added an animation at the start of the game.
- We have shown translation as the animation in rotation of the stick.
- As an added feature of difficulty level everytime the growth speed of stick increases making it hard for the players.
