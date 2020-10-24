package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

@Used(algorithm = Algorithm.DnC)
public class P1074 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, X, Y, answer;

    public static void main(String[] args) {
        N = 1 << scanner.nextInt();
        X = scanner.nextInt();
        Y = scanner.nextInt();
        dnc(N, 0, 0);
        System.out.println(answer);
    }

    private static boolean dnc(int n, int x, int y) {
        if (n <= 2) {
            for (int i=x; i<x+n; i++)
                for (int j=y; j<y+n; j++) {
                    if (i == X && j == Y)
                        return true;
                    else
                        answer++;
                }
            return false;
        }
        int unit = n / 2;
        if (dnc(unit, x, y))
            return true;
        if (dnc(unit, x, y+unit))
            return true;
        if (dnc(unit, x+unit, y))
            return true;
        if (dnc(unit, x+unit, y+unit))
            return true;
        return false;
    }

}
