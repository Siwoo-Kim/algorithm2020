package com.siwoo.p1000;

import java.util.Scanner;

public class P1676 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(factorial(scanner.nextInt()));
    }

    public static int factorial(int n) {
        if (n == 0) return 0;
        int cnt = 0, tmp = n;
        while (tmp % 5 == 0) {
            tmp /= 5;
            cnt++;
        }
        return factorial(n-1) + cnt;
    }
}
