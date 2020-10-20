package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제의 핵심은 행렬을 순차적으로 순회하면서 A[i][j] != B[i][j] 라면 무조건 토글해야 된다는 것이다.
 * A[0][0] 만을 생각한다면 B[0][0] 과 다르다면 무조근 토글해야 된다.
 *  아니라면 두 행렬을 같게할 수 있는 방법은 없다. => toggle(x) == toggle(toggle(x))
 *  다음 A[0][1] 칸의 경우에도 A[0][0] 을 처리한 이후이므로 경우의 수는 한가지 밖에 없다. A[0][1] == B[0][1] ? toggle: next
 *
 */
@Used(algorithm = Algorithm.GREEDY)
public class P1080 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] A;
    private static int[][] B;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        A = new int[N][M];
        B = new int[N][M];
        for (int i=0; i<N; i++)
            A[i] = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        for (int i=0; i<N; i++)
            B[i] = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();

        int cnt = 0;
        for (int i=0; i<N-2; i++)
            for (int j=0; j<M-2; j++)
                if (A[i][j] != B[i][j]) {
                    toggle(i, j, A);
                    cnt++;
                }
        for (int i=0; i<N; i++)
            for (int j=0; j<M; j++)
                if (A[i][j] != B[i][j]) {
                    System.out.println(-1);
                    return;
                }
        System.out.println(cnt);
    }

    private static void toggle(int row, int col, int[][] A) {
        for (int i=row; i<row+3; i++)
            for (int j=col; j<col+3; j++)
                A[i][j] = 1 - A[i][j];
    }
}
