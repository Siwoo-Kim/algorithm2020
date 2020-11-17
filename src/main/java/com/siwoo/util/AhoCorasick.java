package com.siwoo.util;

import java.util.*;

/**
 * 아호코라식 Aho-Corasick
 *      KMP + TRIE 을 이용한 알고리즘.
 *
 * 문자열 알고리즘 정리.
 *      KMP(fail), Rabin-Karp(hash): 문자열 s 에서 패턴 p 을 찾는 알고리즘.
 *      TRIE(prefix-tree):   문자 집합 S 에서 문자 s 을 찾는 알고리즘.
 *      Aho-Corasick:   문자 s 에 대해 패턴 집합 P 중 매칭되는 패턴을 찾는 알고리즘.
 *
 *  fail(i) 의 ps = 문자열 S의 0..i 까지 부분 문자열 중 prefix == suffix 인 최장 길이
 *  TRIE = 문자열 S 추가시 모든 prefix 가 노드로 저장. => fail 을 구하기 위한 prefix 와 같은 정보.
 *  
 *  * 노드의 pi = 해당 노드의 suffix 와 일치하면서 해당 suffix 을 prefix 로 가지면서 최장 길이인 문자열을 가지는 노드.
 *      if Node = "abcd"
 *      then pi can be tree[{"bcd", "cd", "d"}]
 *          
 *  root => a [pi=root] => ab[pi=root] => aba[pi=a] => abab[pi=ab]
 *  fail        0               0           1               2
 *  
 *  
 *  Linking pi (fail link).
 *      노드 v 에 대해 모든 자식 c 을 순회하면서
 *          1. v 의 pi 을 시작으로 pi 가 문자 c 가질때까지 위쪽으로 순회한다.
 *          2-1. 위를 만족하는 pi 가 존재한다면 c.pi 로 업데이트 한다.
 *          2-2. 위를 만족하는 pi 가 없다면 root 를 c.pi 로 업데이트 한다. 
 *      
 *      root => A, B    => AB, BC   => ABA
 *      then v=AB, v.pi=root, c=ABC, pi=root['B'].children['C']=BC 
 * 
 *      
 *      
 *  모든 노드에 대한 Linking pi.
 *      level-traverse 을 해야 하므로 bfs 을 수행. 
 */
public class AhoCorasick {
    private static final int ASCII = 1 << 7;
    private static final Node root = new Node();
    private final List<String> PATTERNS;
    
    private static class Node {
        private Node[] children = new Node[ASCII+1];
        private Node pi;
        private boolean exist;
        private String s;   //for debugging

        @Override
        public String toString() {
            return "Node{" +
                    "pi=" + (pi == null? "": pi.s) +
                    ", s='" + s + '\'' +
                    '}';
        }
    }
    
    public AhoCorasick(List<String> patterns) {
        PATTERNS = new ArrayList<>(patterns);
        for (String p: PATTERNS)
            put(p, 0, root);
        build();
    }

    private void build() {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        root.pi = root;
        while (!q.isEmpty()) {
            Node v = q.poll();
            for (int i=0; i<=ASCII; i++) {
                if (v.children[i] == null) continue;
                Node w = v.children[i];
                if (v == root)    //1 len node's pi is always root (no suffix)
                    w.pi = v;
                else {  //KMP fail
                    Node pi = v.pi;
                    while (pi != root && pi.children[i] == null)    //while v's pi doesn't have current char
                        pi = pi.pi; 
                    if (pi.children[i] != null)
                        pi = pi.children[i];
                    w.pi = pi;  //link pi
                }
                Node pi = w.pi;
                w.exist |= pi.exist;
                q.add(w);
            }
        }
    }

    private void put(String p, int i, Node root) {
        if (p.length() == i) {
            root.exist = true;
            return;
        }
        char c = p.charAt(i);
        Node node = root.children[c];
        if (node == null) {
            node = root.children[c] = new Node();
            node.s = p.substring(0, i+1); //prefix
        }
        put(p, i+1, node);
    }
    
    public List<String> search(String s) {
        Node node = root;
        List<String> found = new ArrayList<>();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            while (node != root && node.children[c] == null)
                node = node.pi;
            if (node.children[c] != null)   //bug
                node = node.children[c];
            if (node.exist)
                found.add(node.s);
        }
        return found;
    }

    public static void main(String[] args) {
        AhoCorasick ahoCorasick = new AhoCorasick(Arrays.asList("ab", "dabd"));

        System.out.println(ahoCorasick.search("dabbbb"));
        System.out.println(ahoCorasick.search("ABCDAD"));
        System.out.println(ahoCorasick.search("ABCDEABABABABCD"));
    }
}
