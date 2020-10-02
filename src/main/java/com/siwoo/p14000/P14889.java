package com.siwoo.p14000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.IntStream;

@Used(algorithm = Algorithm.BACK_TRACKING)
public class P14889 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int S[][];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        S = new int[N][N];
        for (int i=0; i<N; i++)
            S[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        System.out.println(select(0, new Stack<>()));
    }

    private static int select(int index, Stack<Integer> stack) {
        if (stack.size() == N/2) {  //backtracking (실제로는 모든 경우를 다 만들지 않으므로)
            int t1 = 0, t2 = 0;
            int[] s = IntStream.range(0, N).filter(stack::contains).toArray();
            int[] l =  IntStream.range(0, N).filter(i -> !stack.contains(i)).toArray();
            for (int i=0; i<N/2; i++) {
                for (int j=0; j<N/2; j++) {
                    if (i == j) continue;
                    t1 += S[s[i]][s[j]];
                    t2 += S[l[i]][l[j]];
                }
            }
            return Math.abs(t1 - t2);
        }
        if (index == N) return Integer.MAX_VALUE;
        stack.push(index);
        int answer = select(index+1, stack);
        if (answer == 0)    //backtracking
            return 0;
        stack.pop();
        return Math.min(answer, select(index+1, stack));
    }
}
