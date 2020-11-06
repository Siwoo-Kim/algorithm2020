package com.siwoo.p2000;

import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static com.siwoo.util.Algorithm.TWO_POINTER;

@Used(algorithm = TWO_POINTER)
public class P2003 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[] A;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int l = 0, r = 0, cnt = 0, sum = 0;
        while (l < A.length) {
            if (sum < M && r < A.length)
                sum += A[r++];
            else {
                if (sum == M)
                    cnt++;
                sum -= A[l++];
            }
        }
        System.out.println(cnt);
    }
}
