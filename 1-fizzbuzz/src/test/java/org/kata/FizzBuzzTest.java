package org.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuzzTest {

    private FizzBuzz fizzBuzz;

    @BeforeEach
    void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    @Test
    void shouldReturn1For1() {
        String result = fizzBuzz.fizzBuzzOf(1);
        assertThat(result).isEqualTo("1");
    }

    @Test
    void shouldReturn2For2() {
        String result = fizzBuzz.fizzBuzzOf(2);
        assertThat(result).isEqualTo("2");
    }

    @Test
    void shouldReturn4For4() {
        String result = fizzBuzz.fizzBuzzOf(4);
        assertThat(result).isEqualTo("4");
    }
}
