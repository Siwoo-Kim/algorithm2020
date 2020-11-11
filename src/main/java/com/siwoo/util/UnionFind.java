package com.siwoo.util;

/**
 * 유니온 파인드
 *  상호 배타적 집합 (Disjoint-set)
 *  연결 요소 검색 알고리즘 (혹은 자료구조)
 *      connected(v, w) - v 와 w 가 연결되었는가? 
 *      connect - v 와 w 을 집합으로 합치는 연산.
 *      
 * BST 와 마찬가지로 입력 순서에 따라 O(N) 이 걸릴 수 있다.
 * 
 *  유니온 파인드 개선 방법.
 *      1. 경로 압축 (zip)
 *          => 연결 요소를 알아내기 위해선 루트만이 중요하다는 특성을 사용. (부모는 의미 없음) 
 *      2. 작은 뿌리를 큰 뿌리에 합체하기.   (weighting)
 *          => 3 height 의 뿌리를 1 height 의 뿌리에 연결하면 height 가 4인 뿌리가 만들어지지만
 *          1 height 의 뿌리를 1 크기의 height 에 연결하면 높이가 3인 뿌리가 생성.
 *          
 *  위의 방법을 사용하면 N < 2^(2^2^2^16) 이라면 항상 5보다 작다.          
 */
public class UnionFind {
    private int size;
    private int[] components;
    private int[] sizes;
    
    public UnionFind(int size) {
        this.size = size;
        components = new int[size];
        sizes = new int[size];
        for (int i=0; i<size; i++) {
            components[i] = i;
            sizes[i] = 1;
        }
    }

    public boolean connected(int v, int w) {
        return get(v) == get(w);
    }
    
    public void connect(int v, int w) {
        if (connected(v, w)) return;
        v = get(v);
        w = get(w);
        if (sizes[v] < sizes[w]) {  //weighting
            int t = v;
            v = w;
            w = t;
        }
        components[w] = v;
        sizes[v] += sizes[w];
        size--;
    }

    private int get(int v) {
        if (isRoot(v))
            return v;
        int p = components[v];
        return components[v] = get(p);  //zip
    }

    private boolean isRoot(int v) {
        return components[v] == v;
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(15);
        uf.connect(1, 2);
        uf.connect(2, 3);
        uf.connect(3, 4);
        uf.connect(4, 5);
        
        uf.connect(6, 7);
        uf.connect(7, 8);
        uf.connect(9, 10);
        uf.connect(10, 11);
        
        System.out.println(uf.connected(1, 3));
        System.out.println(uf.connected(1, 6));

        System.out.println(uf.get(1));
        System.out.println(uf.components[3] == uf.get(1));
        System.out.println(uf.components[4] == uf.get(1));
        System.out.println(uf.components[5] == uf.get(1));  //check zip
    }
}
