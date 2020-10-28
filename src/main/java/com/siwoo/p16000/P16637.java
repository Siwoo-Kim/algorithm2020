package com.siwoo.p16000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static java.util.stream.Collectors.toList;

public class P16637 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[] A;
    private static char[] OP;
    private static int N;
    private static String S;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        String s = reader.readLine();
        A = new int[(N/2)+1];
        OP = new char[N/2];
        int a = 0, o = 0;
        for (int i=0; i<N; i++) {
            if (i % 2 == 0)
                A[a++] = s.charAt(i) - '0';
            else
                OP[o++] = s.charAt(i);
        }
        int x = select(0, new Stack<>());
        System.out.println(x);
    }

    private static int select(int index, Stack<Integer> stack) {
        if (index >= N/2) {
            int[] precedent = stack.stream().mapToInt(Integer::intValue).toArray();
            int[] nums = Arrays.copyOf(A, A.length);
            char[] ops = Arrays.copyOf(OP, OP.length);
            for (int i=0; i<precedent.length; i++) {
                int sIndex = precedent[i];
                int value = calc(OP[sIndex], A[sIndex], A[sIndex + 1]);
                nums[sIndex] = value;
                ops[sIndex] = '?';
            }
            int value = nums[0];
            for (int i=0; i<ops.length; i++)
                value = calc(ops[i], value, nums[i+1]);
            return value;
        }
        stack.push(index);
        int x = select(index+2, stack);
        stack.pop();
        int y = select(index+1, stack);
        return Math.max(x, y);
    }

    private static int calc(char op, int x1, int x2) {
        if (op == '+') return x1 + x2;
        if (op == '-') return x1 - x2;
        if (op == '*') return x1 * x2;
        else return x1;
    }
}
