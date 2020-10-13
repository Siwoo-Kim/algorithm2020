package com.siwoo.p14000;

import java.util.*;

/**
 *
 * 가능한 경우의 수가 매우 많을 것 같은데?
 *  -> 시간 복잡도를 계산할 때 -, / 연산은 고려할 필요가 없다.
 *  -> 정점의 제한이 매우 클 것 같지만 제한이 10억이고 x 은 2x 혹은 x^2 형태, 이때 2x 만 고려.
 *  -> 2x 로 생각한다면 1 -> 2 -> 4 -> 8 .. 16 -> 2^x 급으로 커짐.
 *  -> 2^30 이면 10억에 이르므로 30 번 이상을 bfs 하지 않음을 추론.
 */
public class P14395 {
    private static Scanner scanner = new Scanner(System.in);
    private static long S, T;

    public static void main(String[] args) {
        S = scanner.nextInt();
        T = scanner.nextInt();
        if (S == T) {
            System.out.println(0);
            return;
        }
        Map<Long, Character> ops = new HashMap<>();
        Map<Long, Long> parent = new HashMap<>();
        ops.put(S, '\n');
        //ops.put(1L, '/');
        parent.put(S, S);
        //parent.put(1L, S);    //사전순이라 안먹힘.
        Queue<Long> q = new LinkedList<>();
        q.add(S);
        while (!q.isEmpty()) {
            long s = q.poll();
            if (s == T) {
                //found
                StringBuilder sb = new StringBuilder();
                while (s != S) {
                    sb.append(ops.get(s));
                    s = parent.get(s);
                }
                System.out.println(sb.reverse().toString());
                return;
            }
            long s2 = s * s;
            if (!ops.containsKey(s2)) {
                q.add(s2);
                ops.put(s2, '*');
                parent.put(s2, s);
            }
            long s1 = s + s;
            if (!ops.containsKey(s1)) {
                q.add(s1);
                ops.put(s1, '+');
                parent.put(s1, s);
            }
            if (!ops.containsKey(1L)) {
                q.add(1L);
                ops.put(1L, '/');
                parent.put(1L, s);
            }
        }
        System.out.println(-1);
    }
}
