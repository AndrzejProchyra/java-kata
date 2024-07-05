package org.kata;

public class MarsRover {

    public String execute(String commands) {
        String startingPosition = commands.split("\n")[1];
        String cmds = commands.split("\n").length > 2 ? commands.split("\n")[2] : "";
        if (cmds.startsWith("L")) {
            return startingPosition.substring(0, 3) + " W";
        }
        if (cmds.startsWith("R")) {
            return startingPosition.substring(0, 3) + " E";
        }
        return startingPosition;
    }
}