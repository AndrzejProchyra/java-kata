package org.kata;

public enum Direction {
    N, W;

    public Direction turn(String command) {
        return W;
    }
}
