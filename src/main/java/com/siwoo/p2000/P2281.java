package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.DP)
public class P2281 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[] A;
    private static Integer[][] D;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        A = new int[N];
        D = new Integer[N][M+10];
        for (int i=0; i<N; i++)
            A[i] = Integer.parseInt(reader.readLine());
        int x = go(0, 0);
        System.out.println(x);
    }

    private static int go(int index, int taken) {
        if (index == N) return 0;
        if (D[index][taken] != null) 
            return D[index][taken];
        int x = Integer.MAX_VALUE;
        if (taken + A[index] <= M)
            x = go(index+1, taken + A[index] + 1);  //write in the current line
        int space = M-(taken-1);    //remove space at the end
        x = Math.min(x, go(index+1, A[index] + 1) + space*space); //write in the next line
        return D[index][taken] = x;
    }
}
