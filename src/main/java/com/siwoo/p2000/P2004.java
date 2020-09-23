package com.siwoo.p2000;

import java.util.Scanner;

public class P2004 {
    private static Scanner scanner = new Scanner(System.in);

    // nCm = n! / (n-m)! (m)!
    // i 에 대한 0 의 갯수 (2, 5) 쌍을 이루는 약수 = f(i)
    // nCm 의 0 의 갯수 = f(n!) - f(n-m!) + f(m!)
    public static void main(String[] args) {
        long n = scanner.nextLong(),
                m = scanner.nextLong();
        long two = 0, five = 0;
        for (long i=2; i<=n; i*=2) two += n/i;
        for (long i=2; i<=n-m; i*=2) two -= (n-m)/i;
        for (long i=2; i<=m; i*=2) two -= m/i;
        for (long i=5; i<=n; i*=5) five += n/i;
        for (long i=5; i<=n-m; i*=5) five -= (n-m)/i;
        for (long i=5; i<=m; i*=5) five -= m/i;
        System.out.println(Math.min(two,five));
    }
}
