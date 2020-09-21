package com.siwoo.util;

/**
 * 시간 복잡도?
 *  문제의 크기 (N) 에 대한 시간 측정.
 *  표기법 Big-O
 *  최악의 경우만 고려.
 */
public class TimeComplexity {
    private static final double HUNDRED_MILLION = 100000000.F;

    /**
     * 주어진 문제이 크기 N 에 대해여 주주구먹식으로 시간 계산.
     * Note:
     *  연산 1 억당 1초라고 추측.
     *
     * @param N
     * @return
     */
    public static String takes(long N) {
        return String.format("%.2f seconds", N / HUNDRED_MILLION);
    }

    public static void main(String[] args) {
        int N = 100000;
        System.out.println(takes(N));
        System.out.println(takes((long) Math.pow(N, 2)));
        N = 500;
        System.out.println(takes((long) Math.pow(N, 3)));
    }
}
