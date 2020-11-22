package com.siwoo.p12000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

@Used(algorithm = Algorithm.DP)
public class P12969 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, K;
    private static boolean[][][][] D;
    private static char[] answer;

    public static void main(String[] args) {
        N = scanner.nextInt();
        K = scanner.nextInt();
        D = new boolean[31][31][31][(N*(N-1)/2)+1];
        answer = new char[N+1];
        if (!go(0, 0, 0, 0))
            System.out.println(-1);
    }

    private static boolean go(int i, int p, int a, int b) {
        if (i == N) {
            if (p == K) {
                System.out.println(new String(answer));
                return true;
            }
            return false;
        }
        if (D[i][a][b][p]) return false;
        D[i][a][b][p] = true;
        answer[i] = 'A';
        if (go(i+1, p, a+1, b))
            return true;
        answer[i] = 'B';
        if (go(i+1, p+a, a, b+1))
            return true;
        answer[i] = 'C';
        return go(i + 1, p + a + b, a, b);
    }
}
