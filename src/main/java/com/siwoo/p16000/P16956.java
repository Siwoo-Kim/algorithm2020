package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P16956 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static char[][] board;
    private static int[][] D = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
    
    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        board = new char[N][M];
        for (int i=0; i<N; i++)
            board[i] = reader.readLine().toCharArray();
        boolean ok = true;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (board[i][j] == 'W') {
                    for (int[] d: D) {
                        int dx = i + d[0],
                                dy = j + d[1];
                        if (dx >= 0 && dx < N && dy >= 0 && dy < M) {
                            if (board[dx][dy] == '.')
                                board[dx][dy] = 'D';
                            else if (board[dx][dy] == 'S') {
                                ok = false;
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (ok) {
            System.out.println(1);
            StringBuffer sb = new StringBuffer();
            Arrays.stream(board).forEach(a -> {
                for (char x: a)
                    sb.append(x);
                sb.append("\n");
            });
            System.out.println(sb.toString());
        } else {
            System.out.println(0);
        }
    }
    
}
