package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.TOPOLOGICAL_ORDER)
public class P2252 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Integer>> G = new HashMap<>();
    private static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        for (int i=0; i<M; i++) {
            token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken());
            G.computeIfAbsent(v, k -> new ArrayList<>());
            G.computeIfAbsent(w, k -> new ArrayList<>());
            G.get(v).add(w);
        }
        for (int i=1; i<=N; i++)
            G.computeIfAbsent(i, k -> new ArrayList<>());
        
        Stack<Integer> stack = topologicalOrder(G);
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop()).append(" ");
        System.out.println(sb.toString());
    }

    private static Stack<Integer> topologicalOrder(Map<Integer, List<Integer>> G) {
        Set<Integer> visit = new HashSet<>();
        Stack<Integer> reversePostOrder = new Stack<>();
        for (int v: G.keySet()) {
            if (!visit.contains(v)) 
                dfs(G, v, visit, reversePostOrder);
        }
        return reversePostOrder;
    }

    private static void dfs(Map<Integer, List<Integer>> G, int v, Set<Integer> visit, Stack<Integer> stack) {
        visit.add(v);
        for (int w: G.get(v)) {
            if (!visit.contains(w))
                dfs(G, w, visit, stack);
        }
        stack.push(v);
    }
}
