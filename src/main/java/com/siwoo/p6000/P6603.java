package com.siwoo.p6000;

import java.io.*;
import java.util.Stack;
import java.util.stream.IntStream;

public class P6603 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int K;
    private static int[] S;

    public static void main(String[] args) throws IOException {
        while (true) {
            String[] data = reader.readLine().split("\\s+");
            K = Integer.parseInt(data[0]);
            if (K == 0) {
                writer.flush();
                return;
            }
            S = IntStream.rangeClosed(1, K).map(i -> Integer.parseInt(data[i])).toArray();
            combination(0, new Stack<>(), K, 6);
            writer.write("\n");
        }
    }

    private static void combination(int start, Stack<Integer> stack, int K, int M) throws IOException {
        if (stack.size() == M) {
            StringBuilder sb = new StringBuilder();
            stack.stream().map(i -> S[i]).forEach(i -> sb.append(i).append(" "));
            sb.append("\n");
            writer.write(sb.toString());
            return;
        }
        for (int i=start; i<K; i++) {
            stack.push(i);
            combination(i+1, stack, K, M);
            stack.pop();
        }
    }
}
