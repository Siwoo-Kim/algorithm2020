package com.siwoo.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * N 명의 아이,
 * M 개의 놀이기구 M[i] 은 운행시간.
 *
 * 최소 시간 0 ~ 최대 시간 MAX(M[i]) * N * M
 */
//fail
public class P1561 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static long N, M;
    private static Pair[] pairs;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Long.parseLong(token.nextToken());
        M = Long.parseLong(token.nextToken());
        if (N <= M) {
            System.out.println(N);
        }
        long MAX = 0;
        pairs = new Pair[(int) M];
        token = new StringTokenizer(reader.readLine());
        for (int i=1; i<=M; i++) {
            pairs[i-1] = new Pair(i, Long.parseLong(token.nextToken()));
            MAX = Math.max(pairs[i-1].takes, MAX);
        }
        Arrays.sort(pairs, Pair.C);
        bs(0, 2000000000L * 10000 * 30, N, pairs);
    }

    private static void bs(long minMin, long maxMin, long N, Pair[] pairs) {
        if (minMin > maxMin) return;
        long minutes = (minMin + maxMin) / 2;
        long end = calc(minutes, pairs);
        long rides = numberOfRides(minutes, pairs);
        long start = end - rides + 1;
        if (start > N)
            bs(minMin, minutes-1, N, pairs);
        else if (end < N)
            bs(minutes+1, maxMin, N, pairs);
        else {
            for (int i=0; i<N; i++) {
                if (minutes % pairs[i].takes == 0) {
                    if (start == N) {
                        System.out.println(pairs[i].index);
                        return;
                    }
                    start++;
                }
            }
        }
    }

    private static long numberOfRides(long minutes, Pair[] pairs) {
        long cnt = 0;
        for (Pair p: pairs)
            if (minutes % p.takes == 0)
                cnt++;
        return cnt;
    }

    private static long calc(long minutes, Pair[] pairs) {
        long cnt = 0;
        for (Pair p: pairs)
            cnt += (minutes / p.takes) + 1;
        return cnt;
    }

    private static class Pair {
        private static Comparator<Pair> C = Comparator.comparing(p -> p.takes);
        private long index, takes;

        public Pair(long index, long takes) {
            this.index = index;
            this.takes = takes;
        }
    }
}
