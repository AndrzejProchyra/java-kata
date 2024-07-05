package org.kata;

public enum Direction {
    N, W, S, E;

    public Direction turn(String command) {
        return switch (this) {
            case N -> W;
            case S -> E;
            case E -> N;
            default -> S;
        };
    }
}
