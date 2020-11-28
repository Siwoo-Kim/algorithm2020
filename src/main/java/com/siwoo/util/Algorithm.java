package com.siwoo.util;

public enum Algorithm {
    STACK, QUEUE, GRAPH, LINKED_LIST, HEAP,   //data structure
    DFS, BFS, UNION_FIND,               // graph (reachable)
    TOPOLOGICAL_ORDER, // scheduling
    PRIM, KRUSKAL, // graph-mst
    BELLMAN_FORD, DIJKSTRA, // graph-spt
    GREEDY,
    DnC, BINARY_SEARCH, DP,     //basic
    PERMUTATION, COMBINATION, BINARY_SELECTION, //  brute force 
    BRUTE_FORCE, BACK_TRACKING,
    TWO_POINTER, MEET_IN_THE_MIDDLE,
    KMP, RABIN_KARP, TRIE, AHO_CORASICK, // string
    CCW, LINE_SWEEPING        // 기하학 알고리즘    
}
