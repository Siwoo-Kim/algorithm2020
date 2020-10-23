package com.siwoo.p12000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

/**
 * A 가 a 개, B 가 b 개 있으면 0 ~ A * B 을 만족하는 문자열을 언제나 만들 수 있다.
 * 반대로 N/2 개의 a 개 N-a 의 b 개가 만들 수 있는 최대 쌍의 갯수이다.
 *
 */
@Used(algorithm = Algorithm.GREEDY)
public class P12970 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int N = scanner.nextInt(),
                K = scanner.nextInt();
        int a = N / 2,
                b = N - a;
        if (a * b < K) {
            System.out.println(-1);
            return;
        }
        for (a=0; a<=N; a++) {
            b = N - a;
            if (a * b < K) continue;
            int[] cnt = new int[b+1];
            for (int i=0; i<a; i++) {
                int value = Math.min(b, K);
                cnt[value]++;
                K -= value;
            }
            StringBuilder sb = new StringBuilder();
            for (int i=b; i>=0; i--) {
                for (int j=0; j<cnt[i]; j++)
                    sb.append("A");
                if (i > 0)
                    sb.append("B");
            }
            System.out.println(sb);
            return;
        }
    }
}
