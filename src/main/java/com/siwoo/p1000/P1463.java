package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

/**
 * D[V] = 숫자 v 을 만들기 위한 최소 연산 횟수 라고 정의했을 때,
 *
 * D[V] = min(D[V-1], D[V/2], D[V/3]) + 1 (D[P][1] = 0)
 */
@Used(algorithm = Algorithm.DP)
public class P1463 {
    private static int MAX = 1000000;
    private static int[] D = new int[MAX+1];

    public static void main(String[] args) {
        System.out.println(dp(new Scanner(System.in).nextInt()));
    }

    private static int dp(int n) {
        if (n == 1) return 0;
        if (D[n] != 0) return D[n];
        int answer = dp(n-1);
        if (n % 2 == 0)
            answer = Math.min(answer, dp(n/2));
        if (n % 3 == 0)
            answer = Math.min(answer, dp(n/3));
        return D[n] = answer + 1;
    }
}
