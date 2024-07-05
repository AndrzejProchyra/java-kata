package org.kata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MarsRoverUnitTests {

    @Test
    void should_not_move_if_on_1_2_and_given_no_commands() {
        assertFinalPositionForInstructions("5 5\n1 2 N\n", "1 2 N");
    }

    @Test
    void should_not_move_if_on_1_3_and_given_no_commands() {
        assertFinalPositionForInstructions("5 5\n1 3 N\n", "1 3 N");
    }

    @Test
    void should_turn_left_starting_on_1_2_N() {
        assertFinalPositionForInstructions("5 5\n1 2 N\nL", "1 2 W");
    }

    private void assertFinalPositionForInstructions(String instructions, String expectedFinalPosition) {
        MarsRover rover = new MarsRover();
        String finalPosition = rover.execute(instructions);
        assertThat(finalPosition).isEqualTo(expectedFinalPosition);
    }
}
