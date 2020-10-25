package com.siwoo.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class P1377 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = new int[N];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=0; i<N; i++) {
            int e = Integer.parseInt(reader.readLine());
            A[i] = e;
            pq.add(e);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<N; i++) {
            int e = pq.poll();
            map.put(e, i);
        }
        int answer = 0;
        for (int i=0; i<N; i++) {
            int index = map.get(A[i]);
            if (index < i)
                answer = Math.max(i - index, answer);
        }
        System.out.println(answer + 1);
    }
}
