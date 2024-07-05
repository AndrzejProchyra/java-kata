package org.kata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kata.Direction.N;
import static org.kata.Direction.W;

class DirectionTest {

    @Test
    void should_turn_left_from_North_to_West() {
        assertThat(N.turn("L"))
                .isEqualTo(W);
    }
}