package com.siwoo.p1000;

import java.io.*;
import java.util.Scanner;

public class P1248 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static char[][] signs;
    private static int[] results;
    private static int N;

    public static void main(String[] args) {
        N = Integer.parseInt(scanner.nextLine());
        results = new int[N];
        signs = new char[10][10];
        String line = scanner.nextLine();
        int index = 0;
        for (int i=0; i<N; i++)
            for (int j=i; j < N; j++)
                signs[i][j] = line.charAt(index++);
        select(0, results);
    }

    private static boolean select(int current, int[] results) {
        if (current == N) {
            for (int i=0; i<results.length; i++)
                System.out.print(results[i] + " ");
            return true;
        }
        for (int i=-10; i<=10; i++) {
            results[current] = i;
            if (check(current, results)) {  //backtracking
                if (select(current + 1, results))
                    return true;
            }
            results[current] = 0;
        }
        return false;
    }

    public static boolean check(int index, int[] results) {
        for (int i=0; i<=index; i++) {
            int sum = 0;
            for (int j=i; j<=index; j++) {
                sum += results[j];
                if (signs[i][j] == '0' && sum != 0)
                    return false;
                if (signs[i][j] == '+' && sum <= 0)
                    return false;
                if (signs[i][j] == '-' && sum >= 0)
                    return false;
            }
        }
        return true;
    }
}