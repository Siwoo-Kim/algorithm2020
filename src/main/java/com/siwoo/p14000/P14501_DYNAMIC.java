package com.siwoo.p14000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.DP)
public class P14501_DYNAMIC {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[] T, P;
    private static Integer[] D;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        T = new int[N];
        P = new int[N];
        D = new Integer[N];
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            T[i] = Integer.parseInt(token.nextToken());
            P[i] = Integer.parseInt(token.nextToken());
        }
        int answer = dp(0);
        System.out.println(answer);
    }

    private static int dp(int day) {
        if (day == N) return 0;
        if (day > N) return Integer.MIN_VALUE;
        if (D[day] != null) return D[day];
        int r1 = dp(day + T[day]) + P[day];
        int r2 = dp(day + 1);
        return D[day] = Math.max(r1, r2);
    }

}
