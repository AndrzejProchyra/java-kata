package org.kata;

public enum Direction {
    N, W, S, E;

    public Direction turn(String command) {
        if (this.equals(N)) {
            return W;
        }
        if (this.equals(S)) {
            return E;
        }
        return S;
    }
}
