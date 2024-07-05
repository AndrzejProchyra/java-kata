package org.kata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kata.Direction.*;

class DirectionTest {

    @Test
    void should_turn_left_from_North_to_West() {
        assertThat(N.turn("L"))
                .isEqualTo(W);
    }

    @Test
    void should_turn_left_from_West_to_South() {
        assertThat(W.turn("L"))
                .isEqualTo(S);
    }

    @Test
    void should_turn_left_from_South_to_East() {
        assertThat(S.turn("L"))
                .isEqualTo(E);
    }
}