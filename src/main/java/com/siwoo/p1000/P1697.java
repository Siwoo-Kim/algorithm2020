package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.*;
import java.util.function.Predicate;

/**
 * 가중치 1 인 그래프에서 최단 거리 -> BFS
 * O(E) -> 100000 * 3
 */
@Used(algorithm = Algorithm.BFS)
public class P1697 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, K;

    public static void main(String[] args) {
        N = scanner.nextInt();
        K = scanner.nextInt();
        Map<Integer, Integer> distance = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        distance.put(N, 0);
        q.add(N);
        Predicate<Integer> condition = x -> x >= 0 && x <= 100000;
        while (!q.isEmpty()) {
            int v = q.poll();
            if (v == K) {
                System.out.println(distance.get(v));
                return;
            }
            int d = distance.get(v);
            int w1 = v - 1;
            int w2 = v + 1;
            int w3 = v << 1;
            for (int w: Arrays.asList(w1, w2, w3)) {
                if (condition.test(w) && !distance.containsKey(w)) {
                    q.add(w);
                    distance.put(w, d + 1);
                }
            }
        }
    }
}
