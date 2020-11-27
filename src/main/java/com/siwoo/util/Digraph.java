package com.siwoo.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * 방향 그래프.
 *  정점과 간선의 집합. 방향 간선은 순서를 가진 두 정점 쌍을 연결.
 *  
 *  Edge 은 한쪽 방향으로의 경로만 가진다.
 *      => 작업 순서 제약 조건.
 *      => v->w (v 은 head, w 은 tail)
 *  차수의 분리
 *      => in-degree, out-degree
 *  방향 경로.
 *      Edge 의 방향을 따라 순서대로 이어지는 vertex 의 sequence.
 *  단순 경로.
 *      중복되는 정점이 없는 경로.
 *  단순 순환. (simple cycle)
 *      중복되는 간선이나 정점이 없는 순환 경로 (단, 시작/끝 정점읠 제외)
 *  도달성 vs 연결성 (reachability vs connection)
 *      도달성 - v -> w 로의 경로가 있다하더라도 w -> v 의 경로를 뜻하지 않는다.
 *      연결성 - v -> w == w -> v
 *      
 *      DFS
 *          s 정점에서 도달 가능한 모든 정점 w 검색 알고리즘.
 *          => GC 알고리즘 "표시하고 훑기". 
 *              비트 하나를 표시용으로 남겨두고, 
 *              접근 가능한 객체를 정점으로 DFS 을 주기적으로 실행. 이때 표시되지 않은 객체를 수집. *
 *              
 *  위상 정렬. topological sort
 *      모든 정점이 간선이 가리키는 방향으로만 나열되도록 정렬. (즉 뒤에 있는 정점이 앞에 있는 정점 방향으로 연결되는 경우가 없도록 정렬)
 *      
 *      => 작업의 선호 조건을 만족시키기 위한 작업 배치를 위한 알고리즘.
 *      => 작업 x 가 작업 y 보다 완료되어야 하고, 작업 y는 작업 z 보다 먼저 완료되어야 한다면, 
 *          작업 z 은 절대 작업 x, y 보다 먼저 완료되는 조건을 가질 수 없다. (설계 위반)
 *      
 *      위상 정렬을 위한 사전 조건. - 순환 탐지
 *          DFS 을 사용.
 *              dfs(v, w) 시, 스택에 w 가 존재한다면 w->v 경로가 존재한다는 증거이고, 
 *              이번 스택에서 v->w 이므로 순환이 완성. 
 *      
 */
public class Digraph {
    private final int v;
    private int e;
    private Map<Integer, List<Edge>> G = new HashMap<>();
    private Map<Integer, Integer> inDegree = new HashMap<>();
    
    private static class DFS {
        private Digraph DG;
        private int s;
        private Set<Integer> visit;

        public DFS(Digraph DG, int s) {
            this.DG = DG;
            this.s = s;
            visit = new HashSet<>();
            dfs(s, visit);
        }

        private void dfs(int v, Set<Integer> visit) {
            visit.add(v);
            for (int w: DG.adj(v))
                if (!visit.contains(w))
                    dfs(w, visit);
        }

        boolean marked(int v) {
            return visit.contains(v);
        }
    }
    
    private static class Cycle {
        private Digraph DG;
        private int[] edgeTo;
        private Stack<Integer> cycle;
        private boolean[] onStack;
        private boolean[] visit;
        private boolean hasCycle;

        public Cycle(Digraph DG) {
            this.DG = DG;
            onStack = new boolean[DG.numberOfVertexes()];
            visit = new boolean[DG.numberOfVertexes()];
            edgeTo = new int[DG.numberOfVertexes()];
            for (int v: DG.vertexes())
                if (!visit[v])
                    dfs(DG, v);
        }

        private void dfs(Digraph DG, int v) {
            onStack[v] = true;
            visit[v] = true;
            for (int w: DG.adj(v)) {
                if (hasCycle) return;
                if (!visit[w]) {
                    edgeTo[w] = v;
                    dfs(DG, w);
                } else if (onStack[w]){
                    hasCycle = true;
                    cycle = new Stack<>();
                    //cycle point = v
                    for (int x=v;x!=w;x=edgeTo[x]) {
                        cycle.push(x);
                    }
                    cycle.push(w);
                    cycle.push(v);
                }
            }
            onStack[v] = false;
        }
        
        public boolean hasCycle() {
            return hasCycle;
        }

        public Iterable<Integer> cycle() {
            return cycle;
        }
    }

    public Set<Integer> vertexes() {
        return G.keySet();
    }

    private static class Edge {
        private final int v, w;

        private Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    
    public Digraph(int v) {
        this.v = v;
    }
    
    public int numberOfVertexes() {
        return v;
    }
    
    public int numberOfEdges() {
        return e;
    }
    
    public int inDegreeOf(int v) {
        return !inDegree.containsKey(v)? 0: inDegree.get(v);
    }
    
    public void add(int v, int w) {
        G.computeIfAbsent(v, k -> new ArrayList<>());
        G.computeIfAbsent(w, k -> new ArrayList<>());
        G.get(v).add(new Edge(v, w));
        if (!inDegree.containsKey(w))
            inDegree.put(w, 1);
        else
            inDegree.put(w, inDegree.get(w) + 1);
        e++;
    }
    
    public List<Integer> adj(int v) {
        return G.get(v).stream()
                .map(e -> e.w)
                .collect(Collectors.toList());
    }

    /**
     * 그래프의 모든 간선의 방향을 뒤집은 그래프.
     * @return
     */
    public Digraph reverse() {
        Digraph R = new Digraph(v);
        for (int v: G.keySet())
            for (int w: adj(v))
                R.add(w, v);
        return R;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d vertices, %d edges\n", v, e));
        for (int v: G.keySet()) {
            sb.append(v).append(":");
            for (int w: adj(v))
                sb.append(" ").append(w);
            sb.append("\n");
        }
        return sb.toString();
    }
    
    private DFS dfs(int v) {
        return new DFS(this, v);
    }

    public static void main(String[] args) {
        Digraph DG;
        try (Scanner scanner = new Scanner(new BufferedInputStream(Digraph.class.getClassLoader().getResourceAsStream("tinyDG.txt")))) {
            int v = scanner.nextInt(),
                    e = scanner.nextInt();
            DG = new Digraph(v);
            for (int i=0; i<e; i++)
                DG.add(scanner.nextInt(), scanner.nextInt());
        }
        DFS dfs = DG.dfs(1);
        System.out.println(dfs.visit);

        dfs = DG.dfs(0);
        System.out.println(dfs.visit);

        Cycle cycle = new Digraph.Cycle(DG);
        System.out.println(cycle.hasCycle);
        System.out.println(cycle.cycle);
    }
}
