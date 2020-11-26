package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.STACK)
public class P11873 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] A, B;

    public static void main(String[] args) throws IOException {
        while (true) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(token.nextToken());
            M = Integer.parseInt(token.nextToken());
            if (N == 0 && M == 0) return;
            A = new int[N][M];
            B = new int[N][M];
            for (int i=0; i<N; i++) 
                A[i] = Arrays.stream(reader.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            for (int i=0; i<N; i++)
                for (int j=0; j<M; j++) {
                    if (A[i][j] == 0) continue;
                    B[i][j] = 1;
                    if (i-1 >= 0)
                        B[i][j] += B[i-1][j];
                }
            long d = 0;
            for (int i=0; i<N; i++)
                d = Math.max(d, histogram(B[i]));
            System.out.println(d);
        }
    }
    
    private static class Pair {
        final int h, i;

        private Pair(int i, int h) {
            this.h = h;
            this.i = i;
        }
    }

    private static long histogram(int[] histogram) {
        histogram = Arrays.copyOf(histogram, histogram.length+1);
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(0, histogram[0]));
        long max = histogram[0];
        for (int i=1; i<histogram.length; i++) {
            while (!stack.isEmpty() 
                    && stack.peek().h > histogram[i]) {
                Pair p = stack.pop();
                long width = i;
                if (!stack.isEmpty()) {
                    int left = stack.peek().i;
                    int right = i;
                    width = right - left - 1;
                }
                max = Math.max(max, width * p.h);
            }
            stack.push(new Pair(i, histogram[i]));
        }
        return max;
    }

}
