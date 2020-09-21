package com.siwoo.p9000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.*;
import java.util.Stack;

@Used(algorithm = Algorithm.STACK)
public class P9093 {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        for (int i=0; i<N; i++) {
            String[] words = reader.readLine().split("\\s+");
            for (String word: words) {
                Stack<Character> stack = new Stack<>();
                for (Character c: word.toCharArray())
                    stack.push(c);
                while (!stack.isEmpty())
                    writer.write(stack.pop());
                writer.write(" ");
            }
            writer.write("\n");
        }
        writer.flush();
    }

}
