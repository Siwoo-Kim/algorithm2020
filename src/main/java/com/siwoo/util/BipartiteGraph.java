package com.siwoo.util;

import java.util.HashSet;
import java.util.Set;

/**
 * 이분 그래프 (Bi*part*ite Graph) 바이 팔타이트
 *  - 인접한 정점끼리 서로 다른 색으로 칠해 모든 정점을 두 가지 색으로만 칠할 수 있는 그래프.
 *  - 같은 그룹의 정점끼리는 반드시 "연결되어 있지 않아야 한다." *
 *  - DFS, BFS 으로 판별가능.
 */
public class BipartiteGraph {

    public <E> boolean isBipartite(Graph<E> G) {
        Set<E> red = new HashSet<>();
        Set<E> black = new HashSet<>();
        for (E v: G.vertexes()) {
            if (!red.contains(v) && !black.contains(v))
                if (!dfs(v, 0, red, black, G))
                    return false;
        }
        return true;
    }

    private <E> boolean dfs(E v, int flip, Set<E> red, Set<E> black, Graph<E> G) {
        if (flip == 0)
            red.add(v);
        else
            black.add(v);
        for (Graph.Edge<E> edge: G.edgesOf(v)) {
            E w = edge.w;
            if (flip == 0 && red.contains(w))
                return false;
            else if (flip == 1 && black.contains(w))
                return false;
            if (!red.contains(w) && !black.contains(w))
                if (!dfs(w, 1-flip, red, black, G)) // v-w 에 대한 모든 간선을 확인해야 .
                    return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Graph<Integer> G = new Graph<>();
        G.add(new Graph.Edge<>(1, 4));
        G.add(new Graph.Edge<>(1, 6));
        G.add(new Graph.Edge<>(2, 4));
        G.add(new Graph.Edge<>(2, 3));
        G.add(new Graph.Edge<>(5, 6));
        System.out.println(new BipartiteGraph().isBipartite(G));
        G.add(new Graph.Edge<>(4, 3));
        System.out.println(new BipartiteGraph().isBipartite(G));
    }
}
