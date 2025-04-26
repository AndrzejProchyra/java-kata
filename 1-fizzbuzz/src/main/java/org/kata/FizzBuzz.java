package org.kata;

public class FizzBuzz {
    String fizzBuzzOf(int n) {
        if (n == 3) {
            return "fizz";
        }
        return Integer.toString(n);
    }
}