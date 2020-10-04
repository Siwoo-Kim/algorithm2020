package com.siwoo.util;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 그래프 탐색 알고리즘.
 *  - 임의의 정점 v 에서 시작하여 연결되어 있는 모든 정점 w을 "1번씩" 방문하는 알고리즘.
 *
 * DFS 깊이 우선 탐색 알고리즘
 *  - 스택 사용.
 *  - dfs(v) = O(V+E) = O(E). 모든 정점에 대한 dfs(v) = O(V*E)
 *  - v 방문시 방문했다 표시.
 *
 * BFS 넓이 우선 탐색 알고리즘
 *  - Queue 사용.
 *  - 가중치가 1인 그래프에서의 최단 거리 검
 *  - v 방문 이전에 방문했다 표시. 색
 *  - bfs(v) = O(E).  모든 정점에 대한 bfs(v) = O(V*E)
 */

public abstract class GraphSearch<E> {
    protected Graph<E> graph;

    public static <E> GraphSearch<E> dfs(Graph<E> G) {
        return new DFS<>(G);
    }

    public static <E> GraphSearch<E> bfs(Graph<E> G) {
        return new BFS<>(G);
    }

    /**
     * 정점 v 에 대하여 연결된 모든 정점을 1번씩 순회
     *
     * @param v 방문 정점
     */
    public abstract void traverse(E v);

    private static class BFS<E> extends GraphSearch<E> {

        private BFS(Graph<E> G) {
            super(G);
        }

        @Override
        public void traverse(E v) {
            bfs(v);
        }

        private void bfs(E v) {
            Queue<E> q = new LinkedList<>();
            Set<E> visit = new HashSet<>();
            q.add(v);
            visit.add(v);
            System.out.printf("visit %s%n", v);
            while (!q.isEmpty()) {
                E w = q.poll();
                for (Graph.Edge<E> edge: graph.edgesOf(w))
                    if (!visit.contains(edge.w)) {
                        visit.add(edge.w);
                        System.out.printf("visit %s%n", edge.w);
                        q.add(edge.w);
                    }
            }
        }
    }

    private static class DFS<E> extends GraphSearch<E> {

        @Override
        public void traverse(E v) {
            dfs(v, new HashSet<>());
        }

        public DFS(Graph<E> G) {
            super(G);
        }

        private void dfs(E v, Set<E> visit) {
            visit.add(v);
            System.out.printf("visit %s%n", v);
            for (Graph.Edge<E> edge: graph.edgesOf(v)) {
                if (!visit.contains(edge.w))
                    dfs(edge.w, visit);
            }
        }
    }

    private GraphSearch(Graph<E> graph) {
        this.graph = graph;
    }

    public static void main(String[] args) {
        Graph<Integer> G = new Graph<>();
        G.add(new Graph.Edge<>(1, 5));
        G.add(new Graph.Edge<>(1, 2));
        G.add(new Graph.Edge<>(5, 4));
        G.add(new Graph.Edge<>(5, 2));
        G.add(new Graph.Edge<>(3, 2));
        G.add(new Graph.Edge<>(4, 3));
        G.add(new Graph.Edge<>(6, 4));
        GraphSearch<Integer> dfs = GraphSearch.dfs(G);
        dfs.traverse(1);
        System.out.println();
        GraphSearch<Integer> bfs = GraphSearch.bfs(G);
        bfs.traverse(1);
    }



}