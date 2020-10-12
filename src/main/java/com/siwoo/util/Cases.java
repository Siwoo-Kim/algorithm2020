package com.siwoo.util;

import java.math.BigInteger;

public final class Cases {

    private static BigInteger factorial(int x) {
        if (x == 0) return BigInteger.ONE;
        return factorial(x-1).multiply(BigInteger.valueOf(x));
    }

    private static BigInteger nPr(int n, int r) {
        return factorial(n).divide(factorial(n-r));
    }

    private static BigInteger nCr(int n, int r) {
        return factorial(n).divide((factorial(n-r).multiply(factorial(r))));
    }
}
