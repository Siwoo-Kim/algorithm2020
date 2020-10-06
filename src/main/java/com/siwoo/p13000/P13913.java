package com.siwoo.p13000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.*;
import java.util.function.Predicate;

/**
 *  최단 거리 경로.
 *      bfs(p) -> for (w edgeOf(p) pathTo[w] = p
 *      reverse(bfs(p))
 */
@Used(algorithm = Algorithm.BFS)
public class P13913 {
    private static Scanner scanner = new Scanner(System.in);
    private static final int MAX = 100000;
    private static int N, K;

    public static void main(String[] args) {
        N = scanner.nextInt();
        K = scanner.nextInt();
        int[] pathTo = new int[MAX+1];
        Predicate<Integer> condition = x -> x >= 0 && x <= MAX;
        Map<Integer, Integer> distance = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        distance.put(N, 0);
        while (!q.isEmpty()) {
            int v = q.poll();
            int d = distance.get(v);
            if (v == K) {
                System.out.println(d);
                Stack<Integer> stack = new Stack<>();
                while (v != N) {
                    stack.push(v);
                    v = pathTo[v];
                }
                StringBuilder sb = new StringBuilder();
                sb.append(N).append(" ");
                while (!stack.isEmpty())
                    sb.append(stack.pop()).append(" ");
                System.out.println(sb.toString());
                return;
            }
            int w1 = v - 1;
            int w2 = v + 1;
            int w3 = v << 1;
            for (int w: Arrays.asList(w1, w2, w3)) {
                if (condition.test(w) &&
                        !distance.containsKey(w)) {
                    pathTo[w] = v;
                    distance.put(w, d + 1);
                    q.add(w);
                }
            }
        }
    }
}
