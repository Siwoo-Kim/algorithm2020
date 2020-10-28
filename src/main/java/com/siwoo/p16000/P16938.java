package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P16938 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, L, R, X, CNT;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        L = Integer.parseInt(token.nextToken());
        R = Integer.parseInt(token.nextToken());
        X = Integer.parseInt(token.nextToken());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(A);
        nCr(0, new Stack<>(), A);
        System.out.println(CNT);
    }

    private static void nCr(int index, Stack<Integer> stack, int[] A) {
        if (stack.size() >= 2) {
            if (check(stack))
                CNT++;
        }
        for (int i=index; i<A.length; i++) {
            stack.push(A[i]);
            nCr(i+1, stack, A);
            stack.pop();
        }
    }

    private static boolean check(Stack<Integer> stack) {
        if (stack.get(stack.size()-1) - stack.get(0) < X)
            return false;
        int sum = stack.stream().mapToInt(Integer::intValue).sum();
        return sum >= L && sum <= R;
    }
}
