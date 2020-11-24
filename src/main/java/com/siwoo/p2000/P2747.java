package com.siwoo.p2000;

import java.util.Scanner;

public class P2747 {
    private static Long[] D = new Long[90+1];

    public static void main(String[] args) {
        System.out.println(fibonacci(new Scanner(System.in).nextInt()));
    }

    private static long fibonacci(int i) {
        if (D[i] != null) return D[i];
        if (i == 0 || i == 1) return D[i] = (long) i;
        return D[i] = fibonacci(i-1) + fibonacci(i - 2);
    }
}
