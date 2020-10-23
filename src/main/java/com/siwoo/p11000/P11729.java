package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

@Used(algorithm = Algorithm.DnC)
public class P11729 {
    private static int N, CNT;

    public static void main(String[] args) {
        N = new Scanner(System.in).nextInt();
        StringBuilder sb = new StringBuilder();
        hanoi(sb, N, 1, 3);
        System.out.println(CNT);
        System.out.println(sb);
    }

    private static void hanoi(StringBuilder sb, int n, int from, int to) {
        if (n == 0) return;
        int other = other(from, to);
        CNT++;
        hanoi(sb, n-1, from, other);
        sb.append(from).append(" ").append(to).append("\n");
        hanoi(sb, n-1, other, to);
    }

    private static int other(int x, int y) {
        if (x + y == 3) return 3;
        if (x + y == 4) return 2;
        return 1;
    }
}
