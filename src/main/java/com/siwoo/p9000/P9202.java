package com.siwoo.p9000;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class P9202 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String[] WORDS;
    private static char[][] BOGGLE;
    private static int N, M, MAX = 4;
    private static boolean[][] visit = new boolean[MAX][MAX];
    private static int[] SCORES = { 0, 0, 0, 1, 1, 2, 3, 5, 11 };
    
    private static class Trie {
        private static final int ASCII = 1 << 7;
        private final Node root = new Node();

        public void search(Point v, Set<String> result) {
            search(v, "", result, root);
        }
        
        private void search(Point v, String s, Set<String> result, Node root) {
            if (s.length() == 8) return;
            if (visit[v.x][v.y]) return;
            visit[v.x][v.y] = true;
            char c = BOGGLE[v.x][v.y];
            if (root.children[c] == null) {
                visit[v.x][v.y] = false;
                return;
            }
            s = s + c;
            if (root.children[c].exists)
                result.add(s);
            for (Point d: Point.D) {
                Point w = new Point(v.x + d.x, v.y + d.y);
                if (!w.valid()) continue;
                search(w, s, result, root.children[c]);
            }
            visit[v.x][v.y] = false;
        }

        private static class Node {
            private Node[] children = new Node[ASCII+1];
            private boolean exists;
        }

        public void put(String s) {
            put(s, 0, root, new StringBuilder());
        }

        private void put(String s, int i, Node root, StringBuilder sb) {
            if (s.length() == i) {
                root.exists = true;
                return;
            }
            char c = s.charAt(i);
            Node node = root.children[c];
            if (node == null) {
                node = root.children[c] = new Node();
            }
            put(s, i+1, node, sb);
        }

    }
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        WORDS = new String[N];
        Trie trie = new Trie();
        for (int i=0; i<N; i++) {
            WORDS[i] = reader.readLine();
            trie.put(WORDS[i]);
        }
        reader.readLine();
        M = Integer.parseInt(reader.readLine());
        while (M != 0) {
            BOGGLE = new char[MAX][MAX];
            for (int i=0; i<MAX; i++)
                BOGGLE[i] = reader.readLine().toCharArray();
            Set<String> result = new HashSet<>();
            for (int i=0; i<MAX; i++)
                for (int j=0; j<MAX; j++)
                    trie.search(new Point(i, j), result);
                
            int scores = result.stream()
                    .mapToInt(s -> SCORES[s.length()])
                    .sum();
            int maxLength = result.stream().mapToInt(String::length)
                    .max()
                    .getAsInt();
            String maxWord = result.stream()
                    .filter(s -> s.length() == maxLength)
                    .sorted()
                    .collect(toList())
                    .get(0);
            System.out.printf("%d %s %d%n", scores, maxWord, result.size());
            M--;
            if (M != 0)
                reader.readLine();
        }
    }
    
    private static class Point {
        private static Point[] D = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1),
                new Point(-1, -1), new Point(-1, 1),
                new Point(1, -1), new Point(1, 1)
        };
        
        private final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < MAX && y >= 0 && y < MAX;
        }
    }
}
