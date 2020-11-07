package com.siwoo.p7000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.MEET_IN_THE_MIDDLE)
public class P7453 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] A, B, C, D;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = new int[N];
        B = new int[N];
        C = new int[N];
        D = new int[N];
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            A[i] = Integer.parseInt(token.nextToken());
            B[i] = Integer.parseInt(token.nextToken());
            C[i] = Integer.parseInt(token.nextToken());
            D[i] = Integer.parseInt(token.nextToken());
        }
        int[] left = new int[N*N];
        Map<Integer, Integer> right = new HashMap<>();
        int k = 0;
        for (int i=0; i<N; i++)
            for (int j=0; j<N; j++) {
                left[k++] = A[i] + B[j];
                int v = C[i] + D[j];
                if (!right.containsKey(v))
                    right.put(v, 1);
                else
                    right.put(v, right.get(v) + 1);
            }
        
        long cnt = 0;
        for (int e: left) {
            if (right.containsKey(-e))
                cnt += right.get(-e);
        }
        System.out.println(cnt);
    }
}
