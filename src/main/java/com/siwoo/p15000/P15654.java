package com.siwoo.p15000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P15654 {
    private static Scanner scanner = new Scanner(System.in);
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;
    private static int S[];

    public static void main(String[] args) throws IOException {
        N = scanner.nextInt();
        M = scanner.nextInt();
        scanner.nextLine();
        S = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(S);
        permutation(new Stack<>(), N, M);
        writer.flush();
    }

    private static void permutation(Stack<Integer> stack, int N, int M) throws IOException {
        if (stack.size() == M) {
            StringBuilder sb = new StringBuilder();
            stack.stream().mapToInt(i -> S[i]).forEach(i -> sb.append(i).append(" "));
            sb.append("\n");
            writer.write(sb.toString());
            return;
        }
        for (int i=0; i<N; i++) {
            if (stack.contains(i)) continue;
            stack.push(i);
            permutation(stack, N, M);
            stack.pop();
        }
    }
}
