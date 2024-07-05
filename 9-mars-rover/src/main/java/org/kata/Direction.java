package org.kata;

public enum Direction {
    N, W, S;

    public Direction turn(String command) {
        if (this.equals(N)) {
            return W;
        }
        return S;
    }
}
