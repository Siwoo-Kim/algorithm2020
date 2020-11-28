package com.siwoo.util;

import javax.xml.ws.Holder;
import java.util.*;

/**
 * Minimum Spanning Tree (최소 스패닝 트리)
 * 
 *  Spanning Tree 란?
 *      가중치 그래프 G 에서 간선의 부분 집합 E 로 만든 트리 T
 *      
 *      T = 순환이 없는 그래프
 *      
 *  Minimum Spanning Tree ?
 *      가중치 그래프 G 의 Spanning Tree 중 간선의 가중치의 합이 최소인 트리
 *      "모든 정점을 방문"해야할 때, "최소 거리"를 알 수 있는 알고리즘.
 *      
 *      MST v-w 가 최소 거리라는 것은 아니다.
 * 
 *  MST 을 찾는 알고리즘
 *      1. Prim 프림
 *      2. Kruskal 크루스칼
 *      
 *      Prim 알고리즘.
 *          트리의 특성을 이용한 MST 을 찾는 알고리즘.
 *          
 *          트리의 특성.
 *              1. 두 정점을 연결하는 간선 하나를 추가하면 "한 개의 순환"이 만들어진다.
 *              2. 트리에서 간선 하나를 제거하면 두 개의 부분 트리로 나누어진다.
 *              
 *      1. 정점을 선택된 집합 s 과 선택되지 않은 집합 u 로 나눈다.
 *      2. 두 개의 집합 중 가장 가중치가 적은 횡단 간선 e 을 찾는다.
 *      3. e (S 집합의 v 와 U 집합의 w 의 간선) 을 집합 s 에 추가한다. 
 *      4. 모든 정점이 집합 s 에 속할때까지 반복한다.
 *      
 *          Prim 알고리즘의 고려 사항.
 *              일반 그래프의 알고리즘과 달리 간선을 우선순위 큐에 넣고 알고리즘을 수행.
 *              최소 가중치인 간선은 우선순위 큐로 검색할 수 있다.
 *              좀 더 생각한다면 S 집합의 원소-w 간선에 대해서 
 *              현재까지 알고 있는 최소 값만 유지하면 좀 더 많은 간선을 추려낼 수 있다.
 *      
 */
public class MST {
    
    private static class WeightedGraph {
        private Map<Integer, List<Edge>> G = new HashMap<>();
    }

    private static class Edge {
        final int v, w, weight;

        public Edge(int v, int w, int weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "v=" + v +
                    ", w=" + w +
                    ", weight=" + weight +
                    '}';
        }
    }
    
    public static void mst(WeightedGraph G) {   //buggy - edge 을 순회하는 방식으로 도전.
        Map<Integer, Edge> edgeTo = new HashMap<>();    // MST 의 v-w
        Map<Integer, Integer> distTo = new HashMap<>(); // 가중치의 최소값
        Set<Integer> visit = new HashSet<>();
        Queue<Integer> pq = new PriorityQueue<>(Comparator.comparingDouble(distTo::get));
        Integer start = null;
        for (int v: G.G.keySet()) {
            if (start == null) start = v;
            distTo.put(v, Integer.MAX_VALUE);
        }
        distTo.put(start, 0);
        pq.add(start);
        edgeTo.put(start, null);
        while (!pq.isEmpty())
            visit(G, edgeTo, distTo, visit, pq);
        System.out.println(edgeTo);
    }

    private static void visit(WeightedGraph G, Map<Integer, Edge> edgeTo, Map<Integer, Integer> distTo, Set<Integer> visit, Queue<Integer> pq) {
        int v = pq.poll();
        visit.add(v);
        for (Edge e: G.G.get(v)) {
            int w = e.w;
            if (visit.contains(w)) continue;
            if (e.weight < distTo.get(w)) {
                edgeTo.put(w, e);
                distTo.put(w, e.weight);
                pq.add(w);
            }
        }
    }

    public static void main(String[] args) {
        WeightedGraph G = new WeightedGraph();
        try (Scanner scanner = new Scanner(ClassLoader.getSystemResourceAsStream("mst"))) {
            while (true) {
                if (!scanner.hasNext()) break;
                int v = scanner.nextInt(),
                        w = scanner.nextInt(),
                        weight = scanner.nextInt();
                G.G.computeIfAbsent(v, k -> new ArrayList<>());
                G.G.computeIfAbsent(w, k -> new ArrayList<>());
                G.G.get(v).add(new Edge(v, w, weight));
                G.G.get(w).add(new Edge(w, v, weight));
            }
        }
        
        mst(G);
    }
}
