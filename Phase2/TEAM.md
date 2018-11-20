 # CSC207 Group 0618 Team responsibilities/contributions#
This is the TEAM.md file to log individual responsibilities/contributions to the project, and the
mileStones in the whole A1 process.

=============RESPONSIBILITIES=============

## Timothy Lee
- Designed User Interface (including corresponding Activities xml's and interactive buttons) for
Starting Activity, GameCenterActivity, ProfilesActivity, and TilesSettingsActivity).

    - Implemented corresponding activities' xml's using constraint layout, and set onClick button
    listeners for corresponding buttons and their functions.

    - Modified and uploaded all background images for the project via tools like Adobe PhotoShop
    and Adobe Color CC in order for images and font colors to be consistent to the chosen color
    scheme of (#f2a0d4 [@color pink], #045aa9[@color PrimaryColor], #94d7a3[@color Accent],
    #94d7a3 [@color Green], #f07c65[@color Orange],#FF430346 [@color PrimaryDark]).

    - Helped with debugging and refactoring code smells and naming conventions/javadoc to match
    consistency.


##Junxuan Wu
--Designed the verification system, scoreboard system and some ui structure design.  ActivityHelper， IOHelper,
sequenceBundler, User, UserRouter, LoginActivity, TileSettingsActivity, RegisterActivity, ScoreBoardActivity,
PersonalScoreBoard Activity. Do some of the refactoring part(mostly with Yinling Luo). Find and fix most bugs of code.

--Implement the user verify process using storable and non-storable procedure. Manage the user storage system.
And implement the process of registration.

--Implement the rendering process of different types of scoreboard from our stored data. And designed the
display image structure (xml format).

--Implement the userProfile page to remind the user of the previous steps, records and games he did.

--Design the storage structure, based on HashMaps.

--Implement upload image function

##Quanzhou Li
--Implement undo function
    Implement a stack to store each move the user makes in a game, enabling the user to undo by popping
    from the stack. The maximum number of undo steps is changeable (Before a game starts).

--Implement autosave function
    Implement a HashMap to match each user with its specific BoardManager, enabling different users to
    save their own games. The HashMap changes after each move and is stored to an external file, enabling
    the game to save its state automatically.

##Yinling Luo
--Implement complexity function
    Make Tiles callable for boards of all complexity, and generate corresponding images for 5X5 game.
    Implementing the complexity part in tileSettings and initialize the game activity.

--Implement ScoreBoard.
    Make BoardManager to keep track of the score (which represents the number of steps the user takes)
    After user solved the puzzle, save the score into the corresponding HashMap.
    Connect movementController to final activity.

--Do most of the refactoring jobs, refactor code smells, rearrange the classes, do revision on code
    And Find and fix most bugs of code. Rearrange the design of class models.
    Refactoring parts involved: Activities, solve the huge problem: coupling of GameActivity,
    SlidingTileSetting, and startingActivity.

--Help implement upload image function


====== MEETING MINUITES =======
team meeting procedure and milestones are included in file "TEAMRECORD.md"