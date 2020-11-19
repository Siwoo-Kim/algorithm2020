package com.siwoo.p11000;

import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static com.siwoo.util.Algorithm.DP;

/**
 * 
 * A = 40, 30, 30, 50
 * 
 * 1~4 까지의 연속된 파일을 합칠 때의 최소 비용은?
 *  1. (40) (30, 30, 50)
 *  2. (40, 30), (30, 50)
 *  3. (40, 30, 30), (50)
 * 
 *  D[i][j] 을 i 에서 j 까지 파일을 합칠때의 최소 비용이라 가정한다면,
 *      k = 파일을 나누는 기준.
 *      D[i][j] = (i<=k<j) min(D[i][k], D[k+1][j])
 *      
 */
@Used(algorithm = DP)
public class P11066 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N;
    private static Integer[][] D;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(reader.readLine());
        while (T != 0) {
            N = Integer.parseInt(reader.readLine());
            D = new Integer[N][N];
            int[] A = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int answer = dp(0, N-1, A);
            System.out.println(answer);
            T--;
        }
    }

    private static int dp(int start, int end, int[] A) {
        if (start == end) return D[start][end] = 0;
        if (D[start][end] != null) return D[start][end];
        int sum = 0;
        for (int i=start; i<=end; i++)
            sum += A[i];
        int answer = Integer.MAX_VALUE;
        for (int i=start; i<end; i++) {
            int x = dp(start, i, A) + dp(i+1, end, A) + sum;
            answer = Math.min(x, answer);
        }
        return D[start][end] = answer;
    }
}
