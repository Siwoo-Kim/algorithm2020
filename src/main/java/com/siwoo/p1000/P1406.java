package com.siwoo.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P1406 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    public static void main(String[] args) throws IOException {
        String s =  reader.readLine();
        Stack<Character> left = new Stack<>();
        for (char c: s.toCharArray())
            left.push(c);
        Stack<Character> right = new Stack<>();
        N = Integer.parseInt(reader.readLine());

        for (int i=0; i<N; i++) {
            String[] command = reader.readLine().split("\\s+");
            if ("L".equals(command[0]) && !left.isEmpty())
                right.push(left.pop());
            if ("D".equals(command[0]) && !right.isEmpty())
                left.push(right.pop());
            if ("B".equals(command[0]) && !left.isEmpty())
                left.pop();
            if ("P".equals(command[0]))
                left.push(command[1].charAt(0));
        }
        StringBuilder sb = new StringBuilder();
        while (!left.isEmpty())
            sb.append(left.pop());
        sb = sb.reverse();
        while (!right.isEmpty())
            sb.append(right.pop());
        System.out.println(sb.toString());
    }
}
