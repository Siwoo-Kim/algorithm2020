package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.GREEDY)
public class P1202 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static int N, K;
    public static TreeMap<Integer, Integer> treeMap = new TreeMap<>();
    public static int[] bags;
    public static Pair[] pairs;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        bags = new int[K];
        pairs = new Pair[N];
        for (int i=0; i<N; i++) {
            token = new StringTokenizer(reader.readLine());
            pairs[i] = new Pair(
                    Integer.parseInt(token.nextToken()),
                    Integer.parseInt(token.nextToken()), i);
        }
        Arrays.sort(pairs);

        for (int i=0; i<K; i++) {
            bags[i] = Integer.parseInt(reader.readLine());
            treeMap.computeIfAbsent(bags[i], k -> 0);
            treeMap.put(bags[i], treeMap.get(bags[i]) + 1);
        }

        long answer = 0;
        for (int i=0; i<N; i++) {
            if (treeMap.isEmpty()) break;
            Map.Entry<Integer, Integer> bags = treeMap.ceilingEntry(pairs[i].weight);
            if (bags != null) {
                answer += pairs[i].value;
                treeMap.put(bags.getKey(), bags.getValue() - 1);
                if (treeMap.get(bags.getKey()) == 0)
                    treeMap.remove(bags.getKey());
            }
        }
        System.out.println(answer);
    }

    public static class Pair implements Comparable<Pair> {
        final int value, weight, index;

        public Pair(int weight, int value, int index) {
            this.value = value;
            this.weight = weight;
            this.index = index;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(o.value, value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return value == pair.value &&
                    weight == pair.weight &&
                    index == pair.index;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, weight, index);
        }
    }
}
