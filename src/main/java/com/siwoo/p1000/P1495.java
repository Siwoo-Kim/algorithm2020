package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.BACK_TRACKING)
public class P1495 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, S, M, ANSWER = -1;
    private static int[] VOLUMES;
    private static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        S = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        VOLUMES = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        visit = new boolean[N][M+1];
        dp(0, S, new Stack<>());
        System.out.println(ANSWER);
    }
    
    private static void dp(int i, int s, Stack<Integer> stack) {
        if (i == N) {
            ANSWER = Math.max(ANSWER, s);
            return;
        }
        int x = s - VOLUMES[i];
        if (x >= 0 && !visit[i][x]) {
            visit[i][x] = true;
            dp(i + 1, x, stack);
        }
        x = s + VOLUMES[i];
        if (x <= M && !visit[i][x]) {
            visit[i][x] = true;
            dp(i + 1, x, stack);
        }
    }
}
