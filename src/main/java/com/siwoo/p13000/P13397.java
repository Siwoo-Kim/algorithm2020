package com.siwoo.p13000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P13397 {
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
            right = Math.max(right, A[i]);
        }
        bs(left, right, M, A);
        System.out.println(ANSWER);
    }

    private static void bs(int left, int right, int M, int[] A) {
        if (left > right) return;
        int mid = (left + right) / 2;
        int x = numberOfSections(mid, A);
        if (x <= M) {
            ANSWER = mid;
            bs(left, mid-1, M, A);
        } else {
            bs(mid+1, right, M, A);
        }
    }

    private static int numberOfSections(int MAX, int[] A) {
        int min = A[0], max = A[0], cnt = 1;
        for (int i=1; i<A.length; i++) {
            min = Math.min(min, A[i]);
            max = Math.max(max, A[i]);
            if (max - min > MAX) {
                cnt++;
                min = A[i];
                max = A[i];
            }
        }
        return cnt;
    }
}
