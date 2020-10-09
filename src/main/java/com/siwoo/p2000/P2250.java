package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * v 열 -> 트리의 왼쪽 부분. inorder
 * v 행 -> depth(v)
 *
 */
@Used(algorithm = Algorithm.DFS)
public class P2250 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    private static class Tree {
        private static class Node {
            private int value, index;
            private Node left, right, parent;

            public Node(int value) {
                this.value = value;
            }
        }

        private Node root;
        private int index = 1, depth;

        public Tree(Node root) {
            this.root = root;
        }

        public void indexing() {
            indexing(root);
        }

        private Map<Integer, Pair> cache = new HashMap<>();

        private static class Pair {
            int v, w;

            public Pair(int v, int w) {
                this.v = v;
                this.w = w;
            }
        }

        private void indexing(Node root) {
            if (root == null) return;
            indexing(root.left);
            root.index = index++;
            indexing(root.right);
        }

        private void calculate() {
            calculate(root, 1);
        }

        private void calculate(Node root, int depth) {
            if (root == null) return;
            if (!cache.containsKey(depth))
                cache.put(depth, new Pair(root.index, root.index));
            Pair pair = cache.get(depth);
            pair.v = Math.min(pair.v, root.index);
            pair.w = Math.max(pair.w, root.index);
            this.depth = Math.max(depth, this.depth);
            calculate(root.left, depth+1);
            calculate(root.right, depth+1);
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        Tree.Node[] nodes = new Tree.Node[N+1];
        for (int i=0; i<N; i++) {
            token = new StringTokenizer(reader.readLine());
            int p = Integer.parseInt(token.nextToken());
            int l = Integer.parseInt(token.nextToken());
            int r = Integer.parseInt(token.nextToken());
            Tree.Node parent = nodes[p];
            if (parent == null)
                parent = nodes[p] = new Tree.Node(p);
            Tree.Node left = null;
            if (l != -1) {
                left = nodes[l];
                if (left == null)
                    left = nodes[l] = new Tree.Node(l);
            }
            Tree.Node right = null;
            if (r != -1) {
                right = nodes[r];
                if (right == null)
                    right = nodes[r] = new Tree.Node(r);
            }
            parent.left = left;
            parent.right = right;
            if (left != null)
                left.parent = parent;
            if (right != null)
                right.parent = parent;
        }
        Tree.Node root = null;
        for (int i=1; i<N+1; i++)
            if (nodes[i].parent == null) {
                root = nodes[i];
                break;
            }
        Tree tree = new Tree(root);
        tree.indexing();
        tree.calculate();
        int max = 0, d = 0;
        for (int depth=1; depth<=tree.depth; depth++) {
            Tree.Pair pair = tree.cache.get(depth);
            int value = pair.w - pair.v + 1;
            if (value > max) {
                max = value;
                d = depth;
            }
        }
        System.out.printf("%d %d%n", d, max);
    }
}
