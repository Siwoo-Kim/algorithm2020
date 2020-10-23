package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.DnC)
public class P11728 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[] A, B;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(token.nextToken()),
                M = Integer.parseInt(token.nextToken());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        B = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] t = new int[N + M];
        int left = 0, right = 0, j = 0;
        for (int i=0; i<N+M; i++) {
            if (left >= N)
                t[j++] = B[right++];
            else if (right >= M)
                t[j++] = A[left++];
            else {
                int cmp = Integer.compare(A[left], B[right]);
                if (cmp <= 0)
                    t[j++] = A[left++];
                else
                    t[j++] = B[right++];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<t.length; i++)
            sb.append(t[i]).append(" ");
        System.out.println(sb.toString());
    }
}
