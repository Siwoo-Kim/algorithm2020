package com.siwoo.util;

import java.util.*;

/**
 * 그래프 (Graph)
 *  그래프와 알고리즘 - 문제을 식별하여 그래프로 표현하여 해결.
 *
 *  정점: vertex
 *  간선: edge
 *  경로: path (간선의 연속)
 *  사이클: cycle 정점 v 에서 정점 v 으로 연결된 경로. (v -> ? -> v)
 *  단순 경로, 단순 사이클: 같은 정점을 두 번 이상 방문하지 않는 경로/사이
 *  가중치: weight 이동의 cost
 *  차수: degree 해당 정점에 연결된 간선의 갯수. (방향 그래프에선 in-degree, out-degree 로 구분)
 *
 *  방향 그래프 (Directed Graph)
 *  무방향 그래프 (Undirected Graph)
 *
 *  그래프 표현 방식.
 *      1. 인접 행렬. adjacency-matrix, O(v) = 한 정점 연결된 모든 간선 접근
 *      2. 인접 리스트.  adjacency-list, O(degree(v)) =  한 정점 연결된 모든 간선 접근
 */
public class Graph<E> {
    private Map<E, Set<Edge<E>>> G = new HashMap<>();

    public Set<E> vertexes() {
        return G.keySet();
    }

    public Set<Edge<E>> edgesOf(E v) {
        return G.get(v);
    }

    public static class Edge<E> {
        public final E v, w;

        public Edge(E v, E w) {
            this.v = v;
            this.w = w;
        }

        public Edge<E> reverse() {
            return new Edge<>(w, v);
        }

        @Override
        public String toString() {
            return v + " -> " + w;
        }
    }

    public void add(Edge<E> edge) {
        addEdge(edge);
        addEdge(edge.reverse());
    }

    public int degreeOf(E e) {
        return G.get(e) == null? 0: G.get(e).size();
    }

    private void addEdge(Edge<E> edge) {
        G.computeIfAbsent(edge.v, k -> new HashSet<>());
        G.get(edge.v).add(edge);
    }

    public static void main(String[] args) {
        Graph<String> G = new Graph<>();
        G.add(new Edge<>("A", "B"));
        G.add(new Edge<>("A", "C"));
        G.add(new Edge<>("C", "B"));

        for (Edge<String> edge: G.G.get("A"))
            System.out.println(edge);
        for (Edge<String> edge: G.G.get("C"))
            System.out.println(edge);
    }

}
