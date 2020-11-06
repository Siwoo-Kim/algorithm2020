package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.*;

@Used(algorithm = Algorithm.BFS)
public class P16953 {
    private static Scanner scanner = new Scanner(System.in);
    private static long A, B;
    
    public static void main(String[] args) {
        A = scanner.nextLong();
        B = scanner.nextLong();
        Map<Long, Long> parent = new HashMap<>();
        Queue<Long> q = new LinkedList<>();
        q.add(A);
        parent.put(A, A);
        
        long answer = -1;
        while (!q.isEmpty()) {
            long v = q.poll();
            if (B == v) {
                //found
                long cnt = 1;
                while (parent.get(v) != v) {
                    v = parent.get(v);
                    cnt++;
                }
                answer = cnt;
                break;
            }
            long a1 = v * 2;
            long a2 = v * 10 + 1;
            for (long a: Arrays.asList(a1, a2)) {
                if (parent.containsKey(a)) continue;
                if (a > B) continue;
                parent.put(a, v);
                q.add(a);
            }
        }
        System.out.println(answer);
    }
}
