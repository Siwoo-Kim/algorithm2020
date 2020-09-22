package com.siwoo.p10000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Used(algorithm = Algorithm.QUEUE)
public class P10845 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int MAX = 10000, N;

    private static class Queue {
        private int[] queue = new int[MAX+1];
        private int start, end;

        public void push(int e) {
            queue[end++] = e;
        }

        public int pop() {
            if (isEmpty())
                return -1;
            else
                return queue[start++];
        }

        public boolean isEmpty() {
            return start == end;
        }

        public int front() {
            if (isEmpty())
                return -1;
            else
                return queue[start];
        }

        public int back() {
            if (isEmpty())
                return -1;
            else
                return queue[end-1];
        }

        public int size() {
            return end - start;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        Queue queue = new Queue();
        for (int i=0; i<N; i++) {
            String[] commands = reader.readLine().split("\\s+");
            if ("push".equals(commands[0]))
                queue.push(Integer.parseInt(commands[1]));
            if ("pop".equals(commands[0]))
                sb.append(queue.pop()).append("\n");
            if ("size".equals(commands[0]))
                sb.append(queue.size()).append("\n");
            if ("empty".equals(commands[0]))
                sb.append(queue.isEmpty()? 1: 0).append("\n");
            if ("front".equals(commands[0]))
                sb.append(queue.front()).append("\n");
            if ("back".equals(commands[0]))
                sb.append(queue.back()).append("\n");
        }
        System.out.println(sb.toString());
    }
}


























