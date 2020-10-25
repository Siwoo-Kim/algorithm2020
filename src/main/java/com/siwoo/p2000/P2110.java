package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.*;

@Used(algorithm = Algorithm.BINARY_SEARCH)
public class P2110 {
    private static Scanner scanner = new Scanner(System.in);
    private static long N, C, ANSWER;
    private static long[] X;

    public static void main(String[] args) {
        N = scanner.nextInt();
        C = scanner.nextInt();
        X = new long[(int) N];
        for (int i=0; i<N; i++)
            X[i] = scanner.nextLong();
        Arrays.sort(X);
        long max = X[(int) (N-1)] - X[0];
        long d = install(1, max, C, X);
        System.out.println(d);
    }

    /**
     *
     * @param left min dis
     * @param right max dis
     * @param C required hubs
     * @param X
     * @return
     */
    private static long install(long left, long right, long C, long[] X) {
        if (left > right) return -1;
        long d = (left + right) / 2;
        long installed = tryInstall(d, X);  //number of installed hubs with 'distance d'
        if (installed >= C) {
            ANSWER = Math.max(ANSWER, d);
            install(d + 1, right, C, X);
        } else {
            install(left, d - 1, C, X);
        }
        return ANSWER;
    }

    private static long tryInstall(long d, long[] X) {
        int cnt = 1, prev = 0;
        for (int i=1; i<X.length; i++) {
            if (X[i] - X[prev] >= d) {
                cnt ++;
                prev = i;
            }
        }
        return cnt;
    }
}
