package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Used(algorithm = Algorithm.DFS)
public class P1991 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    private static class Tree {

        private static class Node {
            private String value;
            private Node left, right;
            public Node(String value) {
                this.value = value;
            }
        }
        private Node root;

        public Node get(String value) {
            return get(root, value);
        }

        private Node get(Node root, String value) {
            if (root == null) return null;
            if (value.equals(root.value)) return root;
            Node node = get(root.left, value);
            if (node == null)
                return get(root.right, value);
            return node;
        }

        public void preorder() {
            preorder(root);
        }

        private void preorder(Node root) {
            if (root == null) return;
            System.out.print(root.value);
            preorder(root.left);
            preorder(root.right);
        }

        public void inorder() {
            inorder(root);
        }

        private void inorder(Node root) {
            if (root == null) return;
            inorder(root.left);
            System.out.print(root.value);
            inorder(root.right);
        }

        public void postorder() {
            postorder(root);
        }

        private void postorder(Node root) {
            if (root == null) return;
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.value);
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        Tree tree = new Tree();
        for (int i=0; i<N; i++) {
            String[] data = reader.readLine().split("\\s+");
            String p = data[0],
                    left = data[1],
                    right = data[2];
            Tree.Node node = tree.get(p);
            if (node == null)
                node = tree.root = new Tree.Node(p);
            if (!left.equals("."))
                node.left = new Tree.Node(left);
            if (!right.equals("."))
                node.right = new Tree.Node(right);
        }
        tree.preorder();
        System.out.println();
        tree.inorder();
        System.out.println();
        tree.postorder();
    }
}
