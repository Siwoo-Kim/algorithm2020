package com.siwoo.util;

import java.util.*;

/**
 * 최단 경로. shortest path
 * 
 *  그래프 G 의 정점 v 에서 정점 w 로 갈 수 있는 최소 비용 v-w 찾기.
 *  
 *  최단 경로의 속성.
 *      1. 가중치가 꼭 거리를 의미하지 않는다.
 *      2. 정점에 도달할 수 없다면 최단 거리도 존재하지 않는다.
 *      3. 음수 가중치는 문제를 복잡하게 만든다.
 *      4. 최단 경로는 단순 경로이다. (가중치가 0 이면서 순환을 이루는 간선은 무시.)
 *      
 *  최단 경로 트리 shortest-paths tree
 *      정점 v 에 대한 도달 가능한 모든 w의 최단 경로를 포함한 트리.
 *      
 *  간선 이완 (relaxation)
 *      그래프 순회 중 방문하는 모든 w 에 대해 v->w 을 이완하는 작업을 수행하며,
 *      v->w 의 간선 이완이란 현재까지 알려진 최소 경로 s->w 와 v->w 을 비교하여
 *      적절히 업데이트하는 작업.
 *      
 *      distTo[v] + edge.weight is less than distTo[w] ?
 *      
 *  weighted digraph 의 최단 경로 알고리즘.
 *      벨판-포드, 다익스트라.
 *      
 *  벨만 포드 알고리즘. bellman ford
 *      V 개의 정점을 가진 가중 방향 그래프 G 에서, 
 *      원점 s 에서 "음의 순환이 없는 경우", 단일 원점 s 로부터의 최단 경로 문제를 해결한다.가
 *      -> 벨만 포드의 시간 복잡도는 O(VE) 이다. 가중치가 음수인 그래프가 아니라면 다익스트라를 이용.
 *      
 *      가중치 음수가 있는 경우 음의 순환을 탐지하는 알고리즘을 이용하면 가중치가 음수인 그래프에서도
 *      최단 경로를 구할 수 있다.
 *      
 *      s-w 의 최단 경로에서 방문하는 정점은 V-1 을 클 수 없단 점을 이용한다.
 *          => V-1 보다 크다면 분명 정점을 중복 방문한다.
 *          어떤 정점 w 을 중복 방문했다고 가정하자.
 *          s-w-k 의 경로는 이미 방문했으므로 s-w-w-k 은 절대 답이 될 수 없다. (양수로 이루어진 가중 그래프이므로) 
 *                 
 *      distTo[s] = 0 으로 초기화, 나머지는 무한 값으로 초기화.
 *      그 다음 정점 V 개의 정점에 대해서 간선을 "임의의 순서" 로 이완한다.
 *      V 번을 반복 하면서 E 개의 간선을 이완하면 VE 의 시간 복잡도를 가진다.
 *      
 *      개선된 큐-기반 벨만 포드 알고리즘.
 *          간선의 최소 경로의 검사를 줄이기 위해 
 *          distTo[] 의 값이 바뀔 수 있는 것은 relax(v) 에서 distTo[] 값이 바뀐 정점 w 라는 점을 이용한다.
 *          이러한 점들을 큐을 이용해 추적한다.
 *          
 *          
 */
public class ShortestPath {
    
    private Map<Integer, List<Edge>> G = new HashMap<>();
    
    private static class Edge {
        final int from, to;
        final double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }
    
    private static class BellmanFordSP {
        private double[] distTo;    // cost for s-v
        private Edge[] edgeTo;      // v's last edge
        private boolean[] onQ;      // is the v on the q?
        private Queue<Integer> q;   // vertexes need to be relaxed
        private int cost;           // the number of count for relax
        private Iterable<Integer> cycle;   // is there cycle on G?
        private int s;

        public BellmanFordSP(Map<Integer, List<Edge>> G, int s) {
            this.s = s;
            distTo = new double[G.size()];
            edgeTo = new Edge[G.size()];
            onQ = new boolean[G.size()];
            q = new LinkedList<>();
            for (int v: G.keySet())
                distTo[v] = Double.POSITIVE_INFINITY;
            distTo[s] = 0.0;
            q.add(s);
            onQ[s] = true;
            while (!q.isEmpty() && !hasNegativeCycle()) {
                int v = q.poll();
                onQ[v] = false;
                relax(G, v);
            }
        }

        private boolean hasNegativeCycle() {
            return cycle != null;
        }

        private void relax(Map<Integer, List<Edge>> G, int v) {
            for (Edge edge: G.get(v)) {
                int w = edge.to;
                if (distTo[w] > (distTo[v] + edge.weight)) {
                    distTo[w] = distTo[v] + edge.weight;
                    edgeTo[w] = edge;
                    if (!onQ[w]) {
                        q.add(w); 
                        onQ[w] = true;
                    }
                }
            }
            if (cost++ % G.size() == 0)
                findNegativeCycle();
        }

        private void findNegativeCycle() {
            int V = edgeTo.length;
            Map<Integer, List<Edge>> spt = new HashMap<>();
            for (int v=0; v<V; v++)
                if (edgeTo[v] != null) {
                    spt.putIfAbsent(v, new ArrayList<>());
                    spt.putIfAbsent(edgeTo[v].from, new ArrayList<>());
                    spt.get(edgeTo[v].from).add(edgeTo[v]);
                }
            cycle = getCycle(spt);
        }

        private Iterable<Integer> getCycle(Map<Integer, List<Edge>> G) {
            Set<Integer> visit = new HashSet<>();
            Stack<Integer> stack = new Stack<>();
            for (int v: G.keySet())
                if (!visit.contains(v))
                    if (cycle(v, visit, stack, G))
                        return cycle;
            return null;
        }

        private boolean cycle(int v, Set<Integer> visit, Stack<Integer> stack, Map<Integer, List<Edge>> G) {
            visit.add(v);
            stack.push(v);
            for (Edge edge: G.get(v)) {
                if (hasNegativeCycle()) return true;
                if (!visit.contains(edge.to)) {
                    if (cycle(edge.to, visit, stack, G))
                        return true;
                } else if (stack.contains(edge.to)) {
                    // found cycle
                    Stack<Integer> path = new Stack<>();
                    path.push(edge.to);
                    for (int k=stack.pop(); k!=edge.to; k=stack.pop())
                        path.push(k);
                    path.push(edge.to);
                    cycle = path;
                    return true;
                }
            }
            stack.pop();
            return false;
        }
    }

    public static void main(String[] args) {
        ShortestPath sp = new ShortestPath();
        try (Scanner scanner = new Scanner(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("tinyEWDn.txt")))) {
            int V = scanner.nextInt();
            int E = scanner.nextInt();
            for (int i=0; i<E; i++) {
                int v = scanner.nextInt(),
                        w = scanner.nextInt();
                double weight = scanner.nextDouble();
                sp.G.putIfAbsent(v, new ArrayList<>());
                sp.G.putIfAbsent(w, new ArrayList<>());
                sp.G.get(v).add(new Edge(v, w, weight));
            }
        }
        BellmanFordSP bellmanFordSP = new BellmanFordSP(sp.G, 0);
        for (int k: sp.G.keySet())
            System.out.printf("%d: %.02f%n", k, bellmanFordSP.distTo[k]);
        
    }
    
}





















