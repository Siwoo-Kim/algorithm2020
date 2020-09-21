package com.siwoo.p9000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

@Used(algorithm = Algorithm.STACK)
public class P9012 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        for (int i=0; i<N; i++) {
            String ps = reader.readLine();
            Stack<Character> stack = new Stack<>();
            boolean vps = true;
            for (char c: ps.toCharArray()) {
                if (c == '(')
                    stack.push(c);
                else {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        vps = false;
                        break;
                    }
                }
            }
            if (!stack.isEmpty())
                vps = false;
            System.out.println(vps ? "YES": "NO");
        }
    }
}
