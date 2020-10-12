package com.siwoo.p12000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.*;

/**
 * 일단 연산 중 중복이 매우 많다.
 * 3 개에서 나올 수 있는 모든 2 쌍을 선택시 선택하지 않은 돌인 SUM-A-B 로 쉽게 구할 수 있다.
 * 이를 BFS 로 순회시 3 쌍이 아닌 2 쌍으로 순회를 한다.
 *
 * 문제의 입력으로 A,B,C <= 500 이라
 * 각 A,B,C 가 가질 수 있는 돌의 갯수는 1000을 넘지 않으므로
 * 총 경우의 수는 1000^3 => 2 개로 쌍으로 하면 1000^2
 *
 */
@Used(algorithm = Algorithm.BFS)
public class P12886 {
    private static Scanner scanner = new Scanner(System.in);
    private static int a, b, c, SUM;

    public static void main(String[] args) {
        a = scanner.nextInt();
        b = scanner.nextInt();
        c = scanner.nextInt();
        SUM = a + b + c;
        if (SUM % 3 != 0) {
            System.out.println(0);
            return;
        }
        Queue<Pair> q = new LinkedList<>();
        Set<Pair> visit = new HashSet<>();
        q.add(new Pair(a, b));
        visit.add(new Pair(a, b));
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            if (pair.x == pair.y && pair.y == pair.other()) {
                System.out.println(1);
                return;
            }
            int[] ints = new int[]{pair.x, pair.y, pair.other()};
            for (int i=0; i<3; i++) {
                for (int j=0; j<3; j++) {       // all pair case
                    if (ints[i] < ints[j]) {
                        int[] b = {pair.x, pair.y, pair.other()};
                        b[i] += ints[i];
                        b[j] -= ints[i];
                        Pair w = new Pair(b[i], b[j]);
                        if (!visit.contains(w)) {
                            q.add(w);
                            visit.add(w);
                        }
                    }
                }
            }

        }
        System.out.println(0);
    }

    private static class Pair {
        private int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int other() {
            return SUM - (x + y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
