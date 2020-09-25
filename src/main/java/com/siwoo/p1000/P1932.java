package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * D[r][c] 이 r 행 c 열의 cell 까지 내려왔을 때의 최대 합이라면,
 *      D[r][c] = Math.max(D[r-1][c-1], D[r-1][c]) + C[r][c]
 */
@Used(algorithm = Algorithm.DP)
public class P1932 {
    private static Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int[][] C, D;
    private static int N;

    public static void main(String[] args) {
        N = scanner.nextInt();
        scanner.nextLine();
        C = new int[N][N];
        D = new int[N][N];
        for (int i=0; i<N; i++)
            C[i] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        D[0][0] = C[0][0];
        dp(1);
        System.out.println(Arrays.stream(D[N-1]).max().getAsInt());
    }

    private static void dp(int row) {
        if (row == N) return;
        for (int c=0; c<C[row].length; c++) {
            D[row][c] = D[row-1][c];
            if (c != 0)
                D[row][c] = Math.max(D[row][c], D[row-1][c-1]);
            D[row][c] += C[row][c];
        }
        dp(row+1);
    }
}
