package com.siwoo.p1000;


import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;
import com.sun.org.apache.bcel.internal.generic.ALOAD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * (1 ≤ N ≤ 20, |S| ≤ 1,000,000)
 * O(2^N) =  1,048,576
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P1182_SELECTION {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, S, ANSWER;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        S = Integer.parseInt(token.nextToken());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        select(0, S, new Stack<>());
        System.out.println(ANSWER);
    }

    private static void select(int current, final int S, Stack<Integer> stack) {
        if (current == N) {
            if (!stack.isEmpty() &&
                    stack.stream().mapToInt(i -> A[i]).sum() == S)
                ANSWER++;
            return;
        }
        stack.push(current);
        select(current+1, S, stack);
        stack.pop();
        select(current+1, S, stack);
    }
}
