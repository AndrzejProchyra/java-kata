package org.kata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @ParameterizedTest
    @CsvSource({
            "N, W",
            "W, S",
            "S, E",
            "E, N"
    })
    void should_turn_left(Direction initialDirection, Direction finalDirection) {
        assertThat(initialDirection.turn("L"))
                .isEqualTo(finalDirection);
    }

    @ParameterizedTest
    @CsvSource({
            "N, E",
            "E, S",
            "S, W",
            "W, N"
    })
    void should_turn_right(Direction initialDirection, Direction finalDirection) {
        assertThat(initialDirection.turn("R"))
                .isEqualTo(finalDirection);
    }
}