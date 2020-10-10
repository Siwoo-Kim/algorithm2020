package com.siwoo.p14000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * O(MAX*N + 2^N)
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P14225 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int MAX = 100000, N;
    private static int[] S;
    private static Set<Integer> visit = new HashSet<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        S = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        select(0, 0);
        for (int i=1; i<=MAX*N; i++)
            if (!visit.contains(i)) {
                System.out.println(i);
                return;
            }
    }

    private static void select(int current, int sum) {
        if (current == N) {
            visit.add(sum);
            return;
        }
        select(current + 1, sum + S[current]);
        select(current + 1, sum);
    }
}
