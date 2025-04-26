package org.kata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuzzTest {
    @Test
    void true_is_true() {
        assertThat(true).isTrue();
    }

    @Test
    void shouldReturn1For1() {
        String result = fizzBuzzOf(1);
        assertThat(result).isEqualTo("1");
    }

    private String fizzBuzzOf(int n) {
        return "1";
    }
}
