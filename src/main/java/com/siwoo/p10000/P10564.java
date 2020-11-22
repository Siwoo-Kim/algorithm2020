package com.siwoo.p10000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 자바 메모리 초과 주의
@Used(algorithm = Algorithm.DP)
public class P10564 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, M;
    private static int[] S;
    private static Integer[][] D = new Integer[5001][5001];

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(reader.readLine());
        for (int t=0; t<T; t++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(token.nextToken());
            M = Integer.parseInt(token.nextToken());
            S = new int[M];
            token = new StringTokenizer(reader.readLine());
            for (int i=0; i<M; i++)
                S[i] = Integer.parseInt(token.nextToken());
            for (int i=0; i<D.length; i++)
                Arrays.fill(D[i], null);
            int answer = go(0, 0);
            System.out.println(answer);
        }
    }

    private static int go(int score, int arm) {
        if (score > N) return -1;
        if (arm > N) return -1;
        if (arm == N) return score;
        if (D[score][arm] != null)
            return D[score][arm];
        int max = -1;
        for (int i=0; i<M; i++)
            max = Math.max(max, go(score+S[i], arm+score+S[i]));
        return D[score][arm] = max;
    }
}
