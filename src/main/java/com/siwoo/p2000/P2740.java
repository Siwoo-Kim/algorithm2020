package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P2740 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] m1, m2, r;
    private static int N, K, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());
        m1 = new int[N][K];
        for (int i=0; i<N; i++)
            m1[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        token = new StringTokenizer(reader.readLine());
        token.nextToken();
        M = Integer.parseInt(token.nextToken());
        m2 = new int[K][M];
        for (int i=0; i<K; i++)
            m2[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        r = new int[N][M];
        for (int i=0; i<N; i++)
            for (int j=0; j<M; j++)
                for (int k=0; k<K; k++)
                    r[i][j] += m1[i][k] * m2[k][j];
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            for (int j = 0; j < M; j++)
                sb.append(r[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
