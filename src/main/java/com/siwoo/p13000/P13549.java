package com.siwoo.p13000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.*;
import java.util.function.Predicate;

/**
 * 가중치 0,1 일때의 최단 거리 - BFS
 */
@Used(algorithm = Algorithm.BFS)
public class P13549 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, K;

    public static void main(String[] args) {
        N = scanner.nextInt();
        K = scanner.nextInt();
        Predicate<Integer> condition = x -> x >= 0 && x < 1000000;
        Map<Integer, Integer> distance = new HashMap<>();
        Deque<Integer> q = new LinkedList<>();
        q.addLast(N);
        distance.put(N, 0);
        while (!q.isEmpty()) {
            int v = q.pollFirst();
            int d = distance.get(v);
            if (v == K) {
                System.out.println(d);
                return;
            }
            int w1 = v + 1;
            int w2 = v - 1;
            int w3 = v << 1;
            if (condition.test(w3)
                    && !distance.containsKey(w3)) {
                //위치 중요.. 왜? 어차피 가장 먼저 꺼내지는데?
                distance.put(w3, d);
                q.addFirst(w3);
            }
            for (int w: Arrays.asList(w1, w2)) {
                if (condition.test(w)
                        && !distance.containsKey(w)) {
                    distance.put(w, d + 1);
                    q.addLast(w);
                }
            }
        }
    }
}
