package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.BINARY_SEARCH)
public class P2805 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static long N, M, ANSWER = 0;
    private static long[] A;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        A = new long[(int) N];
        token = new StringTokenizer(reader.readLine());
        long MAX = 0;
        for (int i=0; i<N; i++) {
            A[i] = Long.parseLong(token.nextToken());
            MAX = Math.max(A[i], MAX);
        }
        long h = binarySearch(1, MAX, M, A);
        System.out.println(h);
    }

    private static long binarySearch(long left, long right, long M, long[] A) {
        if (left > right) return -1;
        long h = (left + right) / 2;
        long m = cut(h, A);
        if (m >= M) {   // 더 높게 자를 수 있다.
            ANSWER = Math.max(ANSWER, h);
            binarySearch(h+1, right, M, A);
        } else {
            binarySearch(left, h-1, M, A);
        }
        return ANSWER;
    }

    private static long cut(long height, long[] a) {
        long cnt = 0;
        for (long tree: a)
            if (tree >= height)
                cnt += tree - height;
        return cnt;
    }
}
