package com.siwoo.p5000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.*;

@Used(algorithm = Algorithm.BFS)
public class P5014 {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        final int F = scanner.nextInt(), 
                S = scanner.nextInt(), 
                G = scanner.nextInt(), 
                U = scanner.nextInt(), 
                D = scanner.nextInt();
        Queue<Integer> q = new LinkedList<>();
        q.add(S);
        Map<Integer, Integer> distance = new HashMap<>();
        distance.put(S, 0);
        while (!q.isEmpty()) {
            int v = q.poll();
            if (v == G) {
                System.out.println(distance.get(v));
                return;
            }
            int w1 = v + U;
            int w2 = v - D;
            for (int w: Arrays.asList(w1, w2)) {
                if (w >= 1 && w <= F && !distance.containsKey(v)) {
                    distance.put(w, distance.get(w) + 1);
                    q.add(w);
                }
            }
        }
        System.out.println("use the stairs");
    }
}
