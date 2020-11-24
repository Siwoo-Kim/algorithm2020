package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P1629 {
    private static Scanner scanner = new Scanner(System.in);
    private static long a, b, MOD;

    public static void main(String[] args) {
        a = scanner.nextInt();
        b = scanner.nextInt();
        MOD = scanner.nextInt();
        System.out.println(pow(a, b) % MOD);
    }

    private static long pow(long a, long b) {
        if (b == 0) return 1;
        if (b == 1) return a;
        if (b % 2 == 0) {
            long r = pow(a, b/2);
            return (r * r) % MOD;
        }
        return (pow(a, 1) * pow(a, b-1)) % MOD;
    }
}
