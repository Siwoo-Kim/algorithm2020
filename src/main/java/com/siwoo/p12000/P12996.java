package com.siwoo.p12000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

/**
 * D[i][a][b][c] = i 번째 곡을 불렀고, 각 a, b, c 각각이 불러야할 남은 곡의 수라 가정하면,
 *   D[i][a][b][c] += D[i-1][7 가지 경우. 1 << 8 - 1 (공집합 제거))
 */
@Used(algorithm = Algorithm.DP)
public class P12996 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, A, B, C;
    private static Integer[][][][] D;
    private static int MOD = 1000000007;
    
    public static void main(String[] args) {
        N = scanner.nextInt();
        A = scanner.nextInt();
        B = scanner.nextInt();
        C = scanner.nextInt();
        D = new Integer[N+1][N+1][N+1][N+1];
        long x = go(N, A, B, C);
        System.out.println(x);
    }

    private static int go(int n, int a, int b, int c) {
        if (n == 0) {
            if (a == 0 && b == 0 && c == 0)
                return 1;
            return 0;
        }
        if (D[n][a][b][c] != null)
            return D[n][a][b][c];
        int cnt = 0;
        for (int bitset=1; bitset<(1<<3); bitset++) {
            int[] next = new int[3];
            for (int bit=0; bit<3; bit++) {
                if ((bitset & 1 << bit) != 0)
                    next[bit]++;
            }
            if (a-next[0] >= 0 && b-next[1] >= 0 && c-next[2] >= 0) {
                cnt = (cnt + go(n-1, a-next[0], b-next[1], c-next[2])) % MOD;
            }
        }
        return D[n][a][b][c] = cnt;
    }

}
