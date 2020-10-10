package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 대표적 순열 문제.
 * O(N-2!*(N-2)*2N) = 6,451,200
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P16198 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int W[];
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        visit = new boolean[N];
        W = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        long answer = select(N-2, new Stack<>());
        System.out.println(answer);
    }

    private static long select(int THRESHOLD, Stack<Integer> stack) {
        if (stack.size() == THRESHOLD) {
            Stack<Integer> copy = (Stack<Integer>) stack.clone();   //sync make slow
            Set<Integer> visit = new HashSet<>();
            int sum = 0;
            while (!copy.isEmpty()) {
                int v = copy.pop();
                int start = v-1;
                while (start > 0 && visit.contains(start))
                    start--;
                int end = v+1;
                while (end < N && visit.contains(end))
                    end++;
                sum += W[start] * W[end];
                visit.add(v);
            }
            return sum;
        }
        long max = 0;
        for (int i=1; i<N-1; i++) {
            if (!visit[i]) {
                visit[i] = true;
                stack.push(i);
                max = Math.max(select(THRESHOLD, stack), max);
                stack.pop();
                visit[i] = false;
            }
        }
        return max;
    }


}
