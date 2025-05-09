# Katacombs

## Your job

- Implement the game using both Domain Driven Design and Hexagonal architecture.
- Start from the use cases, we will not be implementing the transport layer for this project.
- Use a in-memory database implementation.

## The game

The game describes a fictional world to be explored by the player via a set of commands. The world is a collection of
locations linked to each other geographically (on North, South, East, or West) or via specific connection points (doors,
passages, gates, stairs, etc.). The player can move among them using cardinal points for directions or exploiting the
connection points with actions or items. The world will have treasures hidden in several locations, which will be
collected by players. The game terminates when the player finds the exit of the katacomb. The score will be the sum of
the value of the treasures collected.

## Playing the game

### Starting

The game at startup shows the title and a brief description of the initial location. When the player moves to another
location, the system will always prompt a title and a brief description for the new location.

```text
LOST IN SHOREDITCH.
YOU ARE STANDING AT THE END OF BRICK LANE BEFORE A SMALL BRICK BUILDING CALLED THE OLD TRUMAN BREWERY.
AROUND YOU IS A FOREST OF RESTAURANTS.
A SMALL STREAM OF CRAFTED BEER FLOWS OUT OF THE BUILDING AND DOWN A GULLY.
>
```

### Exploring the world

#### _Moving_

**GO** followed by the letter of the cardinal point

```text
> GO N => move to the NORTH
> GO E => move to the EAST
> GO S => move to the SOUTH
> GO W => move to the WEST
```

#### _Stairs_

**GO** followed by **UP** or **DOWN** depending where the stairs are leading

```text
> GO UP => use stairs to go up
> GO DOWN => use stairs to go down
```

#### _Looking_

**LOOK** allows looking in every direction to have an idea of the surroundings or a better description of an item.

```text
LOST IN SHOREDITCH.
YOU ARE STANDING AT THE END OF BRICK LANE BEFORE A SMALL BRICK BUILDING CALLED THE OLD TRUMAN BREWERY.
AROUND YOU IS A FOREST OF INDIAN RESTAURANTS.
A SMALL STREAM OF CRAFTED BEER FLOWS OUT OF THE BUILDING AND DOWN A GULLY.
> LOOK N
I CAN SEE A BRICK BUILDING WITH A SIGN SAYING "TRUMAN BREWERY" AND A WOODEN WHITE DOOR
```

### _Interact with the environment_

There are several commands for interacting with the environment. These commands are always composed of two parts: the
action and the object. Usually, the object is specified in the main description or in the result of the LOOK command.

#### _Opening and closing_

**OPEN** will try to open the object of the command. Usually, it's about doors or gates, but this should be a magic
world...

```text
I CAN SEE A BRICK BUILDING WITH A SIGN SAYING "TRUMAN BREWERY" AND A WOODEN WHITE DOOR.
> OPEN DOOR
YOU ARE INSIDE THE MAIN ROOM OF THE TRUMAN BREWERY. THERE IS A STRONG SMELL OF HOP AND A DOZEN EMPTY CASKS
```

**CLOSE** will try to close the object of the command. Usually, it's about doors or gates, but this should be a magic
world...

```text
I CAN SEE A BRICK BUILDING WITH A SIGN SAYING "TRUMAN BREWERY" AND AN OPEN WOODEN WHITE DOOR.
> CLOSE DOOR
I CAN SEE A BRICK BUILDING WITH A SIGN SAYING "TRUMAN BREWERY" AND A WOODEN WHITE DOOR.
```

#### _Taking and dropping items_

**TAKE** will collect an item from the environment and will put it in the bag. **DROP** allows leaving an item in the
environment. Every item can be taken and dropped anywhere, and the game has to list the items in the environment just
after the main description.

```text
INSIDE THE BUILDING.
YOU ARE INSIDE THE BUILDING, A WELL HOUSE FOR LARGE SPRINGS.
THERE ARE SOME KEYS ON THE FLOOR.
> TAKE KEYS
KEYS: TAKEN.
> DROP KEYS
KEYS: DROPPED.
```

#### _Checking BAG_

**BAG** will show a list of items in user's bag. The bag can containing only 10 items.

```text
> BAG
THE BAG CONTAINS SOME KEYS, A SWISS KNIFE, A CANDLE AND A COMPASS.
```

#### _Using items_

**USE** will perform an action with the item, if the environment is set up for it and if we have the item in the bag.
The action can result in an extra sentence in the main description, unlocking a passage toward a hidden location,
releasing a new item in the location.

