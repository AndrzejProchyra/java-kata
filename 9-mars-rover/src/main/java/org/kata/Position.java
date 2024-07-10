package org.kata;

public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position fromString(String s) {
        String[] components = s.split(" ");
        int x = Integer.parseInt(components[0]);
        int y = Integer.parseInt(components[1]);
        return new Position(x, y);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
