package com.siwoo.p10000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.AHO_CORASICK)
public class P10256 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int T;
    
    private static class AhoCorasick {
        private static final int MAX = 4;
        private Node root = new Node();
        
        private static class Node {
            private Node[] children = new Node[MAX];
            private boolean exists;
            private Node pi;
        }
        
        public AhoCorasick(Set<String> P) {
            for (String p: P)
                put(p, 0, root);
            build();
        }

        public int search(String s) {
            Node node = root;
            int cnt = 0;
            for (int i=0; i<s.length(); i++) {
                int index = index(s.charAt(i));
                while (node != root && node.children[index] == null)
                    node = node.pi;
                if (node.children[index] != null)
                    node = node.children[index];
                if (node.exists)
                    cnt++;
            }
            return cnt;
        }

        private void build() {
            root.pi = root;
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            while (!q.isEmpty()) {
                Node v = q.poll();
                for (int i=0; i<MAX; i++) {
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

        private void put(String p, int i, Node root) {
            if (i == p.length()) {
                root.exists = true;
                return;
            }
            int index = index(p.charAt(i));
            Node node = root.children[index];
            if (node == null)
                node = root.children[index] = new Node();
            put(p, i+1, node);
        }

        private int index(char c) {
            switch (c) {
                case 'A': return 0;
                case 'C': return 1;
                case 'G': return 2;
                case 'T': return 3;
            }
            throw new AssertionError();
        }
    }
    
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(reader.readLine());
        while (T > 0) {
            reader.readLine();
            String S = reader.readLine(),
                    M = reader.readLine();
            Set<String> ms = mutate(M);
            AhoCorasick ahoCorasick = new AhoCorasick(ms);
            int cnt = ahoCorasick.search(S);
            System.out.println(cnt);
            T--;
        }
    }

    private static Set<String> mutate(String marker) {
        Set<String> ms = new HashSet<>();
        for (int i=0; i<=marker.length(); i++)
            for (int j=i; j<=marker.length(); j++) {
                StringBuilder sb = new StringBuilder(marker.substring(i, j));
                String s = marker.substring(0, i) + sb.reverse() + marker.substring(j);
                ms.add(s);
            }
        return ms;
    }
}
