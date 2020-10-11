package com.siwoo.p14000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * S=[5,1,2], N=3
 * SUB = {}, {5}, {1}, {2}, {5,1}, {5,2}, {5,1,2} = (2^N)-1
 * bitmask = (1 << N) - 1
 *  contains i ?  bitmask & (1 << e)
 *
 *  O(2^20*N)
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P14225_BITMASK {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] S;
    private static boolean[] check = new boolean[(20 * 100000) + 1];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        S = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int bits=0; bits<(1<<N); bits++) {
            int sum = 0;
            for (int e=0; e<N; e++) {
                if ((bits & (1 << e)) != 0)
                    sum += S[e];
            }
            check[sum] = true;
        }
        for (int i=1; i<check.length; i++) {
            if (!check[i]) {
                System.out.println(i);
                return;
            }
        }
    }
}
