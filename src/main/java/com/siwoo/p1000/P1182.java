package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P1182 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, S;
    private static int[] A;

    public static void main(String[] args) {
        N = scanner.nextInt();
        S = scanner.nextInt();
        A = new int[N];
        for (int i=0; i<N; i++)
            A[i] = scanner.nextInt();
        int cnt = 0;
        for (int set=1; set<(1 << N); set++) {
            int sum = 0;
            for (int e = 0; e < N; e++) {
                if ((set & (1 << e)) != 0)
                    sum += A[e];
            }
            if (sum == S)
                cnt++;
        }
        System.out.println(cnt);
    }
}
