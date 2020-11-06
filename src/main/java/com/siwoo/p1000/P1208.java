package com.siwoo.p1000;

import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static com.siwoo.util.Algorithm.MEET_IN_THE_MIDDLE;

@Used(algorithm = MEET_IN_THE_MIDDLE)
public class P1208 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, S;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        S = Integer.parseInt(token.nextToken());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        long cnt = go(A);
        System.out.println(cnt);
    }

    private static long go(int[] A) {
        int n = N / 2;
        //DIVIDE - all subset's sum for left & right (empty set should be required in both)
        int[] left = go(0, n, A),       // 2^N/2 
                right = go(n, N, A);           // 2^N/2
        Arrays.sort(left);
        Arrays.sort(right);
        for (int i=0; i<right.length/2; i++) {
            int t = right[i];
            right[i] = right[right.length-1-i];
            right[right.length-1-i] = t;
        }
        //merge op by two pointer
        int l = 0, r = 0;
        long cnt = 0;
        while (l < left.length && r < right.length) {
            int sum = left[l] + right[r];
            if (sum == S) { // found
                long leftCnt = 1, rightCnt = 1;
                l++;
                r++;
                while (l < left.length && left[l] == left[l-1]) {
                    l++;
                    leftCnt++;
                }
                while (r < right.length && right[r] == right[r-1]) {
                    r++;
                    rightCnt++;
                }
                cnt += (leftCnt * rightCnt);
            } else if (sum < S)
                l++;
            else
                r++;
        }
        return cnt + (S == 0? -1: 0); //include empty set
    }

    private static int[] go(int start, int end, int[] A) {
        int size = end - start;
        int[] subSum = new int[(1 << size)];
        for (int bits=0; bits<(1 << size); bits++) {    //permutations
            int sum = 0;
            for (int i=0; i<size; i++) {
                if ((bits & (1 << i)) != 0) {
                    sum += A[i+start];
                }
            }
            subSum[bits] = sum;
        }
        return subSum;
    }
}