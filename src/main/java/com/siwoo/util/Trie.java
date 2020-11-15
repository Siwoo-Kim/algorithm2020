package com.siwoo.util;

/**
 * 트라이 Trie (prefix tree)
 *  문자열 특화 트리 자료구조
 *  각 노드는 어떤 문자열의 prefix 을 표현.
 *  
 *  트라이의 구현 특징
 *      1. 빈 루트가 존재한다.
 *      2. 노드는 클라이언트가 추가하지 않은 문자열을 의미할 수 있기 때문에 flag 을 이용하여 구별.
 *      3. O(M) = M 은 문자열 길이.
 *      4. 메모리의 소모가 심하다. ASCII 보다 큰 인코딩에선 사용을 고려할 것. 
 *      
 */
public class Trie {
    
    private static class Node {
        private Node[] children = new Node[127+1];
        private boolean exists;

        public Node(boolean exists) {
            this.exists = exists;
        }
    }
    
    private Node root = new Node(false);

    public void put(String s) {
        put(s, 0, root);
    }

    private void put(String s, int index, Node root) {
        if (s.length() == index) {
            root.exists = true;
            return;
        }
        char c = s.charAt(index);
        Node node = root.children[c];
        if (root.children[c] == null)
            root.children[c] = node = new Node(false);
        put(s, index+1, node);
    }

    private boolean contains(String s) {
        Node node = get(s, 0, root);
        return node != null && node.exists;
    }

    private Node get(String s, int index, Node root) {
        if (s.length() == index) return root;
        char c = s.charAt(index);
        return root.children[c] == null? null: 
                get(s, index+1, root.children[c]);
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.put("abc");
        System.out.println(trie.contains("abc"));
        System.out.println(trie.contains("ab"));
        System.out.println(trie.contains("ddef"));
        
        trie.put("ace");
        System.out.println(trie.contains("ace"));

        System.out.println(trie.contains("a"));
        trie.put("a");
        System.out.println(trie.contains("a"));
    }

}
