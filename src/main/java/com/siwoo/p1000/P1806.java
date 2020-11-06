package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.TWO_POINTER)
public class P1806 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[] A;
    private static int N, S;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        S = Integer.parseInt(token.nextToken());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int l = 0, r = 0, sum = 0, length = Integer.MAX_VALUE;
        while (l < N) {
            int sublength = r - l;
            if (sum >= S)
                length = Math.min(sublength, length);
            if (sum < S && r != N) {
                sum += A[r++];
            } else {
                if (sum == S && r != N)
                    sum += A[r++];
                else
                    sum -= A[l++];
            }
        }
        System.out.println(length == Integer.MAX_VALUE? 0: length);
    }
    
}
