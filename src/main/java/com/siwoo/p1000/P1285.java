package com.siwoo.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1285 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static boolean[][] BOARD;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        BOARD = new boolean[N][N];
        for (int i=0; i<N; i++) {
            String line = reader.readLine();
            for (int j=0; j<N; j++)
                if (line.charAt(j) == 'H')
                    BOARD[i][j] = true;
        }
        int answer = N * N;
        for (int bits=0; bits<(1<<N); bits++) {
            int sum = 0;
            for (int i=0; i<N; i++) {
                int cnt = 0;
                for (int j=0; j<N; j++) {
                    boolean head = BOARD[i][j];
                    if ((bits & (1 << j)) != 0)
                        head = !head;
                    if (head)
                        cnt++;
                }
                sum += Math.min(cnt, N-cnt);
            }
            answer = Math.min(sum, answer);
        }
        System.out.println(answer);
    }

}
