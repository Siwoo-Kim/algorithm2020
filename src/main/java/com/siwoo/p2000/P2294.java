package com.siwoo.p2000;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 동전을 적당히 사용하여 가치 k 을 만들때, 최소 동전의 갯수.
 * 
 * A={1, 5, 12}, K=15
 * 
 * 1 을 기준으로 K 에 따른 총 동전의 갯수를 D[k] 에 저장.
 * 5 을 기준으로 K 에 따른 동전의 갯수 D[k-5] + 1 와 D[k] 을 비교하여 작은 것을 취한다.
 *      이때 D[k-5] + 1 은 5 짜리를 한번 더 사용했을 때 K 을 만드는 동전의 최소 갯수
 * 12 을 기준으로 K 에 따른 동전의 갯수 D[k-12] + 1 와 D[k] 을 비교하여 작은 것을 취한다.
 *      이때 D[k-12] + 1 은 12 짜리를 한번 더 사용했을 때 K 을 만드는 동전의 최소 갯수
 * K 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
 */
public class P2294 {
    private static Scanner scanner = new Scanner(System.in);
    private static int[] A, D;
    private static int N, K;

    public static void main(String[] args) {
        N = scanner.nextInt();
        K = scanner.nextInt();
        A = new int[N];
        D = new int[K+1];
        Arrays.fill(D, -1);
        D[0] = 0;
        for (int i=0; i<N; i++)
            A[i] = scanner.nextInt();
        for (int a: A) {
            for (int k=0; k<=K; k++) {
                boolean ok = k-a >= 0;
                if (ok && D[k-a] != -1) {
                    if (D[k] == -1 || D[k] > D[k-a] + 1)
                        D[k] = D[k-a] + 1;
                }
            }
        }
        System.out.println(D[K]);
    }
}
