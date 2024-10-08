# Mars Rover kata

## Problem

A robot rover is landed by NASA on a plateau on Mars.
The plateau is divided up into a grid to simplify navigation.
This plateau, must be navigated by the rover so that it´s on-board cameras can get a complete view of the surrounding
terrain to send back to Earth.

## Input

- The rover receives three lines of input:
    - The first line defines the upper-right coordinates of the plateau.
        - Example: ‘5:5’
        - The lower-left coordinates are always ‘0 0’.
    - The second line contains the rover’s starting position and direction.
        - Example ‘1:2:N’ measn x=1, y=2 and Direction=North
        - Assume that the square directly North from (x, y) is (x, y+1).
    - The third line contains the sequence of commands to execute.
        - Example: ‘LMLMLMLMM’
        - ‘L’ and ‘R’ makes the rover spin 90 degrees left or right respectively, without moving from its current spot.
        - ‘M’ means move forward one grid point, and maintain the same heading.

### Example input

```text
5:5
1:2:N
MLMLMLMM
```

Assume that NASA will never send invalid messages to the rover, nor will it send a message moving the rover outside the
defined grid.

## Output

The output for the rover should be its final co-ordinates and heading and the list of execute commands whith the final
coordinates after executing each command

```text
1:3:N
M 2:3:N
L 2:3:E
M 3:2:N
L
M
L
M
M
```

## Examples

### Input 1

```text
5:5
1:2:N
LMLMLMLMM
```

### Expected Output 1

```text
1:3:N
```

### Input 2

```text
5:5
3:3:E
MMRMMRMRRM
```

### Expected Output 2

```text
5:1:E
```

## First run

Implement the Mars Rover as best as you can using TDD.

There are 2 acceptance tests to get you started. Treat them as an automated "definition of done". Once the 2 acceptance
tests are passing your are done :) Don't try to make the acceptance tests go green. Focus on writing smaller unit tests
to implement the behaviour requiered and leave the acceptance tests on red. Once you impplement all behaviours the
acceptance tests should turn green.

### Important

**_Your first task is to make the acceptance tests fail for the rigth reason._**

### Example starting code

```typescript
const rover = new Rover();
const finalPosition = rover.execute('5:5\n1:2:N\nLMLMLMLMM');
```

## Second run

Refactor the code you produced in the first run using design patterns, namely Command, State and Strategy.

Additional reading after completing the
exercise <https://www.codurance.com/publications/2019/01/22/mars-rover-kata-refactoring-to-patterns>
