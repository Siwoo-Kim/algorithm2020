package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.DP)
public class P11049 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Matrix[] matrices;
    private static Long[][] DP;
    private static int N;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        matrices = new Matrix[N];
        DP = new Long[N+1][N+1];
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            matrices[i] = new Matrix(
                    Integer.parseInt(token.nextToken()),
                    Integer.parseInt(token.nextToken()));
        }
        long x = dp(0, matrices.length-1);
        System.out.println(x);
    }

    private static long dp(int left, int right) {
        if (right == left) return 0;
        if (right - left == 1) return matrices[left].mutli(matrices[left+1]);
        if (DP[left][right] != null)
            return DP[left][right];
        long answer = Integer.MAX_VALUE;
        for (int i=left; i<right; i++) {
            long l = dp(left, i);
            long r = dp(i+1, right);
            long x  = l + r + (matrices[left].n * matrices[i].m * matrices[right].m); //left matrix * right matrix
            answer = Math.min(x, answer);
        }
            
        return DP[left][right] = answer;
    }

    private static class Matrix {
        private final int n, m;

        private Matrix(int n, int m) {
            this.n = n;
            this.m = m;
        }

        public int mutli(Matrix matrix) {
            return n * m * matrix.m;
        }
    }
}
