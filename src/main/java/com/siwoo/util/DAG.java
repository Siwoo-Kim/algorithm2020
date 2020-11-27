package com.siwoo.util;

import java.util.*;

/**
 * DAG (directed acylic graph)
 * 
 * 싸이클이 없는 방향 있는 그래프.
 *  = 스케쥴링, 선행 관계 요소.
 *  
 *  DAG 을 위한 방향 순환 탐지 알고리즘.
 *      주어진 digraph 에서 방향 순환이 존재하는가?
 *      
 *      dfs(v, w) 을 진행하는 동안 현재 탐색 중인 방향 경로가 유지된다는 점을 이용한다.
 *      => v -> w 만났을 때, w 가 이미 스택에 존재한다면 순환을 찾은 것이다.
 *      => 스택 자체가 w -> v 의 경로의 증거이고 현재 콜스택에 의해 v -> w 로 순환이 완선.
 *  
 *  위상정렬 (topological order)
 *      dfs 을 이용한 위상 정렬
 *      
 *          그래프의 간선 v-w 에서 v 가 w 보다 선행한다는 순서를 찾는 알고리즘
 *          위상 정렬의 조건은 DAG 이다
 *      
 *          위상 정렬의 결과는 여러가지다.
 *          위상 정렬은 반전된 후행 (reversed postorder) 을 이용한다
 *              => 재귀 호출 후에 정점 v 을 스택에 저장
 *          dfs(v, w) 시 v 는 반드시 w 에 선행되어야 한다.
 *     
 *     bfs 을 이용한 위상 정렬
 *          정점의 in-degree 을 이용한다.
 *          bfs 순회 중 in-degree 가 0 인 정점 v 은
 *          v 의 선행되어야 할 w 들이 모두 찾았음을 의미한다.
 *          
 *          1. 큐에는 indegree 가 0 인 정점만을 유지한다.
 *          2. bfs(v) 시 v 은 indegree 가 0 이므로 order 에 추가.
 *          3. v 의 방문 정점 w 에 대하여 indegree 을 하나씩 지워준다.
 *          이때 w 의 indegree = 0 이라면 큐에 넣어준다.
 *          
 */
public class DAG {
    
    public static Stack<Integer> topologicalOrder(Digraph G) {
        if (!isDAG(G))
            throw new IllegalArgumentException();
        Set<Integer> visit = new HashSet<>();
        Stack<Integer> reversePostOrder = new Stack<>();
        for (int v: G.vertexes())
            if (!visit.contains(v))
                topologicalOrder(G, v, visit, reversePostOrder);
        return reversePostOrder;
    }

    private static void topologicalOrder(Digraph G, int v, Set<Integer> visit, Stack<Integer> stack) {        
        visit.add(v);
        for (int w: G.adj(v)) {
            if (!visit.contains(w))
                topologicalOrder(G, w, visit, stack);
        }
        stack.push(v);
    }
    
    public static List<Integer> topologicalOrderBFS(Digraph G) {
        if (!isDAG(G)) throw new IllegalArgumentException();
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Integer> degree = new HashMap<>();
        for (int v: G.vertexes()) {
            if (G.inDegreeOf(v) == 0)
                q.add(v);
            degree.put(v, G.inDegreeOf(v));
        }
        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int v = q.poll();
            order.add(v);
            for (int w: G.adj(v)) {
                degree.put(w, degree.get(w) - 1);
                if (degree.get(w) == 0)
                    q.add(w);
            }
        }
        return order;
    }

    public static boolean isDAG(Digraph G) {
        Set<Integer> visit = new HashSet<>();
        for (int v: G.vertexes()) {
            if (visit.contains(v)) continue;
            if (!isDAG(G, v, visit, new Stack<>()))
                return false;
        }
        return true;
    }

    private static boolean isDAG(Digraph G, int v, Set<Integer> visit, Stack<Integer> stack) {
        stack.add(v);
        visit.add(v);
        for (int w: G.adj(v)) {
            if (!visit.contains(w)) {
                if (!isDAG(G, w, visit, stack))
                    return false;
            } else if (stack.contains(w))
                return false;
        }
        stack.pop();
        return true;
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(9);
        G.add(1, 4);
        G.add(2, 4);
        G.add(1, 9);
        G.add(4, 7);
        G.add(3, 5);
        G.add(5, 6);
        G.add(5, 7);
        G.add(7, 6);
        G.add(7, 8);
        G.add(6, 8);
        System.out.println(isDAG(G));
        
//        G.add(2, 1);
//        G.add(4, 2);
//        System.out.println(isDAG(G));
        Stack<Integer> to = topologicalOrder(G);
        while (!to.isEmpty())
            System.out.println(to.pop());
        System.out.println(topologicalOrderBFS(G));
    }
}
