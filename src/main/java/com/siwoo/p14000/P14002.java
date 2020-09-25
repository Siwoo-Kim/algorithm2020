package com.siwoo.p14000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.IntStream;

@Used(algorithm = Algorithm.DP)
public class P14002 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int[] D, P, S;
    private static int N;

    public static void main(String[] args) {
        N = scanner.nextInt();
        D = new int[N];
        P = new int[N];
        S = new int[N];
        Arrays.fill(P, -1);
        for (int i=0; i<N; i++)
            S[i] = scanner.nextInt();
        for (int i=0; i<N; i++)
            dp(i);
        int maxIndex = IntStream.range(0, N)
                .reduce((a, b) -> D[a] > D[b]? a: b)
                .getAsInt();
        System.out.println(D[maxIndex]);

        Stack<Integer> stack = new Stack<>();
        int start = maxIndex;
        while (start != -1) {
            stack.push(S[start]);
            start = P[start];
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop()).append(" ");
        System.out.println(sb.toString());
    }

    private static int dp(int index) {
        if (D[index] != 0) return D[index];
        D[index] = 1;
        for (int i=0; i<index; i++)
            if (S[index] > S[i] && D[index] < dp(i) + 1) {
                D[index] = dp(i) + 1;
                P[index] = i;   //역추적
            }
        return D[index];
    }
}
