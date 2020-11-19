package com.siwoo.p10000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * isPalindrome(i, j) 을 i 부터 시작하여 j 까지 부분 수열 중 팰린드롬의 여부라 가정한다면,
 *  isPalindrome(i, j) = A[i] == A[j] && isPalindrome(i+1, j-1) 
 */
@Used(algorithm = Algorithm.DP)
public class P10942 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Boolean[][] D;
    private static int[] A;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        D = new Boolean[N][N];
        M = Integer.parseInt(reader.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<M; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int s = Integer.parseInt(token.nextToken()) - 1,
                    e = Integer.parseInt(token.nextToken()) - 1;
            sb.append(isPalindrome(s, e)? 1: 0).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static boolean isPalindrome(int s, int e) {
        if (e - s <= 1)
            return D[s][e] = s == e || A[s] == A[e];
        if (D[s][e] != null) return D[s][e];
        boolean ok = A[s] == A[e] 
                && isPalindrome(s + 1, e - 1);
        return D[s][e] = ok;
    }

}