package com.siwoo.p17000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.*;

/**
 * 수빈이의 위치는 n, 동생은 k 일때
 *  수빈이의 초당 이동 방법은 2n, n-1, n+1
 *  동생의 s 초시 위치 (seconds*seconds+1)/2 + k
 *  
 *  bfs 시 n-1, n+1 의 이동 경로때문에 다른 초에 한 정점을 두 번 방문한다.
 *      => n 은 2 초후에 다시 방문 가능.
 *      => n 이 짝수라면 짝수초 후에 재 방문 가능.
 *      => n 이 홀수라면 홀수초 후에 재 방문 가능.
 *  
 *  bfs 을 이용해 수빈이가 도달 가능한 정점 x 에 대한 최단 이동 시간을 
 *      짝수와 홀수로 나누어 d[x][even] 에 저장.
 *  
 *      d[x][even] 은 수빈이가 정점 x 에 짝수 혹은 홀수 시간에 도착했을 때의 최소 시간.
 *      
 *  이후에 동생의 위치 k 을 초 t 마다 이동시켜보면서
 *      만약 d[k][t%2 == 0] <= t 보다 작다면 수빈이는 2 초후에 그 위치에 도달 가능.
 *      
 */
@Used(algorithm = Algorithm.BFS)
public class P17071 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, K, MAX = 500000;
    
    private static class Pair {
        private int x;
        private boolean even;

        public Pair(int x, boolean even) {
            this.x = x;
            this.even = even;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x &&
                    even == pair.even;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, even);
        }
    }
    
    public static void main(String[] args) {
        N = scanner.nextInt();
        K = scanner.nextInt();
        Queue<Pair> q = new LinkedList<>();
        Pair s = new Pair(N, true);
        q.add(s);
        Map<Pair, Integer> dist = new HashMap<>();
        dist.put(s, 0);
        while (!q.isEmpty()) {
            Pair p = q.poll();
            for (int w: Arrays.asList(p.x*2, p.x-1, p.x+1)) {
                if (w >= 0 && w <= MAX) {
                    Pair p2 = new Pair(w, !p.even);
                    if (!dist.containsKey(p2)) {
                        dist.put(p2, dist.get(p) + 1);
                        q.add(p2);
                    }
                }
            }
        }
        for (int t=0; K<=MAX; t++) {
            K += t;
            Pair p = new Pair(K, t % 2 == 0);
            if (dist.containsKey(p) && dist.get(p) <= t) {
                System.out.println(t);
                return;
            }
        }
        System.out.println(-1);
    }
    
}
