package org.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuzzTest {

    private FizzBuzz fizzBuzz;

    @BeforeEach
    void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    @ParameterizedTest
    @CsvSource({"1,1", "2,2", "4,4"})
    void shouldBeTheIntegerItself(int n, String expected) {
        String result = fizzBuzz.fizzBuzzOf(n);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldBeFizzFor3() {
        String result = fizzBuzz.fizzBuzzOf(3);
        assertThat(result).isEqualTo("fizz");
    }

    @Test
    void shouldBeFizzFor6() {
        String result = fizzBuzz.fizzBuzzOf(6);
        assertThat(result).isEqualTo("fizz");
    }

}
