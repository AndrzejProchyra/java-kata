package org.kata;

public class FizzBuzz {
    String fizzBuzzOf(int n) {
        if (isFizzy(n)) {
            return "fizz";
        }
        return Integer.toString(n);
    }

    private static boolean isFizzy(int n) {
        return n % 3 == 0;
    }
}