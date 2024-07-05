package org.kata;

public class MarsRover {

    public String execute(String instructions) {
        String startingPosition = instructions.split("\n")[1];
        String startingDirection = startingPosition.substring((startingPosition.length() - 1));
        String commands = extractCommands(instructions);
        String finalPosition = startingPosition;
        Direction currentDirection = Direction.valueOf(startingDirection);
        for (int i = 0; i < commands.length(); i++) {
            if (commands.charAt(i) == 'L') {
                currentDirection = currentDirection.turn("L");
                finalPosition = startingPosition.substring(0, 3) + " " + currentDirection;
            }
            if (commands.charAt(i) == 'R') {
                Direction finalDirection = Direction.valueOf(startingDirection).turn("R");
                finalPosition = startingPosition.substring(0, 3) + " " + finalDirection;
            }
        }
        return finalPosition;
    }

    private String extractCommands(String instructions) {
        return instructions.split("\n").length > 2 ? instructions.split("\n")[2] : "";
    }
}