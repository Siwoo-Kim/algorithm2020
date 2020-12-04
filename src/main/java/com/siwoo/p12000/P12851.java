package com.siwoo.p12000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.*;

@Used(algorithm = Algorithm.BFS)
public class P12851 {
    private static Scanner scanner = new Scanner(System.in);
    private static int MAX = 100000;
    
    public static void main(String[] args) {
        int N = scanner.nextInt(),
                K = scanner.nextInt();
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        Map<Integer, State> dist = new HashMap<>();
        dist.put(N, new State(0, 0, 1));
        while (!q.isEmpty()) {
            int v = q.poll();
            State s = dist.get(v);
            for (int w: Arrays.asList(v+1, v-1, v*2)) {
                if (w >= 0 && w <= MAX) {
                    if (!dist.containsKey(w)) {
                        dist.put(w, new State(w, s.seconds+1, s.cases));
                        q.add(w);
                    } else {
                        if (s.seconds+1 == dist.get(w).seconds) {
                            dist.get(w).cases += s.cases;
                        }
                    }
                }
            }
        }

        State s = dist.get(K);
        System.out.println(s.seconds);
        System.out.println(s.cases);
    }

    private static class State {
        int v, seconds, cases;

        public State(int v, int seconds, int cases) {
            this.v = v;
            this.seconds = seconds;
            this.cases = cases;
        }
        
        @Override
        public String toString() {
            return "State{" +
                    "v=" + v +
                    ", seconds=" + seconds +
                    ", cases=" + cases +
                    '}';
        }
    }
}
