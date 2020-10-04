package com.siwoo.util;

import java.util.HashSet;
import java.util.Set;

/**
 * 싸이클 탐지 알고리즘.
 *  싸이클 탐지 알고리즘 이전에 이해해야할 사항.
 *      1. 무방향 그래프는 항상 싸이클이 존재한다. 간선 a-b 은 a->b, b->a 을 의미하므로
 *          => 무방향 그래프에서 길이가 2인 싸이클의 탐지 여부는 무의미하다.
 *      2. 무방향 그래프에서 싸이클은 3이상이여야 의미가 있다.
 *
 * 무방향 그래프의 싸이클 탐지
 *  1. dfs with visit
 *   이전 정점과 다른 정점으로 이동했을 때,
 *   이미 방문한 정점을 또 방문했으면 길이가 3 이상인 싸이클은 무조건 존재한다.
 *   = A -> B, B <- A 의 싸이클은 확인할 수 없다.
 *   = A -> B, B -> C, C -> A 형태의 싸이클만 확인할 수 있다.
 *
 *  2. Union Find
 *
 *  방향 그래프의 싸이클 탐지.
 *  1. dfs with stack visit
 *    스택에 쌓여있는 정점을 다시 방문했다면 싸이클이 존재.
 */
public abstract class Cycle {
    public abstract <E> boolean hasCycle(Graph<E> G);

    private static class DFSCycle extends Cycle {

        @Override
        public <E> boolean hasCycle(Graph<E> G) {
            Set<E> visit = new HashSet<>();
            for (E v: G.vertexes())
                if (!visit.contains(v)
                        && cycle(v, null, visit, G))
                    return true;
            return false;
        }

        private <E> boolean cycle(E v, E p, Set<E> visit, Graph<E> G) {
            if (visit.contains(v)) return true;
            visit.add(v);
            for (Graph.Edge<E> edge: G.edgesOf(v)) {
                if (!edge.w.equals(p)) {    //이전 방문한 정점이 아니라면 방문.
                    if (cycle(edge.w, v, visit, G))
                        return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Graph<Integer> G = new Graph<>();
        G.add(new Graph.Edge<>(1, 2));
        G.add(new Graph.Edge<>(2, 3));
        Cycle cycle = new DFSCycle();
        System.out.println(cycle.hasCycle(G));

        G.add(new Graph.Edge<>(3, 4));
        System.out.println(cycle.hasCycle(G));

        G.add(new Graph.Edge<>(4, 2));
        System.out.println(cycle.hasCycle(G));
    }
}
