package com.siwoo.util;

import java.util.*;

/**
 *  LCA (lowest common ancestor)
 *  
 *  두 정점의 공통 조상 중 가장 가까운 조상. 
 *  
 *      1. 왼쪽, 오른쪽의 깊이와 부모를 구한다.
 *      2. 깊이 내려간 쪽이 반대쪽 깊이가 될때까지 올라간다.
 *      3. 왼쪽, 오른쪽을 동시에 올라가면서 left == right 라면 lca
 */
public class LCA {
    
    private static class Tree<E extends Comparable<E>> {
        private Node root;
        private int size;
        
        private class Node {
            Node left, right;
            E data;
            int size;

            public Node(E data) {
                this.data = data;
            }

            @Override
            public String toString() {
                return "{" +
                        "data=" + data +
                        '}';
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
        
        public E lca(E left, E right) {
            Graph graph = graph(root);
            Node v = get(left);
            Node w = get(right);
            if (graph.depth.get(v) < graph.depth.get(w)) {
                Node t = v;
                v = w;
                w = t;
            }
            while (graph.depth.get(v) != graph.depth.get(w))
                v = graph.parent.get(v);
            while (v != w) {
                v = graph.parent.get(v);
                w = graph.parent.get(w);
            }
            return v.data;
        }

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
        System.out.println(tree.lca('r', 'z'));
        System.out.println(tree.lca('a', 'r'));
        System.out.println(tree.lca('a', 'e'));
    }
}
