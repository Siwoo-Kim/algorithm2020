package com.siwoo.p15000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.Stack;

/**
 * 순서에 의미가 없고 중복없는 N 과 M (오름차순) -> 조합
 * O(nCm)
 * select 알고리즘으로도 풀 수 있다. -> 2^N
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P15650 {
    private static Scanner scanner = new Scanner(System.in);
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;

    public static void main(String[] args) throws IOException {
        N = scanner.nextInt();
        M = scanner.nextInt();
        combination(1, new Stack<>(), N, M);
        writer.flush();
    }

    private static void combination(int current, Stack<Integer> stack, int N, int M) throws IOException {
        if (stack.size() == M) {
            StringBuilder sb = new StringBuilder();
            stack.forEach(i -> sb.append(i).append(" "));
            sb.append("\n");
            writer.write(sb.toString());
            return;
        }
        if (current > N) return;
        for (int i=current; i<=N; i++) {
            stack.push(i);
            combination(i+1, stack, N, M);
            stack.pop();
        }
    }
}
