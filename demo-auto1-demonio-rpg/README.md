# Demônio RPG

A command-line-based Role Playing Game implemented using the MVC (Model-View-Controller) pattern in pure Java.
This project serves as an educational example of how to structure a game using the MVC architecture.

## Overview

Demônio RPG is an open-world RPG game where players can explore cities, fight monsters, level up,
and save their progress. The game demonstrates various software design patterns and principles including MVC,
Observer pattern, and Builder pattern.

## Prerequisites

* Apache Maven 3.6.0 or higher
* JDK 11 or higher

## Game Features

* **Character Classes**: Choose between Warrior (high damage, medium defense) and Knight (medium damage, high defense)
* **Open World Exploration**: Navigate between cities in four directions (North, South, East, West)
* **Combat System**: Turn-based combat with monsters featuring attacks, critical hits, and defense
* **Progression System**: Level up by defeating monsters or level down by running away from fights
* **Save/Load System**: Save your game progress and load it later

## Gameplay

1. **Start**: Choose to start a new game or load a saved one
2. **Character Creation**: Select your character class (Warrior or Knight)
3. **Exploration**: Navigate between cities and encounter monsters
4. **Combat**: Fight monsters to gain experience and level up
5. **Save**: Save your progress at any time during exploration

## Architecture

The game is built using the MVC (Model-View-Controller) pattern:

* **Model**: Contains game entities (Player, Monster, City) and game logic
* **View**: Handles user interface through menus and status displays
* **Controller**: Manages game flow and user input

![MVC Architecture](mvc.jpg)

The project also includes a class diagram showing the relationships between components:

![Class Diagram](diagram.jpg)

## How to Compile

The project uses Maven for build management. To compile the source code:

```bash
mvn clean package
```

## How to Run

### From IDE

Execute the main class: `br.tech.desiderati.demo.demonio.controller.StartController`

### From Command Line

Run the compiled JAR file:

```bash
java -jar target/demo-auto1-demonio-rpg-0.0.1-SNAPSHOT.jar
```

## Future Improvements

* World map visualization
* Enhanced character customization
* Improved level-up/level-down algorithms
* More diverse monster types
* Enhanced player statistics
* Better combat mechanics

## License

This project is licensed under the MIT License.

## Contributing

Contributions are welcome! Feel free to submit pull requests or open issues to improve the game.
