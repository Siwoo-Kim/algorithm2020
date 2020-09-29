package com.siwoo.p15000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.Stack;

/**
 * 중복 가능한 N 과 M (선택 요소를 다시 선택 가능)
 *  -> 순열 + 중복 선택
 *  -> N1 * N2 * N3 ... NM -> O(N^M)
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P15651 {
    private static Scanner scanner = new Scanner(System.in);
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M, CNT;

    public static void main(String[] args) throws IOException {
        N = scanner.nextInt();
        M = scanner.nextInt();
        duplicatePermutation(1, new Stack<>(), N, M);
        writer.flush();
    }

    private static void duplicatePermutation(int current, Stack<Integer> stack, int N, int M) throws IOException {
        if (stack.size() == M) {
            StringBuilder sb = new StringBuilder();
            stack.forEach(i -> sb.append(i).append(" "));
            sb.append("\n");
            writer.write(sb.toString());
            CNT++;
            return;
        }
        if (current > N) return;
        for (int i=1; i<=N; i++) {
            stack.push(i);
            duplicatePermutation(current, stack, N, M);
            stack.pop();
        }
    }
}
