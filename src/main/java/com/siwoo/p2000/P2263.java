package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Used(algorithm = Algorithm.DnC)
public class P2263 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static Map<Integer, Integer> inOrderMap = new HashMap<>();
    private static int[] IN_ORDER, POST_ORDER;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        IN_ORDER = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int i=0; i<N; i++)
            inOrderMap.put(IN_ORDER[i], i);
        POST_ORDER = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        preOrder(0, N-1, 0, N-1, IN_ORDER, POST_ORDER);
    }

    private static void preOrder(int inStart, int inEnd, int postStart, int postEnd, int[] inOrder, int[] postOrder) {
        if (inStart > inEnd || postStart > postEnd) return;
        int root = postOrder[postEnd];
        System.out.print(root + " ");
        int inRoot = inOrderMap.get(root);
        int left = inRoot - inStart;
        preOrder(inStart, inRoot-1, postStart, postStart+left-1, inOrder, postOrder);
        preOrder(inRoot+1, inEnd, postStart+left, postEnd-1, inOrder, postOrder);
    }
}
