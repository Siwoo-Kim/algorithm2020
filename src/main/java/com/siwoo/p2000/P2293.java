package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Arrays;
import java.util.Scanner;

/**
 * {1, 2, 5}, K = 10 일때,
 * K 을 만들 수 있는 경우의 수. (중복 사용 가능, 조합.)
 * 
 * 1 에 대한 경우의 수를 구한다.
 * 이후, 2 에 대한 경우의 수와 1 에 대한 경우의 수를 합친다.
 * 위의 경우의 수와 5 에 대한 경우의 수를 합친다.
 * 
 * K  = 1 2 3 4 5 6 7 8 9 10
 * 1) = 1 1 1 1 1 1 1 1 1 1
 * 2) = 1 2 2 3 3 4 4 5 5 6
 * 3) = 0 0 0 0 4 5 6 7 8 10  
 */
@Used(algorithm = Algorithm.DP)
public class P2293 {
    private static Scanner scanner = new Scanner(System.in);
    private static int[] A;
    private static int[] D;
    private static int N, K;
    
    public static void main(String[] args) {
        N = scanner.nextInt();
        K = scanner.nextInt();
        A = new int[N];
        for (int i=0; i<N; i++)
            A[i] = scanner.nextInt();
        Arrays.sort(A);
        D = new int[K+1];
        D[0] = 1;
        for (int a: A) {
        for (int k=0; k<=K; k++) {
                if (k-a >= 0)
                    D[k] += D[k-a];
            }
        }
        System.out.println(D[K]);
    }
}
