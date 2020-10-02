package com.siwoo.p14000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * select(N) = 2^N (N<=15) = 32,768
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P14501 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] T;
    private static int[] P;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        T = new int[N];
        P = new int[N];
        for (int i=0; i<N; i++) {
            String[] DATA = reader.readLine().split("\\s+");
            T[i] = Integer.parseInt(DATA[0]);
            P[i] = Integer.parseInt(DATA[1]);
        }
        System.out.println(select(0, 0));
    }

    private static int select(int day, int pay) {
        if (day == N) return pay;
        if (day > N) return Integer.MIN_VALUE;
        return Math.max(select(day + T[day], pay + P[day]), select(day + 1, pay));
    }
}
