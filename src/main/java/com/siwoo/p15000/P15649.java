package com.siwoo.p15000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

/**
 * 중복없는 N 과 M (순열)
 *  N=5, M=3
 *  1, 2, 3
 *  1, 2, 4
 *  1, 2, 5
 *  1, 3, 2
 *  1, 3, 4
 *  1, 3, 5
 *  ..
 *
 *  => O(nPm) (1 ≤ M ≤ N ≤ 8) => 8! = 40,320
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P15649 {
    private static Scanner scanner = new Scanner(System.in);
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;

    public static void main(String[] args) throws IOException {
        N = scanner.nextInt();
        M = scanner.nextInt();
        permutation(1, new Stack<>(), N, M);
        writer.flush();
    }

    private static void permutation(int current, Stack<Integer> stack, int N, int M) throws IOException {
        if (stack.size() == M) {
            StringBuilder sb = new StringBuilder();
            stack.forEach(i -> sb.append(i).append(" "));
            sb.append("\n");
            writer.write(sb.toString());
            return;
        }
        if (current > N) return;
        for (int i=current; i<=N; i++) {
            if (stack.contains(i)) continue;
            stack.push(i);
            permutation(current, stack, N, M);
            stack.pop();
        }
    }

}
