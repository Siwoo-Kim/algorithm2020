package com.siwoo.p10000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Used(algorithm = Algorithm.STACK)
public class P10828 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final int MAX = 10000;
    private static int N;

    private static class Stack {
        private int[] data = new int[MAX];
        private int size;

        public void push(int e) {
            data[size++] = e;
        }

        public int pop() {
            if (isEmpty())
                return -1;
            int e = top();
            data[--size] = 0;
            return e;
        }

        public int top() {
            return isEmpty()? -1: data[size-1];
        }

        private boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        Stack stack = new Stack();
        for (int i=0; i<N; i++) {
            String[] commands = reader.readLine().split("\\s+");
            if ("push".equals(commands[0])) {
                stack.push(Integer.parseInt(commands[1]));
            } else if ("pop".equals(commands[0])) {
                System.out.println(stack.pop());
            } else if ("size".equals(commands[0])) {
                System.out.println(stack.size());
            } else if ("empty".equals(commands[0])) {
                System.out.println(stack.isEmpty()? 1: 0);
            } else if ("top".equals(commands[0])) {
                System.out.println(stack.top());
            }
        }
    }
}
