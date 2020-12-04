package com.siwoo.util;

import java.lang.reflect.Array;
import java.util.*;

/**
 *  LCA (lowest common ancestor)
 *  
 *  두 정점의 공통 조상 중 가장 가까운 조상. 
 *  
 *      1. 왼쪽, 오른쪽의 깊이와 부모를 구한다.
 *      2. 깊이 내려간 쪽이 반대쪽 깊이가 될때까지 올라간다.
 *      3. 왼쪽, 오른쪽을 동시에 올라가면서 left == right 라면 lca
 *      
 * 그래프에서 두 정점 v, w 이 만나는 가장 가까운 정점 u 을 찾는 알고리즘인 듯.
 *  (트리도 그래프니까) => 그래프에 사이클이 없어야 한다.
 *  
 *  트리에서 두 정점 v, w 의 사이의 거리를 구할때도 사용. O(N)
 *      root, v, w, lca 가 있고, distTo[] 에 루트와의 거리라 가정한다면
 *          dist[v] + dist[w] - (dist[lca] * 2) (겹치는 간선)
 *  
 *  LCA 개선하기 O(logN)
 *      1. 조상 이어주기
 *          p[i][j] 을 노드 i 의 2^j 번째 조상이라 가정한다면,
 *      
 *          p[i][0] = parent[0]
 *          p[i][j] = p[p[i][j-1]][j-1]
 *              노드 i 의 2^j 조상은 노드 i의 2^j-1 조상의 2^j-1 번째 조상이다.
 *              => 나의 2^2 조상은 노드 2^2-1 (할아버지) 의 2^2-1 번째 조상 (할아버지의 할아버지)
 *              => 같은 논리로 나의 2^4 조상은 노드 2^3 의 2^3 번째 조상. (2^4 = 2^4-1 + 2^4-1) 이므로
 *          
 *      2. v 와 w 의 lca 구하기.
 *          k = max(log(Math.max(depth[v], depth[w])))
 *          
 *          depth[v] != depth[w] 이라면 레벨이 큰 것을 2^k 칸씩 올린다. (k 은 1씩 감소)
 *              v = parent[v][k--]
 *          depth[v] == depth[w] 이고 v != w (같은 노드가 되지 않을 때까지) 2^k 칸씩 위로 올린다. (k 은 1씩 감소)
 *              => v != w 은 가장 낮은 공통 조상을 구하기 위한 조건. 
 *          마지막으로 1 칸 올린다.
 *  
 *  LCA 개선하기 - DFS O(logN)
 *      dfs 로 노드를 방문할 때, 
 *          in[v] 에 i 을 방문할 때, 몇번째인지
 *          out[v] 에 i 을 빠져나갈 때, 몇번째인지를 기록.
 *          마찬가지로 dept
 *      upper 함수.
 *          upper(v, w) = in[v] <= in[w] 이고 out[w] <= out[v] 이라면
 *              v 은 w 의 조상이다.
 */
public class LCA {
    
    private static class Tree<E extends Comparable<E>> {
        private Node root;
        private int size;
        private Map<Node, Integer> in, out;
        private Map<Node, Node[]> p;
        private int k, l;
        
        public E lca(E v, E w) {
            for (l=1; (1<<l)<=size(root); l++) ;
            l--;
            if (p == null) {
                p = new HashMap<>();
                in = new HashMap<>();
                out = new HashMap<>();
                dfs(root, root);
            }
            Node vn = get(v),
                    wn = get(w);
            if (isAncestor(vn, wn)) return vn.data;
            if (isAncestor(wn, vn)) return wn.data;
            for (int i=l; i>=0; i--) {
                if (!isAncestor(p.get(vn)[i], wn))
                    vn = p.get(vn)[i];
            }
            return p.get(vn)[0].data;
        }

        public boolean isAncestor(Node v, Node w) {
            return in.get(v) <= out.get(w) && out.get(v) >= out.get(w);
        }
        
