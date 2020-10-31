package com.siwoo.p17000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P17089 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static Map<Integer, Set<Integer>> friends = new HashMap<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        for (int i=0; i<M; i++) {
            token = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(token.nextToken()),
                    y = Integer.parseInt(token.nextToken());
            friends.computeIfAbsent(x, k -> new HashSet<>());
            friends.computeIfAbsent(y, k -> new HashSet<>());
            friends.get(x).add(y);
            friends.get(y).add(x);
        }
        int answer = -1;
        for (int x: friends.keySet()) {
            for (int y: friends.get(x)) {
                for (int z: friends.keySet()) {
                    if (friends.get(z).contains(x) && friends.get(z).contains(y)) {
                        int cnt = friends.get(x).size() - 2
                                + friends.get(y).size() - 2
                                + friends.get(z).size() - 2;
                        if (answer == -1 || answer > cnt)
                            answer = cnt;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
