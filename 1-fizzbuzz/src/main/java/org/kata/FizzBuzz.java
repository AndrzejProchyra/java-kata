package org.kata;

public class FizzBuzz {
    String fizzBuzzOf(int n) {
        if(isFizzy(n)&&isBuzzy(n)){
            return "fizzbuzz";
        }
        if (isFizzy(n)) {
            return "fizz";
        }
        if (isBuzzy(n)) {
            return "buzz";
        }
        return Integer.toString(n);
    }

    private boolean isBuzzy(int n) {
        return n % 5 == 0;
    }

    private static boolean isFizzy(int n) {
        return n % 3 == 0;
    }
}