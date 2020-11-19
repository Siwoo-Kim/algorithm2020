package com.siwoo.p15000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * DP[i] 가 i 번째 날에 얻을 수 있는 최대 수익이라면,
 *  DP[i] 을 이용하여 i+1 번째, i+T[i] 의 값을 계산할 수 있다.
 *  
 *  DP[i+1] = Math.max(DP[i+1], DP[i])
 *  DP[i+T[i]] = Math.max(DP[i+T[i]], DP[i] + P[i])
 *  
 */
public class P15486 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Pair[] PAIRS;
    private static long[] DP;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());    // 1 based index
        PAIRS = new Pair[N+1];
        DP = new long[N+2];
        for (int i=1; i<=N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            PAIRS[i] = new Pair(Integer.parseInt(token.nextToken()), 
                    Integer.parseInt(token.nextToken()));
        }
        dp(1);
        System.out.println(Arrays.stream(DP).max().getAsLong());
    }

    private static void dp(int day) {
        if (day >= N+1) return;
        Pair pair = PAIRS[day];
        DP[day+1] = Math.max(DP[day+1], DP[day]);
        if (day+pair.t <= N+1)
            DP[day+pair.t] = Math.max(DP[day+pair.t], DP[day] + pair.p);
        dp(day+1);
    }

    private static class Pair {
        private final int t, p;

        private Pair(int t, int p) {
            this.t = t;
            this.p = p;
        }
    }
    
}
