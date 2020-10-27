package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

/**
 * 반반 치킨의 갯수만 정하면 양념, 후라이드의 갯수를 구할 수 있으므로
 * 시간안에 풀 수 있다.
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P16917 {
    private static Scanner scanner = new Scanner(System.in);
    private static int A, B, C, X, Y;

    public static void main(String[] args) {
        A = scanner.nextInt();
        B = scanner.nextInt();
        C = scanner.nextInt();
        X = scanner.nextInt();
        Y = scanner.nextInt();
        int answer = X * A + Y * B;
        for (int i=0; i*2<=Math.max(X, Y)*2; i++) {
            int z = i * 2;
            int a = Math.max(X - (z / 2), 0);
            int b = Math.max(Y - (z / 2), 0);
            int sum = 0;
            sum += a * A;
            sum += b * B;
            sum += z * C;
            answer = Math.min(answer, sum);
        }
        System.out.println(answer);
    }
}
