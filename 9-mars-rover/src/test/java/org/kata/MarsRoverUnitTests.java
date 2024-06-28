package org.kata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MarsRoverUnitTests {

    @Test
    void should_not_move_if_given_no_commands() {
        String commands = "5 5\n1 2 N\n";
        MarsRover rover = new MarsRover();

        String finalPosition = rover.execute(commands);

        assertThat(finalPosition).isEqualTo("1 2 N");
    }
}
