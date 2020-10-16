package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.GREEDY)
public class P11047 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());
        A = new int[N];
        for (int i=0; i<N; i++)
            A[i] = Integer.parseInt(reader.readLine());
        int cnt = 0, i = N-1;
        while (K != 0) {
            if (A[i] > K)
                i--;
            else {
                cnt += K/A[i];
                K %= A[i];
            }
        }
        System.out.println(cnt);
    }
}
