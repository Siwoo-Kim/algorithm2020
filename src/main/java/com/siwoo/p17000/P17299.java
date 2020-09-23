package com.siwoo.p17000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

@Used(algorithm = Algorithm.STACK)
public class P17299 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int MAX = 1000000;
    private static int[] sequence;
    private static int[] counts = new int[MAX+1];
    private static int[] answers;

    public static void main(String[] args) throws IOException {
        reader.readLine();
        sequence = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        answers = new int[sequence.length];
        for (int i=0; i<sequence.length; i++) {
            counts[sequence[i]]++;
            answers[i] = -1;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<sequence.length; i++) {
            while (!stack.isEmpty()
                    && counts[sequence[stack.peek()]] < counts[sequence[i]])
                answers[stack.pop()] = sequence[i];
            stack.push(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int answer: answers)
            sb.append(answer).append(" ");
        System.out.println(sb);
    }
}
