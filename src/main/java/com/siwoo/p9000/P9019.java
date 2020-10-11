package com.siwoo.p9000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.*;

@Used(algorithm = Algorithm.BFS)
public class P9019 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N;

    public static void main(String[] args) {
        N = scanner.nextInt();
        for (int i=0; i<N; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            String answer = bfs(a, b);
            System.out.println(answer);
        }
    }

    private static class Result {
        int value;
        char operator;

        public Result(int value, char operator) {
            this.value = value;
            this.operator = operator;
        }
    }

    private static String bfs(final int A, final int B) {
        if (A == B) return "";
        Queue<Integer> q = new LinkedList<>();
        q.add(A);
        Map<Integer, Character> operators = new HashMap<>();
        Map<Integer, Integer> pathTo = new HashMap<>();
        operators.put(A, '\n');
        pathTo.put(A, A);
        while (!q.isEmpty()) {
            int v = q.poll();
            if (v == B) {
                StringBuilder sb = new StringBuilder();
                while (v != A) {
                    sb.append(operators.get(v));
                    v = pathTo.get(v);
                }
                return sb.reverse().toString();
            }
            for (Result w: register(v)) {
                if (!pathTo.containsKey(w.value)) {
                    operators.put(w.value, w.operator);
                    pathTo.put(w.value, v);
                    q.add(w.value);
                }
            }
        }
        throw new AssertionError();
    }

    private static Result[] register(int v) {
        //D
        Result d = null;
        if (v << 1 > 9999)
            d = new Result((v << 1) % 10000, 'D');
        else
            d = new Result(v << 1, 'D');
        Result s = null;
        //S
        if (v == 0)
            s = new Result(9999, 'S');
        else
            s = new Result(v-1, 'S');
        Result l = new Result((v % 1000 * 10) + (v / 1000), 'L');
        Result r = new Result(((v % 10) * 1000) + (v / 10), 'R');
        return new Result[]{d, s, l, r};
    }
}
