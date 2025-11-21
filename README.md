# POOSnake - Snake Game Remix

A modern remake of the classic Snake game, developed as part of an Object-Oriented Programming university course. This project features both graphical and textual interfaces, customizable gameplay elements, dynamic obstacles, and a leaderboard system.

## Features

### Game Modes
- **Manual Mode**: Control the snake using keyboard inputs (WASD or Arrow Keys)
- **Automatic Mode**: Watch the snake move automatically using an AI-based pathfinding algorithm

### Interface Options
- **Graphical Mode**: Modern Swing-based GUI with visual rendering
- **Textual Mode**: Console-based gameplay for terminal enthusiasts

### Customization
- **Arena Dimensions**: Configure custom width and height for the playing field
- **Snake Head Size**: Adjustable head dimensions affecting gameplay difficulty
- **Food Types**: Choose between circular or square food items
- **Rendering Styles**: Filled or outlined rasterization for visual elements
- **Obstacles**: Add static or dynamic (rotating) obstacles with configurable quantities
- **Scoring System**: Customize points per food item

### Advanced Features
- Dynamic obstacles with rotation around custom pivot points
- Collision detection (self-collision, wall collision, obstacle collision)
- Leaderboard system to track top players
- JUnit test coverage for core game mechanics

## Project Structure

```
Remix-of-Snake-Game/
├── POOSnake/                 # Main source directory
│   ├── Core/                 # Game logic and core mechanics
│   │   ├── Arena.java        # Main game arena controller
│   │   ├── Snake.java        # Snake entity and movement
│   │   ├── AbstractFood.java # Food item base class
│   │   ├── FoodCircle.java   # Circular food implementation
│   │   ├── FoodSquare.java   # Square food implementation
│   │   ├── Obstacle.java     # Obstacle entities
│   │   ├── Player.java       # Player data management
│   │   ├── Rank.java         # Leaderboard system
│   │   └── CoreTests/        # Unit tests for core functionality
│   ├── Geometry/             # Geometric utilities
│   │   ├── Circle.java       # Circle geometric shape
│   │   ├── Square.java       # Square geometric shape
│   │   ├── Poligono.java     # Polygon base class
│   │   ├── Ponto.java        # Point representation
│   │   └── GeometryTests/    # Geometry unit tests
│   └── UI/                   # User interface components
│       ├── GameClient.java   # Console-based game launcher
│       ├── GraphicalClient.java # GUI-based game launcher
│       └── UI.java           # UI factory and rendering
├── JAVADOC/                  # Generated Javadoc documentation
├── lib/                      # External libraries
│   └── junit-platform-console-standalone-1.10.2.jar
└── README.md                 # This file
```

## Requirements

### Prerequisites
- **Java Development Kit (JDK)**: Version 8 or higher
  - Download from: https://www.oracle.com/java/technologies/downloads/
  - Or use OpenJDK: https://openjdk.org/

### Verify Installation
Open a terminal/command prompt and run:
```bash
java -version
```
You should see output indicating Java version 8 or higher.

```bash
javac -version
```
Verify the Java compiler is also installed.

## Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/LuisAPR1/Remix-of-Snake-Game.git
   cd Remix-of-Snake-Game
   ```

2. **Verify project structure**
   Ensure the `POOSnake` directory and all subdirectories are present.

## How to Run

### Option 1: Graphical Interface (Recommended)

1. **Compile the project**
   ```bash
   javac -d bin -encoding UTF-8 -sourcepath POOSnake POOSnake/UI/GraphicalClient.java
   ```

2. **Run the graphical client**
   ```bash
   java -cp bin UI.GraphicalClient
   ```

3. **Configure your game**
   - Select game mode (Manual or Automatic)
   - Choose rasterization type (Filled or Outlined)
   - Set arena dimensions and head size
   - Configure food type (Circle or Square)
   - Set number of obstacles and their type (Static or Dynamic)
   - Enter player name
   - Click "Iniciar Jogo" (Start Game)

### Option 2: Console Interface

1. **Compile the project**
   ```bash
   javac -d bin -encoding UTF-8 -sourcepath POOSnake POOSnake/UI/GameClient.java
   ```

2. **Run the console client**
   ```bash
   java -cp bin UI.GameClient
   ```

3. **Follow the prompts**
   - Choose interface mode: `T` for textual
   - Select game mode: `A` for Automatic or `M` for Manual
   - Enter arena dimensions (width and height)
   - Enter head dimensions
   - Choose food type: `C` for circle or `S` for square
   - Select rasterization: `O` for outline or `F` for filled
   - Enter food dimensions and points per food
   - Set number of obstacles and type: `D` for dynamic or `S` for static
   - If dynamic obstacles, configure rotation angle and pivot point
   - Enter player name
   - Specify number of leaderboard entries to display

### Option 3: Quick Start Script

**For Windows (PowerShell):**
```powershell
# Compile
javac -d bin -encoding UTF-8 -sourcepath POOSnake POOSnake/UI/GraphicalClient.java

