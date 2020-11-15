package com.siwoo.p2000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P2512 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[] A;
    private static int M, N, THRESHOLD;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        M = Integer.parseInt(reader.readLine());
        int max = Arrays.stream(A).max().getAsInt();
        long s = calc(max, A);
        if (s <= M) {
            System.out.println(max);
            return;
        }
        binarySearch(0, M, A);
        System.out.println(THRESHOLD);
    }

    private static int binarySearch(int left, int right, int[] A) {
        if (left > right) return right;
        int mid = (left + right) / 2;
        long sum = calc(mid, A);
        if (sum <= M) {
            THRESHOLD = Math.max(THRESHOLD, mid);
            return binarySearch(mid+1, right, A);
        }
        else
            return binarySearch(left, mid-1, A);
    }

    private static long calc(int threshold, int[] A) {
        long sum = 0;
        for (int e: A)
            sum += Math.min(e, threshold);
        return sum;
    }
}
