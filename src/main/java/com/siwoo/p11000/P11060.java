package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * dp(i) 가 i 칸 까지 오기위한 최소 점프 횟수라고 한다면
 * dp(i) = (0<k<i && A[k] + k >= i) Math.min(dp(k)) + 1 
 * 
 */
@Used(algorithm = Algorithm.DP)
public class P11060 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[] A;
    private static Integer[] DP;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        DP = new Integer[N];
        dp(N-1);
        System.out.println(DP[N-1] == null? -1: DP[N-1]);
    }

    private static int dp(int index) {
        if (index == 0) return DP[index] = 0;
        if (DP[index] != null) return DP[index];
        int cnt = -1;
        for (int i=0; i<index; i++) {
            if (i + A[i] >= index) {
                int d = dp(i);
                if (d == -1) continue;
                if (cnt == -1 || cnt > d)
                    cnt = d;
            }
        }
        return DP[index] = cnt == -1? -1: cnt+1;
    }

}
