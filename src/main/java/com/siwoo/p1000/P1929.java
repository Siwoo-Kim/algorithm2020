package com.siwoo.p1000;

import java.util.Arrays;
import java.util.Scanner;

public class P1929 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int from = scanner.nextInt();
        int to = scanner.nextInt();
        boolean[] primes = primes(to);
        for (int i=from; i<=to; i++) {
            if (primes[i])
                System.out.println(i);
        }
    }

    private static boolean[] primes(int n) {
        boolean[] primes = new boolean[n+1];
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for (int i=2; i*i<=n; i++) {
            if (primes[i]) {
                for (int j=i+i; j<=n; j+=i)
                    primes[j] = false;
            }
        }
        return primes;
    }
}
