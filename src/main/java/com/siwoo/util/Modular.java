package com.siwoo.util;

/**
 * 나머지 연산.
 * (A+B) mod M = ((A % M) + (B % M)) % M
 * 덧셈, 곱셈, 뺄셈에 적용.
 *
 */
public class Modular {

    public static int add(int x, int y, int mod) {
        return ((x % mod) + (y % mod)) % mod;
    }

    public static int multiply(int x, int y, int mod) {
        return ((x % mod)) * ((y % mod)) % mod;
    }

    public static int minus(int x, int y, int mod) {
        return ((x % mod) - (y % mod) + mod) % mod;
    }

    public static void main(String[] args) {
        System.out.println((100 + 37) % 13);
        System.out.println(add(100, 37, 13));
        System.out.println((100 * 37) % 13);
        System.out.println(multiply(100, 37, 13));
        System.out.println((6 - 5) % 3);
        System.out.println(minus(6, 5, 3));
    }
}
