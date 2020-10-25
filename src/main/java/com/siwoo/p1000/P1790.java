package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

@Used(algorithm = Algorithm.BINARY_SEARCH)
public class P1790 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, LAST;
    private static long K;

    public static void main(String[] args) {
        N = scanner.nextInt();
        K = scanner.nextLong();
        long x = lengthOf(N);
        if (x < K) {
            System.out.println(-1);
            return;
        }
        binarySearch(1, N, K);
        String s = Long.toString(LAST);
        long l = lengthOf(LAST);
        System.out.println(s.charAt((int) (s.length()-1-(l-K))));
    }

    private static void binarySearch(int left, int right, long K) {
        if (left > right) return;
        int mid = (left + right) / 2;
        long x = lengthOf(mid);
        if (x < K)
            binarySearch(mid + 1, right, K);
        else {
            LAST = mid;
            binarySearch(left, mid - 1, K);
        }
    }

    private static long lengthOf(int N) {
        long cnt = 0;
        for (int i=1; Math.pow(10, i-1)<=N; i++) {
            long x = (long) (Math.pow(10, i) - 1L);
            if (x > N)
                x = N;
            cnt += (x - (Math.pow(10, i-1) - 1)) * i;
        }
        return cnt;
    }
}
