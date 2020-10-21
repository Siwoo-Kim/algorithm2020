package com.siwoo.p10000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 3 의 배수 확인 법 -> 각 자리수를 더한 값이 삼의 배수 -> 즉 삼의 배수인 수 x 은 자리를 바꿔도 삼의 배수. (덧셈은 순서를 바꿔도 변하지 않으므로)
 * 10 의 배수 확인 법 -> 끝자리 수가 0
 *
 * N 이 10^5 아니라, N 을 이루는 숫자의 갯수가 10^5 => 순열 문제해결 실패
 *
 */
@Used(algorithm = Algorithm.GREEDY)
public class P10610 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static char[] A;

    public static void main(String[] args) throws IOException {
        A = reader.readLine().toCharArray();
        Arrays.sort(A);
        for (int i=0; i<A.length/2; i++)
            swap(i, A.length-1-i, A);
        boolean zero = false;
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<A.length; i++) {
            int d = A[i] - '0';
            zero = zero || d == 0;
            sum += d;
            sb.append(A[i]);
        }

        if (zero && sum % 3 == 0)
            System.out.println(sb.toString());
        else
            System.out.println(-1);
    }

    private static boolean nextPermutation(char[] P) {
        int i = P.length-1;
        while (i > 0 && P[i] >= P[i-1])
            i--;
        if (i == 0) return false;
        int j = P.length-1;
        while (P[i-1] <= P[j]) j--;
        swap(i-1, j, P);
        j = P.length - 1;
        while (j > i)
            swap(i++, j--, P);
        return true;
    }

    private static void swap(int i, int j, char[] a) {
        char t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

}
