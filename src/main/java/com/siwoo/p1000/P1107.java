package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *  O(N)
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P1107 {
    private static int N, MIN = Integer.MAX_VALUE;
    private static Set<Integer> S = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        int T = scanner.nextInt();
        for (int i=0; i<T; i++)
            S.add(scanner.nextInt());
        MIN = N - 100;
        if (MIN < 0)
            MIN = -MIN;
        for (int i=0; i<=1000000; i++) {
            if (!contains(i)) {
                int answer = Math.abs(i-N) + Integer.toString(i).length();
                MIN = Math.min(answer, MIN);
            }
        }
        System.out.println(MIN);
    }

    private static boolean contains(int i) {
        if (i == 0) return S.contains(i);
        while (i != 0) {
            int mod = i % 10;
            if (S.contains(mod))
                return true;
            i /= 10;
        }
        return false;
    }
}