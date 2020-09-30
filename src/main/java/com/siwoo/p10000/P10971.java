package com.siwoo.p10000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P10971 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, MIN = Integer.MAX_VALUE;
    private static int[][] distances;
    private static int[] path;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        distances = new int[N][N];
        for (int i=0; i<N; i++)
            distances[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        path = IntStream.range(0, N).toArray();
        do {
            boolean ok = true;
            int sum = 0;
            for (int i=0; i<path.length-1; i++) {
                if (distances[path[i]][path[i+1]] == 0) {
                    ok  = false;
                    break;
                }
                sum += distances[path[i]][path[i+1]];
            }
            if (ok && distances[path[N-1]][path[0]] != 0) {
                sum += distances[path[N-1]][path[0]];
                MIN = Math.min(sum, MIN);
            }
            System.out.println(Arrays.toString(path));
        } while (nextPermutation(1, N, path));
        System.out.println(MIN);
    }

    private static boolean nextPermutation(int from, int to, int[] S) {
        int i = to-1;
        while (i>from && S[i-1] >= S[i])
            i--;
        if (i == from) return false;
        int j = to-1;
        while (S[i-1] >= S[j])
            j--;
        swap(i-1, j, S);
        j = to-1;
        while (i < j)
            swap(i++, j--, S);
        return true;
    }

    private static void swap(int i, int j, int[] S) {
        int t = S[i];
        S[i] = S[j];
        S[j] = t;
    }
}
