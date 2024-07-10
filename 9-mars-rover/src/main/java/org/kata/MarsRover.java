package org.kata;

import static org.kata.Direction.*;

public class MarsRover {

    public String execute(String instructions) {
        String[] instructionParts = instructions.split("\n");
        Position position = Position.fromString(instructionParts[1]);
        String startingDirection = instructionParts[1].substring((instructionParts[1].length() - 1));
        Direction currentDirection = Direction.valueOf(startingDirection);
        String commands = extractCommands(instructions);
        for (int i = 0; i < commands.length(); i++) {
            String command = String.valueOf(commands.charAt(i));
            if (command.equals("M")) {
                if (currentDirection.equals(N)) {
                    position = position.incrementY();
                }
                if (currentDirection.equals(S)) {
                    position = position.decrementY();
                }
                if (currentDirection.equals(W)) {
                    position = position.decrementX();
                }
                if (currentDirection.equals(E)) {
                    position = position.incrementX();
                }
            }
            if (command.equals("L") || command.equals("R")) {
                currentDirection = currentDirection.turn(command);
            }
        }
        return position + " " + currentDirection;
    }

    private String extractCommands(String instructions) {
        return instructions.split("\n").length > 2 ? instructions.split("\n")[2] : "";
    }
}