```text
> USE KEYS
THE RED DOOR HAS BEEN UNLOCKED!
> OPEN DOOR
YOU ARE INSIDE THE RED CHAMBER
```

#### _Collecting gold_

The treasures we can find in the cave are in the form of gold coins. They get collected automatically when a user moves
to a location with gold for the first time or opens an item containing gold for the first time. The total amount of gold
retrieved can be seen in the BAG.

### _Special commands_

#### _Help_

**?** will list all the available commands and their usage.

```text
> ?
GO [direction]
LOOK [direction/item]
OPEN [item]
CLOSE [item]
TAKE [item]
DROP [item]
BAG
USE [item]
QUIT
```

#### _Quit_

**Quit** terminates the game

#### _World correctness_

There are only two requirements for the world. The first is that there should not be two different locations with the
same title. The second is that the locations must have mutual reversed references to each other. That means that if from
location A going South I end up in location B, then from location B going North I must end up in location A. Same
principle should be valid for all cardinal points, but also when going up and down.

## Features

### Feature look and move in the Katacombs

```gherkin
Scenario: New Katacombs Player
  Given I'm a new player of Katacombs
  When I register to play the game
  Then I'm listed as a player in the players list
  And I'm shown the description of the entry of Katacombs
  And the possible paths with a short description of the path plus the direction North, South, East, West, Up or Down
  And the items present in that location

Scenario: Look in a direction from a location with a path to the direction
  Given I'm playing Katacombs
  When I look in a direction
  And there is a path to that direction
  Then I'm shown a description of the path plus a brief description what lies in that direction

Scenario: Look in a direction without a path to the direction
  Given I'm playing Katacombs
  When I look in a direction
  But there isn't a path to that direction
  Then I'm shown a message saying "Nothing to look in this direction"

Scenario: Move in a direction from a location with a path to the direction
  Given I'm playing Katacombs
  When I look in a direction
  And there is a path to that direction
  Then I'm shown a description of the new location
  And the possible paths with a short description of the path plus the direction North, South, East, West, Up or Down
  And the items present in that location

Scenario: Move in a direction without a path to the direction
  Given I'm playing Katacombs
  When I look in a direction
  But there isn't a path to that direction
  Then I'm shown a message saying "Cannot move in this direction"

Scenario: Move in a direction with a closed path to the direction
  Given I'm playing Katacombs
  When I look in a direction
  But the path is closed
  Then I'm shown a message saying "Cannot move in this direction because ..."
```

### Feature pick and drop items

```gherkin
Scenario: Inspect Player Bag
  Given I'm playing Katacombs
  When I inspect my bag
  And the bag contains items
  Then I'm shown a message displaying the bag items

Scenario: Inspect empty Player Bag
  Given I'm playing Katacombs
  When I inspect my bag
  And the bag is empty
  Then I'm shown a message "The bag is empty"

Scenario: Take an item from a location
  Given I'm playing Katacombs
  When Take an item from a location
  And the item exists in the location
  And I don't have that item in my bag yet
  Then I'm shown a message saying "Took %s"
  And the item is present in my bag

Scenario: Take an item from a location that is not present
  Given I'm playing Katacombs
  When Take an item from a location
  But the item does not exists in the locationction
  Then I'm shown a message saying "There is no %s to take here"
  And the item is not present in my bag

Scenario: Take an item from a location but item is already in the bag
  Given I'm playing Katacombs
  When Take an item from a location
  nd the item exists in the location
  But I already have the item on my bag
  Then I'm shown a message saying "I already have %s in my bag"

Scenario: Drop an existing item from the bag
  Given I'm playing Katacombs
  When Drop an item from the bag
  And the item exists in the bag
  Then I'm shown a message saying "Droped %"
  And the item is no longer present in my bag

Scenario: Drop a non existing item from the bag
  Given I'm playing Katacombs
  When Drop an item from the bag
  But the item does not exist in the bag
  Then I'm shown a message saying "I don't have a % to drop"
```

### Feature using items

```gherkin
Scenario: Use a key in the bag to open a loked door
  Given I'm playing Katacombs
  And I'm in a location with a path behind a locked door
  And I have the key to open the door in my bag
  When I use the key to open the door
  Then I'm shown a message "Door open"
  And I'm shown a short description of the path
```

### Feature quit Katacombs

```gherkin
Scenario: Quit Game
  Given I'm playing Katacombs
  When I quit the game
  Then I'm shown a message "Good bye"
```

## Resources

- [MUD](https://en.wikipedia.org/wiki/Multi-user_dungeon)
- [How to program a text adventure in C](https://helderman.github.io/htpataic/htpataic01.html)
