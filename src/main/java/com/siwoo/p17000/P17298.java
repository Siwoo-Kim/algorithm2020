package com.siwoo.p17000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.STACK)
public class P17298 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[] sequence;
    private static int[] answers;
    public static void main(String[] args) throws IOException {
        reader.readLine();
        sequence = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        answers = new int[sequence.length];
        Arrays.fill(answers, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<sequence.length; i++) {
            while (!stack.isEmpty()
                    && sequence[stack.peek()] < sequence[i] )
                answers[stack.pop()] = sequence[i];
            stack.push(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int answer : answers)
            sb.append(answer).append(" ");
        System.out.println(sb);
    }
}
