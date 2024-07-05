package org.kata;

public enum Direction {
    N, W, S, E;

    public Direction turn(String command) {
        return command.equals("L") ? turnLeft() : turnRight();
    }

    private Direction turnLeft() {
        return switch (this) {
            case N -> W;
            case S -> E;
            case E -> N;
            default -> S;
        };
    }

    private Direction turnRight() {
        return switch (this) {
            case N -> E;
            case E -> S;
            case S -> W;
            default -> N;
        };
    }
}
