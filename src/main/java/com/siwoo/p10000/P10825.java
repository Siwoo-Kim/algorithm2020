package com.siwoo.p10000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P10825 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        List<S> list = new ArrayList<>(N*2);
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            String name = token.nextToken();
            list.add(new S(
                    Integer.parseInt(token.nextToken()),
                    Integer.parseInt(token.nextToken()),
                    Integer.parseInt(token.nextToken()),
                    name));
        }
        Collections.sort(list, S.C);
        StringBuilder sb = new StringBuilder();
        for (S s: list)
            sb.append(s.name).append("\n");
        System.out.println(sb);
    }

    private static class S {
        private static Comparator<S> C = Comparator.comparing(S::getA).reversed()
                .thenComparing(s -> s.b)
                .thenComparing(s -> -s.c)
                .thenComparing(s -> s.name);
        final int a, b, c;
        final String name;

        public S(int a, int b, int c, String name) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.name = name;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int getC() {
            return c;
        }

        public String getName() {
            return name;
        }
    }
}
