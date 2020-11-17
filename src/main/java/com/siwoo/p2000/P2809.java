package com.siwoo.p2000;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//시간 초과 - line swapping 필요
public class P2809 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static String S;
    private static String[] P;

    private static class AhoCorasick {
        private static final int MAX = 26;
        private final Node root = new Node();
        private final String[] P;

        private AhoCorasick(String[] P) {
            this.P = P;
            for (String p: P)
                put(p, 0, root);
            link();
        }

        private void link() {
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            root.pi = root;
            while (!q.isEmpty()) {
                Node v = q.poll();
                for (int i=0; i<=MAX; i++) {
                    if (v.children[i] != null) {
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
                        Node pi = w.pi;
                        w.link |= pi.link;
                        q.add(w);
                    }
                }
            }
        }

        private void put(String s, int i, Node root) {
            if (s.length() == i) {
                root.exist = root.link = true;
                return;
            }
            int c = s.charAt(i) - 'a';
            Node node = root.children[c];
            if (node == null) {
                node = root.children[c] = new Node();
                node.len = i+1;
            }
            put(s, i+1, node);
        }

        public void search(String s, Set<Integer> visit) {
            Node pi = root;
            for (int i=0; i<s.length(); i++) {
                int index = s.charAt(i) - 'a';
                while (pi != root && pi.children[index] == null)
                    pi = pi.pi;
                if (pi.children[index] != null)
                    pi = pi.children[index];
                if (pi.link) {
                    Node node = pi;
                    int tried = 0;
                    while (!node.exist) {
                        node = node.pi;
                        if (++tried > 100) throw new RuntimeException();
                    }
                    for (int j=0; j<node.len; j++)
                        visit.add(i-node.len+j+1);
                }
            }
        }

        private static class Node {
            private Node[] children = new Node[MAX+1];
            private Node pi;
            private int len;
            private boolean exist;
            private boolean link;
        }
    }
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        S = reader.readLine();
        M = Integer.parseInt(reader.readLine());
        P = new String[M];
        for (int i=0; i<M; i++)
            P[i] = reader.readLine();
        AhoCorasick ahoCorasick = new AhoCorasick(P);
        Set<Integer> visit = new HashSet<>();
        ahoCorasick.search(S, visit);
        System.out.println(S.length() - visit.size());
    }
}
