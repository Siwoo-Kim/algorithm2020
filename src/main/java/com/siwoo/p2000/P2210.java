package com.siwoo.p2000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class P2210 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N = 5;
    private static int[][] BOARD = new int[N][N];
    private static int[][] D = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        for (int i=0; i<N; i++)
            BOARD[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        Set<String> set = new HashSet<>();
        for (int i=0; i<N; i++)
            for (int j=0; j<N; j++)
                set.addAll(traverse(i, j, BOARD[i][j] + "", new HashSet<>()));
        System.out.println(set.size());
    }

    private static Set<String> traverse(int i, int j, String s, Set<String> result) {
        if (s.length() == 6) {
            result.add(s);
            return result;
        }
        for (int[] d: D) {
            int x = i + d[0],
                    y = j + d[1];
            if (!valid(x, y)) continue;
            traverse(x, y, s + BOARD[x][y], result);
        }
        return result;
    }

    private static boolean valid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
