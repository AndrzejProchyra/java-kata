package org.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(ints = {3, 6, 9})
    void shouldBeFizzForMultipleOf3(int n) {
        String result = fizzBuzz.fizzBuzzOf(n);
        assertThat(result).isEqualTo("fizz");
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 25, 125})
    void shouldBeBuzzForMultipleOf5(int n) {
        String result = fizzBuzz.fizzBuzzOf(n);
        assertThat(result).isEqualTo("buzz");
    }

    @ParameterizedTest
    @ValueSource(ints = {15,30,45})
    void shouldBeFizzBuzzForMultipleOfBoth3And5(int n) {
        String result = fizzBuzz.fizzBuzzOf(n);
        assertThat(result).isEqualTo("fizzbuzz");
    }


}
