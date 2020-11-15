package com.siwoo.p14000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.TRIE)
public class P14426 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    
    private static class Trie {
        private static int ASCII = 1 << 7;
        private static final Node root = new Node();
        
        private static class Node {
            private Node[] children = new Node[ASCII+1];
        }
        
        public void put(String s) {
            put(s, 0, root);
        }

        private void put(String s, int i, Node root) {
            if (s.length() == i) return;
            char c = s.charAt(i);
            Node node = root.children[c];
            if (node == null)
                node = root.children[c] = new Node();
            put(s, i+1, node);
        }
        
        public boolean contains(String s) {
            return contains(s, 0, root);
        }

        private boolean contains(String s, int i, Node root) {
            if (s.length() == i) return true;
            char c = s.charAt(i);
            if (root.children[c] == null)
                return false;
            else
                return contains(s, i+1, root.children[c]);
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        Trie trie = new Trie();
        for (int i=0; i<N; i++)
            trie.put(reader.readLine());
        int cnt = 0;
        for (int i=0; i<M; i++)
            if (trie.contains(reader.readLine()))
                cnt++;
        System.out.println(cnt);
    }
}
