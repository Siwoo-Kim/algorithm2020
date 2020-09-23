package com.siwoo.p2000;

import java.util.Scanner;

public class P2609 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.printf("%d%n%d", gcd(a, b), lcm(a, b));
    }

    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
