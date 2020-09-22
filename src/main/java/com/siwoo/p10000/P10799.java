package com.siwoo.p10000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

@Used(algorithm = Algorithm.STACK)
public class P10799 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String line = reader.readLine();
        Stack<Integer> stack = new Stack<>();
        int sticks = 0;
        for (int i=0; i<line.length(); i++) {
            char c = line.charAt(i);
            if (c == '(')
                stack.push(i);
            else {
                int index = stack.pop();
                if (index == i-1)
                    sticks += stack.size();
                else
                    sticks++;
            }
        }
        System.out.println(sticks);
    }
}
