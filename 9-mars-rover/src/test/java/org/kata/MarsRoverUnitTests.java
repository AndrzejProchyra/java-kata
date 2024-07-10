package org.kata;

import org.junit.jupiter.api.Disabled;
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

    @Test
    void should_turn_left_twice_starting_on_1_2_N() {
        assertFinalPositionForInstructions("5 5\n1 2 N\nLL", "1 2 S");
    }

    @Test
    void should_turn_right_twice_starting_on_1_2_N() {
        assertFinalPositionForInstructions("5 5\n1 2 N\nRR", "1 2 S");
    }

    @Test
    void should_turn_right_starting_on_1_2_N() {
        assertFinalPositionForInstructions("5 5\n1 2 N\nR", "1 2 E");
    }

    @Test
    void should_move_one_square_North_starting_on_0_0_N() {
        assertFinalPositionForInstructions("5 5\n0 0 N\nM", "0 1 N");
    }

    @Test
    @Disabled
    void should_move_one_square_North_starting_on_1_1_N() {
        assertFinalPositionForInstructions("5 5\n0 0 N\nM", "1 2 N");
    }

    private void assertFinalPositionForInstructions(String instructions, String expectedFinalPosition) {
        MarsRover rover = new MarsRover();
        String finalPosition = rover.execute(instructions);
        assertThat(finalPosition).isEqualTo(expectedFinalPosition);
    }
}
