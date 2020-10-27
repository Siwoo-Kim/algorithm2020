package com.siwoo.p16000;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class P16922 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N;
    private static Set<Long> set = new HashSet<>();

    public static void main(String[] args) {
        N = scanner.nextInt();

        for (int i=0; i<=N; i++)
            for (int v=0; v<=N-i; v++)
                for (int x=0; x<=N-i-v; x++) {
                    int l = N - i - v - x;
                    long value = 1L * i + 5 * v + 10 * x + 50 * l;
                    set.add(value);
                }
        System.out.println(set.size());
    }

}
