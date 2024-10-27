# AngryBirdsGame

A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

This project was generated with a template including simple application launchers and a main class extending `Game` that sets the first screen.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------

EXPLAINATION:
myself: Dipanshu parasrampuria (2023204) - i have mostly executed and done coding part. 
partner KUNAL JAIN (2023293)- major contribution was ui, flow and idea and prototyping and applying concpts of oops.

in this project i have build upon simplegame from libgx documentation.
the citations are some from angrybirds wiki and some are handmade by us on canva and remaning assets are downloaded from net.

now the sample flow is iterated below;
 Homescreen (start or exit) -> levelscreen (newgame or saved game) as of now new game is working obviously. -> levelscreen (only level 1 is functional) -> gamescreen of level 1 (containing slingshot, 3 birs, 3 structure and 1 bird) and a restt button which restes to the same screen, ad in pause button i have save button to save game, resuem to continue game and exit(ehich leads to lost game screen which have option to go back to homescreen) and to access winscreen, type 'w' on keyboard. 

i ve adhered to code quality and simple and basic approach to code.  

set up: the game is named Game2testing and first you may need to set up configrations by clicking on eid tconfigration , selecting approppriate filename (Game2testing) and then selecting model and then refer this image: ![image](https://github.com/user-attachments/assets/bd459e56-f2fd-4810-bb61-f768c78a0303)

upon stting up i have inserted all the umages in assets and make individual screens classes and used abstract classes for angrybirds/ stuctures and pigs. where ive defined the texture for each of its type(ie, red, black and yellow)

and then i ahve used template Game for main screen and then for subsiquent screens i have implemented, 'X'Screen implements screen.

Code Structure
Main Components
Screens:

HomeScreen: The main menu where players can navigate to the game levels.
GameScreen: Displays the gameplay GUI, with birds, structures, and a background.
Classes for Game Entities:

Bird Class and Subclasses (RedBird, BlackBird, YellowBird): Each bird type has a texture (image) and can be drawn on the screen.
Structure Class and Subclasses (WoodStructure, SteelStructure): These represent the blocks used to build the piggy structures. Each structure type has a unique texture and can be rendered at specific positions in GameScreen.
GameScreen Logic:

Static GUI Components: The GameScreen uses libGDX’s Sprite objects to place static images of birds, structures, and other elements.
Rendering and Layout: Birds and structures are drawn using relative positioning to allow flexibility. For example, the slingshot and birds are positioned on the left, while the structure and pigs are placed on the right.
Class Implementations
Abstract Bird and Structure Classes: These classes define a shared interface for birds and structures, with properties such as textures and methods like draw() and dispose().
Subclasses: Each bird and structure subclass loads a specific texture (e.g., redbird.png for RedBird), making it easy to manage assets and draw each component on the screen.

oops concepts are applied as:Here’s how OOP concepts are applied in your Angry Birds project:

 1. Inheritance:
   - Base classes like `Bird` and `Structure` provide common attributes (e.g., texture) and methods (`draw()`, `dispose()`).
   - Subclasses (`RedBird`, `SteelStructure`) extend these base classes, each with unique textures and properties.

 2. Polymorphism:
   - Each bird and structure subclass overrides the base class’s attributes with its specific texture (e.g., `redbird.png` for `RedBird`).
   - `draw()` and `dispose()` are used polymorphically across different objects in `GameScreen`.

3. Abstraction:
   - Abstract classes (`Bird`, `Structure`) hide implementation details, like texture loading and disposal, providing only essential methods.

 4. Encapsulation:
   - Attributes like textures are private to each class and accessed only through public methods like `getTexture()` and `dispose()`.

Together, these OOP principles simplify code structure, maintainability, and make it easy to add new entities (e.g., more bird types). This modular approach also allows each component (e.g., birds, structures) to be extended or modified independently.


i have hardcoded the positions as for stayic gui.




