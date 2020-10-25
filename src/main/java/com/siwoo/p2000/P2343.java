package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.BINARY_SEARCH)
public class P2343 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, ANSWER;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        A = new int[N];
        token = new StringTokenizer(reader.readLine());
        int left = 0, right = 0;
        for (int i=0; i<N; i++) {
            A[i] = Integer.parseInt(token.nextToken());
            left = Math.max(A[i], left);
            right += A[i];
        }
        ANSWER = right;
        int size = bs(left, right, M, A);
        System.out.println(size);
    }

    private static int bs(int left, int right, int M, int[] A) {
        if (left > right) return ANSWER;
        int mid = (left + right) / 2;
        int installed = install(mid, A);
        if (installed <= M) {
            ANSWER = Math.min(ANSWER, mid);
            bs(left, mid-1, M, A);
        } else
            bs(mid+1, right, M, A);
        return ANSWER;
    }

    private static int install(int size, int[] A) {
        int cnt = 0, cur = 0;
        for (int i=0; i<A.length; i++) {
            cur += A[i];
            if (cur > size) {
                cnt++;
                cur = A[i];
            }
        }
        if (cur != 0)
            cnt++;
        return cnt;
    }
}
