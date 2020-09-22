package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.QUEUE)
public class P1158 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K;

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());
        Queue<Integer> queue = new LinkedList<>();
        for (int i=1; i<=N; i++)
            queue.add(i);
        StringBuilder sb = new StringBuilder("<");
        while (!queue.isEmpty()) {
            for (int i=1; i<K; i++)
                queue.add(queue.poll());
            sb.append(queue.poll());
            if (queue.size() != 0)
                sb.append(", ");
        }
        sb.append(">");
        System.out.println(sb);
    }
}
