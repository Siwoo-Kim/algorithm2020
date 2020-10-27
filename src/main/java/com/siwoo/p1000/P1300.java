package com.siwoo.p1000;

import java.util.Scanner;

/**
 * N * N 행렬 A 을 일차원 배열 B 에 오름차순 했을 때, B[k] 번째 수.
 *
 * 나올 수 있는 값 - 1 ~ N*N
 *
 * N = 5 일때, K = 3 이라면
 * 1, 2, 3, 4, 5        -> i = 1 일때, 1, 2, 3    (K/i)
 * 2, 4, 6, 8, 10       -> i = 2 일때, 2
 * 3, 6, 9, 12, 15      -> i = 3 일때, 1
 * 4, 8, 12, 16, 20
 * 5, 10, 15, 20, 25
 *
 * 정렬된 배열에서 K 인덱 = B[K 보다 작은 수의 갯수] (left children).
 *
 * 위를 통해, K 보다 작은 수들의 갯수를 알 수 있을때
 * 이분탐색(1, N*N) 을 통해 답을 구
 */
public class P1300 {
    private static Scanner scanner = new Scanner(System.in);
    private static long N, B, ANSWER = 0;

    public static void main(String[] args) {
        N = scanner.nextLong();
        B = scanner.nextLong();
        long num = bs(1, N*N, B);
        System.out.println(num);
    }

    private static long bs(long min, long max, long B) {
        if (min > max) return ANSWER;
        long mid = (min + max) / 2;
        long nth = calc(mid);
        if (nth >= B) {
            ANSWER = mid;
            bs(min, mid-1, B);
        } else {
            bs(mid+1, max, B);
        }
        return ANSWER;
    }

    private static long calc(long k) {
        long cnt = 0;
        for (long i=1; i<=N; i++)
            cnt += Math.min(N, k/i);
        return cnt;
    }

}