package com.siwoo.p2000;

import com.siwoo.util.Marker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Marker
public class P2616 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int K;
    private static int[] A;
    private static Integer[][] D;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        K = Integer.parseInt(reader.readLine());
        D = new Integer[N][4];
        int x = dp(3, N-1);
        System.out.println(x);
    }

    private static int dp(int k, int index) {
        if (D[index][k] != null) return D[index][k];
        int sum = 0;
        for (int i=0; i<K-1; i++)
            sum += A[index-i];
        int max = 0;
        for (int i=index; (i+1)-K >= 0; i--) {
            sum += A[i-1];
            max = Math.max(max, dp(k-1, i-K) + sum);
            sum -= A[i];
        }
        return D[index][k] = max;
    }
}
