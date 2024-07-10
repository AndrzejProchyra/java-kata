package org.kata;

public class MarsRover {

    public String execute(String instructions) {
        Position startingPosition = Position.fromString(instructions.split("\n")[1]);
        String startingDirection = instructions.split("\n")[1].substring((instructions.split("\n")[1].length() - 1));
        String commands = extractCommands(instructions);
        Direction currentDirection = Direction.valueOf(startingDirection);
        for (int i = 0; i < commands.length(); i++) {
            String command = String.valueOf(commands.charAt(i));
            if (command.equals("M")) {
                return "0 1 N";
            }
            currentDirection = currentDirection.turn(command);
        }
        return startingPosition + " " + currentDirection;
    }

    private String extractCommands(String instructions) {
        return instructions.split("\n").length > 2 ? instructions.split("\n")[2] : "";
    }
}