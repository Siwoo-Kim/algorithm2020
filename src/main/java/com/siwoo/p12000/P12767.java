package com.siwoo.p12000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.BINARY_SEARCH)
public class P12767 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K;
    
    private static class Tree {
        private Node root;

        private static class Node {
            private int v;
            private Node left ,right;
            public Node(int v) {
                this.v = v;
            }

        }
        
        public void push(int v) {
            root = push(v, root);
        }

        private Node push(int v, Node root) {
            if (root == null) return new Node(v);
            int c = Integer.compare(root.v, v);
            if (c > 0) 
                root.left = push(v, root.left);
            else if (c < 0)
                root.right = push(v, root.right);
            return root;
        }

        public String postOrder() {
            return postOrder(root, "");
        }

        private String postOrder(Node root, String s) {
            if (root == null) return s;
            s = postOrder(root.left, s + "l");
            s = postOrder(root.right, s + "r");
            s += "x";
            return s;
        }
    }
    
    public static void main(String[] args) throws IOException {
        Set<String> prototypes = new HashSet<>();
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());
        for (int i=0; i<N; i++) {
            Tree tree = new Tree();
            token = new StringTokenizer(reader.readLine());
            for (int k=0; k<K; k++)
                tree.push(Integer.parseInt(token.nextToken()));
            String p = tree.postOrder();
            prototypes.add(p);
        }
        System.out.println(prototypes.size());
    }
}
