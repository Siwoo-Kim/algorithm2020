package com.siwoo.p13000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Used(algorithm = Algorithm.TRIE)
public class P13505 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] A;

    private static class Trie {
        private static Node root = new Node();

        private static class Node {
            private Node[] children = new Node[2];
        }
        
        public void put(int x) {
            put(x, 31, root);
        }

        private void put(int x, int place, Node root) {
            if (place < 0) return;
            int bit = (x & (1 << place)) > 0? 1: 0;
            Node node = root.children[bit];
            if (node == null)
                node = root.children[bit] = new Node();
            put(x, place-1, node);
        }
        
        public int xor(int x) {
            return xor(x, 31, root);
        }

        private int xor(int x, int place, Node root) {
            if (place < 0) return 0;
            int bit = (x & (1 << place)) > 0?1: 0;
            bit = 1 - bit;
            Node node = root.children[bit];
            if (node == null) {
                bit = 1 - bit;
                node = root.children[bit];
            }
            return (bit << place) | xor(x, place-1, node);
        }
    }
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        Trie trie = new Trie();
        for (int i=0; i<N; i++)
            trie.put(A[i]);
        int max = 0;
        for (int i=0; i<N; i++) {
            int x = trie.xor(A[i]);
            max = Math.max(max, A[i] ^ x);
        }
        System.out.println(max);
    }
}
