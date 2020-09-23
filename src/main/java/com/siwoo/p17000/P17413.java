package com.siwoo.p17000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

@Used(algorithm = Algorithm.STACK)
public class P17413 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String line = reader.readLine();
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<line.length(); i++) {
            char c = line.charAt(i);
            if (c == '<') {
                append(stack, sb);
                sb.append(c);
                int end = i + 1;
                while (line.charAt(end) != '>')
                    sb.append(line.charAt(end++));
                sb.append(line.charAt(end));
                i = end;
            } else {
                if (c == ' ') {
                    append(stack, sb);
                    sb.append(c);
                } else {
                    stack.push(c);
                }
            }
        }
        append(stack, sb);
        System.out.println(sb);
    }

    private static void append(Stack<Character> stack,
                               StringBuilder sb) {
        while (!stack.isEmpty())
            sb.append(stack.pop());
    }
}
