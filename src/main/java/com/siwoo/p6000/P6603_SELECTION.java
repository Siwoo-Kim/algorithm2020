package com.siwoo.p6000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P6603_SELECTION {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int K;
    private static int[] S;

    public static void main(String[] args) throws IOException {
        while (true) {
            String[] line = reader.readLine().split("\\s+");
            K = Integer.parseInt(line[0]);
            if (K == 0) return;
            S = new int[K];
            for (int i=1; i<line.length; i++)
                S[i-1] = Integer.parseInt(line[i]);
            select(0, K, new Stack<>(), S);
            System.out.println();
        }
    }

    private static void select(int current, int K, Stack<Integer> stack, int[] S) {
        if (stack.size() == 6) {
            StringBuilder sb = new StringBuilder();
            stack.stream().mapToInt(i -> S[i]).forEach(i -> sb.append(i).append(" "));
            System.out.println(sb);
            return;
        }
        if (current == K) return;
        stack.push(current);
        select(current+1, K, stack, S);
        stack.pop();
        select(current+1, K, stack, S);
    }

}
