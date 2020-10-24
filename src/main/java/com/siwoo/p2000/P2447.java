package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

@Used(algorithm = Algorithm.DnC)
public class P2447 {
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder sb = new StringBuilder();
    private static char[][] board;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = new Scanner(System.in).nextInt();
        board = new char[N][N];
        print(0, 0, N);
        for (int i=0; i<N; i++) {
            for (int j = 0; j < N; j++)
                if (board[i][j] == '\0')
                    writer.write(' ');
                else
                    writer.write(board[i][j]);
            writer.write('\n');
        }
        writer.flush();
    }

    private static void print(int x, int y, int n) {
        if (n == 1) {
            board[x][y] = '*';
            return;
        }
        int unit = n / 3;
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++) {
                if (i == 1 && j == 1) continue;
                print(x + (unit * i), y + (unit * j), unit);
            }
    }

}
