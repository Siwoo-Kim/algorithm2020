package com.siwoo.p15000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.Stack;

/**
 * 순서에 의미가 없고 중복 가능한 오름차순 N 과 M -> 중복 조합 (순서의 의미는 없지만 중복 선택 가능한)
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P15652 {
    private static Scanner scanner = new Scanner(System.in);
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;

    public static void main(String[] args) throws IOException {
        N = scanner.nextInt();
        M = scanner.nextInt();
        duplicateCombination(1, new Stack<Integer>(), N, M);
        writer.flush();
    }

    private static void duplicateCombination(int current, Stack<Integer> stack, int N, int M) throws IOException {
        if (stack.size() == M) {
            StringBuilder sb = new StringBuilder();
            stack.forEach(i -> sb.append(i).append(" "));
            sb.append("\n");
            writer.write(sb.toString());
            return;
        }
        for (int i=current; i<=N; i++) {
            stack.push(i);
            duplicateCombination(i, stack, N, M);
            stack.pop();
        }
    }
}
