package com.siwoo.p1000;

import java.util.Scanner;

public class P1654 {
    private static Scanner scanner = new Scanner(System.in);
    private static long N, K, ANSWER;
    private static long[] A;

    public static void main(String[] args) {
        K = scanner.nextInt();
        N = scanner.nextInt();
        A = new long[(int) K];
        long MAX = 0;
        for (int i=0; i<K; i++) {
            A[i] = scanner.nextLong();
            MAX = Math.max(MAX, A[i]);
        }
        long u = binarySearch(1, (int) MAX, N, A);
        System.out.println(u);
    }

    private static long binarySearch(long left, long right, long size, long[] A) {
        if (left > right) return -1;
        long mid = (left + right) / 2;
        long cnt = cut(mid, A);
        if (cnt >= size) {  // 더 작게 자를 수 있다.
            ANSWER = Math.max(mid, ANSWER);
            binarySearch(mid + 1, right, size, A);
        } else
            binarySearch(left, mid-1, size, A);
        return ANSWER;
    }

    public static long cut(long length, long A[]) {
        long cnt = 0;
        for (int i=0; i<A.length; i++)
            cnt += A[i] / length;
        return cnt;
    }
}
