package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Used(algorithm = Algorithm.DP)
public class P1970 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] A;
    private static Integer[][] D;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        D = new Integer[N+1][N+1];
        int x = go(0, N-1);
        System.out.println(x);
    }

    private static int go(int left, int right) {
        if (left >= right) return 0;
        if (D[left][right] != null)
            return D[left][right];
        int max = go(left+1, right);    // calc for skipping A[left]
        for (int k=left+1; k<=right; k++) {
            if (A[left] == A[k]) {
                int x = go(left+1, k-1) + go(k+1, right) + 1;
                max = Math.max(x, max);
            }
        }
        return D[left][right] = max;
    }
}
