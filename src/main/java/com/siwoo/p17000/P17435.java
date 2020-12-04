package com.siwoo.p17000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P17435 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, Q;
    private static int[][] f;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        f = new int[20][20];
        StringTokenizer token = new StringTokenizer(reader.readLine());
        for (int i=1; i<=N; i++)
            f[i][0] = Integer.parseInt(token.nextToken());
        for (int j=1; j<20; j++)
            for (int i=1; i<=N; i++)
                f[i][j] = f[f[i][j-1]][j-1];
        Q = Integer.parseInt(reader.readLine());
        for (int i=0; i<Q; i++) {
            token = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(token.nextToken()),
                    x = Integer.parseInt(token.nextToken());
            for (int k=0; k<20; k++) {
                if ((n & (1 << k)) != 0)
                    x = f[x][k];
            }
            System.out.println(x);
        }
    }
    
}
