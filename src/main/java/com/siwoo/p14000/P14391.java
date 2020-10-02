package com.siwoo.p14000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

/**
 * O(2^N)
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P14391 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] BOARD;
    private static int N, M, ANSWER = 0;

    public static void main(String[] args) throws IOException {
        String[] data = reader.readLine().split("\\s+");
        N = Integer.parseInt(data[0]);
        M = Integer.parseInt(data[1]);
        BOARD = new int[N][M];
        for (int i=0; i<N; i++)
            BOARD[i] = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        select(0, new Stack<>());
        System.out.println(ANSWER);
    }

    private static void select(int index, Stack<Integer> horizontal) {
        if (index == N * M) {
            int sum = 0;
            for (int i=0; i<N; i++) {
                String s = "";
                for (int j=0; j<M; j++) {
                    int current = i * M + j;
                    if (horizontal.contains(current))
                        s += BOARD[i][j];
                    else {
                        if (!s.isEmpty()) {
                            sum += Integer.parseInt(s);
                            s = "";
                        }
                    }
                }
                if (!s.isEmpty())
                    sum += Integer.parseInt(s);
            }
            for (int i=0; i<M; i++) {
                String s = "";
                for (int j=0; j<N; j++) {
                    int current = j * M + i;
                    if (!horizontal.contains(current))
                        s += BOARD[j][i];
                    else {
                        if (!s.isEmpty()) {
                            sum += Integer.parseInt(s);
                            s = "";
                        }
                    }
                }
                if (!s.isEmpty())
                    sum += Integer.parseInt(s);
            }
            ANSWER = Math.max(ANSWER, sum);
            return;
        }
        horizontal.add(index);
        select(index + 1, horizontal);
        horizontal.pop();
        select(index + 1, horizontal);
    }
}
