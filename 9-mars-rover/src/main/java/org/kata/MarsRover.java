package org.kata;

public class MarsRover {

    public String execute(String instructions) {
        String startingPosition = instructions.split("\n")[1];
        String commands = instructions.split("\n").length > 2 ? instructions.split("\n")[2] : "";
        if (commands.startsWith("LL")) {
            return startingPosition.substring(0, 3) + " S";
        }
        if (commands.startsWith("L")) {
            return startingPosition.substring(0, 3) + " W";
        }
        if (commands.startsWith("R")) {
            return startingPosition.substring(0, 3) + " E";
        }
        return startingPosition;
    }
}