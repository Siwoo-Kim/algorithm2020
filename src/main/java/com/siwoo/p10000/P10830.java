package com.siwoo.p10000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.DP)
public class P10830 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static long B, MOD = 1000;
    private static long[][] m, zero, one;
    private static Map<Long, long[][]> D = new HashMap<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        B = Long.parseLong(token.nextToken());
        m = new long[(int) N][(int) N];
        zero = new long[(int) N][(int) N];
        one = new long[(int) N][(int) N];
        for (int i=0; i<N; i++) {
            m[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToLong(Long::parseLong)
                    .map(x -> x % MOD)
                    .toArray();
            System.arraycopy(m[i], 0, one[i], 0, (int) N);
            Arrays.fill(zero[i], 1);
        }
        long[][] r = pow(m, B);
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            for (int j = 0; j < N; j++)
                sb.append(r[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static long[][] pow(long[][] m, long b) {
        if (D.containsKey(b)) return D.get(b);
        long[][] r;
        if (b == 0) r = zero;
        else if (b == 1) r = one;
        else if (b % 2 == 0) {
            long[][] x = pow(m, b / 2);
            r = multi(x, x);
        } else {
            r = multi(pow(m, 1), pow(m, b - 1));
        }
        D.put(b, r);
        return r;
    }

    private static long[][] multi(long[][] m1, long[][] m2) {
        long[][] r = new long[N][N];
        for (int i=0; i<N; i++)
            for (int j=0; j<N; j++) {
                for (int k = 0; k < N; k++)
                    r[i][j] += m1[i][k] * m2[k][j] % MOD;
                r[i][j] %= MOD;
            }
        return r;
    }
}
