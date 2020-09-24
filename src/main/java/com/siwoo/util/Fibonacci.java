package com.siwoo.util;

/**
 * 동적 프로그래밍 (Dynamic Programming)
 *  큰 문제를 작은 문제로 나누어 푼다.
 *
 *  동적 프로그래밍 속성
 *      1. Optimal Substructure.    (해당 문제는 더 작은 문제로 쪼개서 풀 수 있는 문제여야 한다.)
 *      2. Overlapping sub-problems. (중복된 문제여야 메모리제이션 하여 재사용할 수 있다.)
 */
@Used(algorithm = Algorithm.DP)
public class Fibonacci {
    private final int D[];

    public Fibonacci(int n) {
        D = new int[n+1];
    }

    public int get() {
        return fibonacci(D.length-1);
    }

    private int fibonacci(int n) {
        if (n == 0) return n;
        if (n == 1) return n;
        if (D[n] != 0) return D[n];
        return D[n] = fibonacci(n-2) + fibonacci(n-1);
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci(7);
        System.out.println(fibonacci.get());
    }
}
