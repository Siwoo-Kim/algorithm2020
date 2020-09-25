package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * D[n] 을 n 번째 포도주 잔을 마신 경우의 최대 포도주 양이라 한다면,
 *      D[n] = max(D[n-1], D[n-2] + S[n], D[n-3] + S[n-1] + S[n])
 */
@Used(algorithm = Algorithm.DP)
public class P2156 {
    private static Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int N;
    private static int S[];
    private static long D[];

    public static void main(String[] args) {
        N = scanner.nextInt();
        S = new int[N+1];
        D = new long[N+1];
        for (int i=1; i<=N; i++)
            S[i] = scanner.nextInt();
        D[1] = S[1];
        if (N >= 2)
            D[2] = S[1] + S[2];
        dp(3);
        System.out.println(Arrays.stream(D).max().getAsLong());
    }

    private static void dp(int index) {
        if (index > N) return;
        D[index] = D[index-1];
        D[index] = Math.max(D[index], D[index-2] + S[index]);
        D[index] = Math.max(D[index], D[index-3] + S[index-1] + S[index]);
        dp(index+1);
    }
}
