package com.siwoo.p15000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * D[n] 을 정수 N 을 1, 2, 3 의 합으로 나타내는 방법의 수라 했을때,
 *      D[n] = D[n-1] + D[n-2] + D[n-3]
 */
@Used(algorithm = Algorithm.DP)
public class P15988 {
    private static Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static long MOD = 1000000009L;
    private static int T, MAX = 1000000;
    private static long[] D = new long[MAX+1];

    public static void main(String[] args) {
        D[0] = 1;
        for (int n=1; n<=MAX; n++) {
            if (n - 1 >= 0)
                D[n] += D[n-1];
            if (n - 2 >= 0)
                D[n] += D[n-2];
            if (n - 3 >= 0)
                D[n] += D[n-3];
            D[n] %= MOD;
        }
        T = scanner.nextInt();
        for (int i=0; i<T; i++)
            System.out.println(D[scanner.nextInt()]);
    }

}
