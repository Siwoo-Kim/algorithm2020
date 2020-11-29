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
 *      원점 s 에서 "음의 순환이 없는 경우", 단일 원점 s 로부터의 최단 경로 문제를 해결한다.
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
 *      그 다음 간선을 "임의의 순서" 로 이완한다.
 *      "V 번을 반복" 하면서 E 개의 간선을 이완하면 VE 의 시간 복잡도를 가진다.
 *      
 *      for (int pass=0; pass<V; pass++)
 *          for (v=0; v<G.V; v++)
 *              for (Edge e: G.adj(v))
 *                  relax(e)
 *
 *      음의 순환 탐지법.
 *          벨만 포드의 증명으로 간선 이완을 V 번하면 최단 경로를 구할 수 있다.
 *          음수 순환의 탐지는 이 특징을 이용한다.
 *          만약 V+1 에도 특정 정점의 최단 경로가 변경된다면 이는 음의 순환이 반드시 존재한다는 것.
 *          
 *         for (int pass=0; pass<=N; pass++) {
 *             for (int v: G.keySet()) {
 *                 for (Edge e: G.get(v)) {
 *                     if (distTo[e.to] > distTo[e.from] + e.weight) {
 *                         distTo[e.to] = distTo[e.from] + e.weight;
 *                         if (pass == N) // N 번에 최단 경로가 발견된다면 negative cycle 
 *                             return true;
 *                     }
 *                 }
 *             }
 *         }
 *      개선된 큐-기반 벨만 포드 알고리즘.
 *          간선의 최소 경로의 검사를 줄이기 위해 
 *          distTo[] 의 값이 바뀔 수 있는 것은 relax(v) 에서 distTo[] 값이 바뀐 정점 w 라는 점을 이용한다.
 *          이러한 점들을 큐을 이용해 추적한다.
 *          
 *  다익스트라 알고리즘. dijkstra           
 *      prim (최소 신장 트리을 찾는 알고리즘) 의 방식을 착용해 SPT (최단 경로 트리) 을 구하는 알고리즘.
 *      
 *      prim vs dijkstra
 *          prim 은 최소 신장 트리을 찾는 알고리즘이다.
 *              => 트리에 속하지 않는 정점 중 트리에서 가장 가까운 정점을 추가한다.
 *          dijkstra
 *              => 트리에 속하지 않은 정점 중 원점에서 가장 가까운 정점을 추가한다.
 *              
 *      1. distTo[s] 을 0 으로 초기화, 나머지는 무한대 값으로 초기화.
 *      2. spt 에 속하지 않은 정점 w 을 트리에 추가하고, 이완한다.
 *      
 *      SPT 에 정점 추가하기.
 *          v 가 원점 s 에서 도달 가능하다면 v 가 이완되고 나면
 *          distTo[w] <= distTo[v] + e.weight() 부등식은 만족된다.
 *          이 값은 알고리즘이 끝날때까지 만족되며 줄어들게 하거나 유지된다.
 *          따라서, 정점 s 로부터 도달 가능한 v  트리에 모두 추가되었다면
 *          최단 경로에 대한 조건이 만족된다.
 *          
 *      다익스트라 의 우선순위 큐 고찰.
 *          => 우선순위 큐에 가장 top 의 정점 w 의 s-w 거리는
 *          이미 이완된 정점들 v 의 보단 s-v 보단 크고
 *          아직 이완되지 않은 어떤 정점 k 보단 작다.
 *          
 *          큐의 불변을 지키기 위해 
 *          (v, v.weight) 을 함께 넣어줘야 한다.
 *          중복의 (v, v.weight) 가 큐에 있더라도 상관없다.
 *          SPT 에 포함된 집합은 한번만 수행될거며 (visit[v])
 *          중복 수행되더라도 간선 이완에 영향을 끼치지 않기 때문.
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
    
    private static class Dijkstra {
        private Edge[] edgeTo;
        private double[] distTo;
        private PriorityQueue<Integer> pq;
        private int s;
        
        public Dijkstra(Map<Integer, List<Edge>> G, int s) {
            edgeTo = new Edge[G.size()];
            distTo = new double[G.size()];
            boolean[] onQ = new boolean[G.size()];
            this.s = s;
            //don't do this!
            pq = new PriorityQueue<>(Comparator.comparingDouble(v -> distTo[v]));
            for (int v: G.keySet())
                distTo[v] = Double.POSITIVE_INFINITY;
            distTo[s] = 0.0;
            pq.add(s);
            while (!pq.isEmpty()) {
                int v = pq.poll();
                onQ[v] = false;
                relax(G, onQ, v);
            }
        }

        private void relax(Map<Integer, List<Edge>> G, boolean[] onQ, Integer v) {
            for (Edge e: G.get(v)) {
                int w = e.to;
                if (distTo[w] > distTo[v] + e.weight) {
                    distTo[w] = distTo[v] + e.weight;
                    edgeTo[w] = e;
                    if (!onQ[w]) {
                        onQ[w] = true;
                        pq.add(w);
                    }
                }
            }
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
        System.out.println("==========================");
        Dijkstra dijkstra = new Dijkstra(sp.G, 0);
        for (int k: sp.G.keySet())
            System.out.printf("%d: %.02f%n", k, dijkstra.distTo[k]);
    }
    
}





















