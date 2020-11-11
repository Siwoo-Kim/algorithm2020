package com.siwoo.p6000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.BFS)
public class P6549 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int N = Integer.parseInt(token.nextToken());
            if (N == 0) {
                System.out.println(sb);
                return;
            }
            Stack<Tuple> stack = new Stack<>();
            long max = 0;
            for (long i=0; i<=N; i++) {
                long h = i == N? 0:  Long.parseLong(token.nextToken());
                if (stack.isEmpty() || stack.peek().height <= h)
                    stack.push(new Tuple(i, h));
                else {
                    while (!stack.isEmpty() 
                            && stack.peek().height > h) {
                        Tuple tuple = stack.pop();
                        long width = stack.isEmpty()? i: i - stack.peek().index - 1;
                        max = Math.max(tuple.height * width, max);
                    }
                    stack.push(new Tuple(i, h));
                }
            }
            sb.append(max).append("\n");
        }
    }
    
    private static class Tuple {
        private long height;
        private long index;

        public Tuple(long index, long height) {
            this.index = index;
            this.height = height;
        }
    }
}
