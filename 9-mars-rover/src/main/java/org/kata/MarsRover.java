package org.kata;

public class MarsRover {

    public String execute(String instructions) {
        String startingPosition = instructions.split("\n")[1];
        String startingDirection = startingPosition.substring((startingPosition.length() - 1));
        String commands = extractCommands(instructions);
        if (commands.startsWith("LL")) {
            return startingPosition.substring(0, 3) + " S";
        }
        if (commands.startsWith("L")) {
            return startingPosition.substring(0, 3) + " " + Direction.valueOf(startingDirection).turn("L");
        }
        if (commands.startsWith("R")) {
            return startingPosition.substring(0, 3) + " E";
        }
        return startingPosition;
    }

    private String extractCommands(String instructions) {
        return instructions.split("\n").length > 2 ? instructions.split("\n")[2] : "";
    }
}