package com.simbir.util;

public final class Fibonacci {

    public static int get(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return get(n - 1) + get(n - 2);
        }
    }
}
