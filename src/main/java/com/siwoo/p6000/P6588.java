package com.siwoo.p6000;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

// 실패.. 왜?
public class P6588 {
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static Scanner scanner = new Scanner(System.in);
    private static int MAX = 1000000;
    private static boolean[] primes = new boolean[MAX+1];

    static {
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for (int i=2; i*i<=MAX; i++) {
            if (primes[i])
                for (int j=i+i; j<=MAX; j+=i)
                    primes[j] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            int n = scanner.nextInt();
            if (n == 0) {
                writer.flush();
                return;
            }
            for (int i=3; i<=n; i+=2) {
                if (primes[n-i]) {
                    writer.write(String.format("%d = %d + %d%n", n, i, n-i));
                    break;
                }
            }
        }
    }
}