package com.siwoo.p9000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class P9250 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, Q;

    private static class AhoCorasick {
        private static final int ASCII = 1 << 7;
        private static final Node root = new Node();
        private static boolean lazy;

        public void put(String p) {
            put(p, 0, root);
        }

        private void put(String p, int i, Node root) {
            if (p.length() == i) {
                root.exists = true;
                return;
            }
            char c = p.charAt(i);
            Node node = root.children[c];
            if (node == null)
                node = root.children[c] = new Node();
            put(p, i+1, node);
        }

        public boolean contains(String s) {
            if (!lazy) {
                build();
                lazy = true;
            }
            Node node = root;
            boolean ok = false;
            for (int i=0; i<s.length(); i++) {
                char c = s.charAt(i);
                while (node != root && node.children[c] == null)
                    node = node.pi;
                if (node.children[c] != null)
                    node = node.children[c];
                if (node.exists)
                    ok = true;
            }
            return ok;
        }

        private void build() {
            root.pi = root;
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            while (!q.isEmpty()) {
                Node v = q.poll();
                for (int i=0; i<=ASCII; i++) {
                    if (v.children[i] == null) continue;
                    Node w = v.children[i];
                    if (v == root)
                        w.pi = root;
                    else {
                        Node pi = v.pi;
                        while (pi != root && pi.children[i] == null)
                            pi = pi.pi;
                        if (pi.children[i] != null)
                            pi = pi.children[i];
                        w.pi = pi;
                    }
                    w.exists |= w.pi.exists;
                    q.add(w);
                }
            }
        }

        private static class Node {
            private Node[] children = new Node[ASCII+1];
            private boolean exists;
            private Node pi;
        }
    }
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        AhoCorasick ahoCorasick = new AhoCorasick();
        for (int i=0; i<N; i++)
            ahoCorasick.put(reader.readLine());
        Q = Integer.parseInt(reader.readLine());
        for (int i=0; i<Q; i++)
            if (ahoCorasick.contains(reader.readLine()))
                System.out.println("YES");
            else
                System.out.println("NO");
    }
}