# Run
java -cp bin UI.GraphicalClient
```

**For Linux/Mac (Bash):**
```bash
# Compile
javac -d bin -encoding UTF-8 -sourcepath POOSnake POOSnake/UI/GraphicalClient.java

# Run
java -cp bin UI.GraphicalClient
```

## Game Controls

### Manual Mode
- **W** / **Up Arrow**: Move up
- **A** / **Left Arrow**: Move left
- **S** / **Down Arrow**: Move down
- **D** / **Right Arrow**: Move right

### Automatic Mode
The snake uses an intelligent pathfinding algorithm to navigate toward food while avoiding obstacles and self-collision.

## Running Tests

The project includes comprehensive JUnit tests for core functionality.

1. **Compile test classes**
   ```bash
   javac -cp "lib/junit-platform-console-standalone-1.10.2.jar;." -d bin POOSnake/Core/CoreTests/*.java POOSnake/Geometry/GeometryTests/*.java POOSnake/Core/*.java POOSnake/Geometry/*.java
   ```

2. **Run all tests**
   ```bash
   java -jar lib/junit-platform-console-standalone-1.10.2.jar --class-path bin --scan-class-path
   ```

**Note:** On Linux/Mac, replace semicolons (`;`) with colons (`:`) in the classpath:
```bash
javac -cp "lib/junit-platform-console-standalone-1.10.2.jar:." -d bin POOSnake/Core/CoreTests/*.java POOSnake/Geometry/GeometryTests/*.java POOSnake/Core/*.java POOSnake/Geometry/*.java
```

## Gameplay Objectives

1. **Eat Food**: Navigate your snake to consume food items appearing randomly in the arena
2. **Grow**: Each food item eaten increases your snake's length and score
3. **Avoid Collisions**: 
   - Don't hit the arena walls
   - Don't collide with your own body
   - Avoid obstacles (static and dynamic)
4. **Maximize Score**: Compete for high scores on the leaderboard

## Game Over Conditions

The game ends when:
- The snake collides with the arena walls
- The snake collides with itself
- The snake hits an obstacle

After game over, your score is recorded to the leaderboard if it ranks among the top players.

## Leaderboard

Player scores are saved in `rank.txt` and displayed at the end of each game session. The leaderboard tracks player names and their scores.

## Troubleshooting

### "Command not found: javac" or "Command not found: java"
- Ensure JDK is properly installed and added to your system PATH
- Restart your terminal after installation

### "ClassNotFoundException" or "NoClassDefFoundError"
- Make sure you compiled all files: `POOSnake/Core/*.java`, `POOSnake/Geometry/*.java`, and `POOSnake/UI/*.java`
- Verify the `bin` directory was created during compilation
- Check that you're running from the project root directory

### Graphical window not appearing
- Ensure your system supports Java Swing (GUI toolkit)
- Try running the console version instead
- Update your Java installation to the latest version

### Tests failing
- Ensure JUnit JAR file is present in `lib/` directory
- Verify classpath includes both the JAR and compiled classes
- Make sure all source files compiled successfully

## Authors

- **Luís Rosa**
- **José Lima**
- **Pedro Ferreira**

Developed as part of the Object-Oriented Programming course at university.

## License

This project was created for educational purposes as part of a university course.

## Acknowledgments

- Classic Snake game design
- Java Swing framework for GUI
- JUnit 5 for testing framework
