package com.siwoo.util;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 트리 TREE
 *  "싸이클이 없는", "연결" 그래프.
 *  정점이 v 라면, 간선은 항상 v-1
 *
 * 트리의 components.
 *  1. 루트.
 *      모든 트리의 정점 v 은, v 을 루트로한 트리로 간주할 수 있다.
 *  2. 부모, 자식
 *      간선에 직접 연결된 v-w 일때, v 는 부모, w 은 자식.
 *  3. leaf 노드.
 *      자식이 없는 노드.
 *  4. 깊이.
 *      v-root 로부터의 간선의 갯수.
 *  5. 높이.
 *      max(depth(v))
 *  6. 조상, 자손.
 *      p->q 에서 위로만 향하 간선을 따라 도달할 수 있을때,
 *      p 가 q 보다 깊이가 작다면 p 을 조상, q 을 자손.
 *
 * 이진 트리.
 *  자식을 최대 2개만 가지는 트리.
 *
 *  1. perfect binary tree  (마지막 레벨을 제외한 모든 노드의 자식의 갯수 = 2, 모든 리프 노드의 깊이가 같아야 함)
 *      노드의 갯수 - 2^h - 1
 *  2. complete binary tree (perfect binary tree 와 같지만, 마지막 레벨의 오른쪽 부분이 비어있을 수 있음)
 *      -> heap
 *
 * 트리의 표현.
 *  1. Union-Find (array) array[child] = parent
 *  2. Heap (array) array[v] -> array[2*v], array[2*v+1]. v >= 1
 *
 * 트리의 순회.
 *  1. DFS
 *      1-1. pre-order
 *          - root, left, right
 *          - 부모 값을 먼저 필요하여 연산하는 경우.
 *
 *      1-2. in-order
 *          - left, root, right
 *          - 이진 트리에서만 가능.
 *
 *      1-3. post-order 
 *          -> Topological 정렬시 사용. (dfs 시 call stack 의 정점들을 post-order 을 이용하여 stack 에 저장하면 위상 정렬 순서이다.)
 *          stack.top 은 해당 정점을 저장한 스택 아래의 정점을 절대 가르키지 않고, 그 아래의 stack 또한 재귀적으로 이를 만족한다. (단, 순환이 없다는 조건하에)
 *          
 *          - left, root, right
 *          - 자식의 값을 집계한 후 연산하는 경우.
 *
 *  2. BFS
 *      최단 경로는 dfs 로 구할 수 있으므로 bfs 은 쓸 일이 별로 없을지도...
 *
 * 트리의 지름 (diameter)
 *  1. 정점 v 에서 거리가 가진 먼 정점 u 을 구한다. u = farthest(v)
 *  2. 정점 u 에서 모든 정점까지의 거리를 구한 후 최대값을 취한다. diameter = maxDistance(u)
 *
 */
public class Tree<E> {

    private static class Node<E> {
        private E value;
        private Node<E> left, right;

        public Node(E value) {
            this.value = value;
        }

        public void traverse() {
            if (left != null)
                left.traverse();
            System.out.println(value);
            if (right != null)
                right.traverse();
        }

        @Override
        public String toString() {
            return String.format("(%s)", value);
        }
    }

    private Node<E> root;

    public Iterable<E> leaves() {
        return leaves(root, new ArrayList<>());
    }

    public int height() {
        return height(root);
    }

    private int height(Node<E> root) {
        if (root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + (root == this.root? 0: 1);
    }

    public E reduce(BiFunction<E, E, E> reducer) {
        return reduce(root, reducer);
    }

    private E reduce(Node<E> root, BiFunction<E, E, E> reducer) {
        if (root == null) return null;
        //post-order
        E e = reducer.apply(reduce(root.left, reducer), reduce(root.right, reducer));
        return reducer.apply(e, root.value);
    }

    private <E> Iterable<E> leaves(Node<E> root, List<E> result) {
        if (root == null) return result;
        if (root.left == null || root.right == null) {
            result.add(root.value);
            return result;
        }
        leaves(root.left, result);
        leaves(root.right, result);
        return result;
    }

    private int diameter() {
        Graph<Node<E>> G = new Graph<>();
        createGraph(root, G);
        Queue<Node<E>> q = new LinkedList<>();
        Map<Node<E>, Integer> distance = new HashMap<>();
        q.add(root);
        distance.put(root, 0);
        while (!q.isEmpty()) {
            Node<E> v = q.poll();
            for (Graph.Edge<Node<E>> edge: G.edgesOf(v)) {
                if (!distance.containsKey(edge.w)) {
                    distance.put(edge.w, distance.get(v) + 1);
                    q.add(edge.w);
                }
            }
        }
        int max = 0;
        Node<E> u = null;
        for (Node<E> node: G.vertexes())
            if (max < distance.get(node)) {
                u = node;
                max = distance.get(node);
            }
        q = new LinkedList<>();
        distance = new HashMap<>();
        q.add(u);
        distance.put(u, 0);
        while (!q.isEmpty()) {
            Node<E> v = q.poll();
            for (Graph.Edge<Node<E>> edge: G.edgesOf(v)) {
                if (!distance.containsKey(edge.w)) {
                    distance.put(edge.w, distance.get(v) + 1);
                    q.add(edge.w);
                }
            }
        }
        max = 0;
        for (Node<E> node: G.vertexes())
            if (max < distance.get(node))
                max = distance.get(node);
        return max;
    }

    private void createGraph(Node<E> root, Graph<Node<E>> G) {
        if (root == null) return;
        if (root.left != null)
            G.add(new Graph.Edge<>(root, root.left));
        if (root.right != null)
            G.add(new Graph.Edge<>(root, root.right));
        createGraph(root.left, G);
        createGraph(root.right, G);
    }

    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.root = new Node<>(1);
        tree.root.left = new Node<>(2);
        tree.root.left.left = new Node<>(4);
        tree.root.left.right = new Node<>(5);
        tree.root.right = new Node<>(3);
        tree.root.right.left = new Node<>(6);
        tree.root.right.right = new Node<>(7);

        //트리의 재귀적 속성
        tree.root.traverse();
        System.out.println("=====");
        tree.root.left.traverse();
        System.out.println("=====");
        for (int leaf: tree.leaves())
            System.out.println(leaf);
        System.out.println("=====");
        System.out.println(tree.height());
        System.out.println("=====");

        int max = tree.reduce((a, b) -> {
            if (a == null) return b;
            if (b == null) return a;
            return a > b ? a: b;
        });
        System.out.println(max);
        System.out.println(tree.diameter());
    }
}
