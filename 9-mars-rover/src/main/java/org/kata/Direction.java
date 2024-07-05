package org.kata;

public enum Direction {
    N, W, S, E;

    public Direction turn(String command) {
        if (command.equals("L")) {
            return switch (this) {
                case N -> W;
                case S -> E;
                case E -> N;
                default -> S;
            };
        }
        return switch (this) {
            case N -> E;
            case E -> S;
            case S -> W;
            default -> N;
        };
    }
}