        private void dfs(Node node, Node parent) { 
            in.put(node, ++k);
            p.put(node, (Node[]) Array.newInstance(Node.class, size(this.root)));
            p.get(node)[0] = parent;
            for (int k=1; k<=l; k++)
                p.get(node)[k] = p.get(p.get(node)[k-1])[k-1];
            for (Node child: Arrays.asList(node.left, node.right)) {
                if (child != null)
                    dfs(child, node);
            }
            out.put(node, ++k);
        }

//        @SuppressWarnings("unchecked")
//        public E lca(E v, E w) {
//            Graph G = graph(root);
//            Map<Node, Node[]> lcas = new HashMap<>();
//            for (Node node: G.parent.keySet()) {
//                lcas.put(node, (Node[]) Array.newInstance(Node.class, size(root)));
//                lcas.get(node)[0] = G.parent.get(node);
//            }
//            for (int i=1; (1<<i) < size; i++) {
//                for (Node node: lcas.keySet()) {
//                    if (lcas.get(node)[i-1] != null)    // 부모 이어주
//                        lcas.get(node)[i] = lcas.get(lcas.get(node)[i-1])[i-1];
//                }
//            }
//            Node vn = get(v),
//                    vw = get(w);
//            // 부모 구하기.
//            if (G.depth.get(vn) < G.depth.get(vw)) {
//                Node t = vn;
//                vn = vw;
//                vw = t;
//            }
//            int log = 1;
//            for (log=1; (1<<log) <= G.depth.get(vn); log++);
//            log -= 1;
//            for (int k=log; k>=0; k--) {
//                if (G.depth.get(vn) - (1<<k) >= G.depth.get(vw))
//                    vn = lcas.get(vn)[k];
//            }
//            if (vn == vw) return vn.data;
//            for (int i=log; i>=0; i--) {
//                if (lcas.get(vn)[i] != null && lcas.get(vn)[i] != lcas.get(vw)[i]) {
//                    vn = lcas.get(vn)[i];
//                    vw = lcas.get(vw)[i];
//                }
//            }
//            return lcas.get(vn)[0].data;
//        }

        private class Node {
            Node left, right;
            E data;
            int size;

            public Node(E data) {
                this.data = data;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Node node = (Node) o;
                return Objects.equals(data, node.data);
            }

            @Override
            public int hashCode() {
                return Objects.hash(data);
            }
        }
        
        private class Graph {
            private Map<Node, Node> parent;
            private Map<Node, Integer> depth;

            public Graph(Map<Node, Node> parent, Map<Node, Integer> depth) {
                this.parent = parent;
                this.depth = depth;
            }
        }
        
//        public E lca(E left, E right) {
//            Graph graph = graph(root);
//            Node v = get(left);
//            Node w = get(right);
//            if (graph.depth.get(v) < graph.depth.get(w)) {
//                Node t = v;
//                v = w;
//                w = t;
//            }
//            while (graph.depth.get(v) != graph.depth.get(w))
//                v = graph.parent.get(v);
//            while (v != w) {
//                v = graph.parent.get(v);
//                w = graph.parent.get(w);
//            }
//            return v.data;
//        }

        private Node get(E e) {
            return get(e, root);
        }

        private Node get(E e, Node root) {
            if (root == null) return null;
            int c = e.compareTo(root.data);
            if (c == 0) return root;    //no dup
            else if (c < 0) return get(e, root.left);
            else return get(e, root.right);
        }

        private Graph graph(Node node) {
            Map<Node, Integer> depth = new HashMap<>();
            Map<Node, Node> parent = new HashMap<>();
            Queue<Node> q = new LinkedList<>();
            q.add(node);
            depth.put(node, 0);
            while (!q.isEmpty()) {
                Node v = q.poll();
                for (Node w: Arrays.asList(v.left, v.right)) {
                    if (w == null) continue;
                    parent.put(w, v);
                    depth.put(w, depth.get(v) + 1);
                    q.add(w);
                }
            }
            return new Graph(parent, depth);
        }


        public void add(E e) {
            root = add(root, e);
        }

        private Node add(Node root, E e) {
            if (root == null) return new Node(e);
            int c = e.compareTo(root.data);
            Node node = root;
            if (c == 0) node.data = e;
            if (c < 0) node.left = add(root.left, e);
            else node.right = add(root.right, e);
            node.size = size(node.right) + size(node.left) + 1;
            return node;
        }

        private int size(Node node) {
            return node == null? 0: node.size;
        }
    }

    public static void main(String[] args) {
        Tree<Character> tree = new Tree<>();
        tree.add('h');
        tree.add('c');
        tree.add('s');
        tree.add('a');
        tree.add('e');
        tree.add('r');
        tree.add('x');
        tree.add('z');
        System.out.println();
        System.out.println(tree.lca('r', 'z')); //s
        System.out.println(tree.lca('a', 'r')); //h
        System.out.println(tree.lca('a', 'e')); //c
    }
}
