package com.siwoo.p6000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

/**
 * 카잉 달력 <M:N> 이 있고, <m:n> 이 주어질 때 년도를 계산하라.
 * 1 <= M,N <= 40,000 ( 40,000^2 )
 *  => M 칸씩 뛰어 검사하면 O(N) 만에 문제를 풀 수 있음
 *      ex) <5:7> 일때 <3:5> 가 주어진다면
 *          3:3 -> 3:? -> 3:? -> 3:?
 *              +5      +5      +5
 *         K=3     K=8    K=13    K=18
 *         Y=K%N 단 Y == 0 then Y = N
 *         Y=3     Y=1    Y=6     Y=4
 *  => O(N)
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P6064 {
    private static Scanner scanner = new Scanner(System.in);
    private static int T, M, N;

    public static void main(String[] args) {
        T = scanner.nextInt();
        for (int t=0; t<T; t++) {
            M = scanner.nextInt();
            N = scanner.nextInt();
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            boolean found = false;
            for (int k=m; k<=(M*N); k+=M) {
                int y = k % N;
                if (y == 0) y = N;
                if (y == n) {
                    found = true;
                    System.out.println(k);
                    break;
                }
            }
            if (!found)
                System.out.println(-1);
        }
    }

}
