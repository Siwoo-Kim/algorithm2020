package com.siwoo.p5000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

@Used(algorithm = Algorithm.TRIE)
public class P5052 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N;
    
    private static class Trie {
        private static int MAX = 10;
        private Node root = new Node();
        
        private static class Node {
            private Node[] children = new Node[MAX];
        }
        
        public boolean put(String s) {
            return put(s, 0, root);
        }

        private boolean put(String s, int i, Node root) {
            if (s.length() == i) return true;
            int index = s.charAt(i) - '0';
            Node node = root.children[index];
            if (s.length()-1 == i && node != null)
                return false;
            else if (node == null)
                node = root.children[index] = new Node();
            return put(s, i+1, node);
        }
    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(reader.readLine());
        while (T != 0) {
            N = Integer.parseInt(reader.readLine());
            Trie trie = new Trie();
            PriorityQueue<String> q 
                    = new PriorityQueue<>((a, b) -> Integer.compare(b.length(), a.length()));
            for (int i=0; i<N; i++)
                q.add(reader.readLine());
            boolean ok = true;
            while (!q.isEmpty())
                if (!trie.put(q.poll())) {
                    ok = false;
                    break;
                }
            System.out.println(ok ? "YES": "NO");
            T--;
        }
    }
}
