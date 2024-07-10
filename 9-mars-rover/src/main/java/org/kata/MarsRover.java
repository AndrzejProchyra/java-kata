package org.kata;

import static org.kata.Direction.N;
import static org.kata.Direction.S;

public class MarsRover {

    public String execute(String instructions) {
        Position position = Position.fromString(instructions.split("\n")[1]);
        String startingDirection = instructions.split("\n")[1].substring((instructions.split("\n")[1].length() - 1));
        String commands = extractCommands(instructions);
        Direction currentDirection = Direction.valueOf(startingDirection);
        for (int i = 0; i < commands.length(); i++) {
            String command = String.valueOf(commands.charAt(i));
            if (command.equals("M")) {
                if (currentDirection.equals(N)) {
                    position = position.incrementY();
                }
                if (currentDirection.equals(S)) {
                    position = position.decrementY();
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