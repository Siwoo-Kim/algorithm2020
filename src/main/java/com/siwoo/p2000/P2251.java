package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.*;

/**
 * BFS & 정점의 분리
 * 
 * a, b, c 에서, x -> y 물을 이동시 나오는 경우의 수 = 6
 * a -> b 의 물을 부을시, 물의 이동량 = Math.min((b.capacity-b.water), a.water)
 * a, b 을 가진 배열에서  a 선택시 나머지 b 을 찾는 법. N - a.index
 * O(200^3)=8백만=ok
 * 
 */
@Used(algorithm = Algorithm.BFS)
public class P2251 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Bucket A = new Bucket(0, scanner.nextInt(), 0),
                B = new Bucket(0, scanner.nextInt(), 1);
        int x = scanner.nextInt();
        Bucket C = new Bucket(x, x, 2);
        Set<Integer> answer = new HashSet<>();
        Queue<State> q = new LinkedList<>();
        Set<State> visit = new HashSet<>();
        State s = new State(new Bucket[]{A, B, C});
        q.add(s);
        visit.add(s);
        
        while (!q.isEmpty()) {
            State v = q.poll();
            Bucket a = v.buckets[0],
                    b = v.buckets[1],
                    c = v.buckets[2];
            if (a.water == 0)
                answer.add(c.water);
            for (Bucket from: Arrays.asList(a, b, c)) {
                for (Bucket to: Arrays.asList(a, b, c)) {
                    if (from == to) continue;
                    int cap = to.capacity - to.water;
                    int w = Math.min(from.water, cap);
                    Bucket b1 = new Bucket(from.water-w, from.capacity, from.index),
                            b2 = new Bucket(to.water+w, to.capacity, to.index);
                    Bucket other = v.buckets[3-(b1.index+b2.index)];
                    Bucket[] buckets = new Bucket[3];
                    for (Bucket bucket: Arrays.asList(b1, b2, other))
                        buckets[bucket.index] = bucket;
                    State state = new State(buckets);
                    if (!visit.contains(state)) {
                        q.add(state);
                        visit.add(state);
                    }
                }    
            }
        }
        answer.stream().sorted().forEach(i -> System.out.print(i + " "));
    }
    
    private static class State {
        Bucket[] buckets;

        public State(Bucket[] buckets) {
            this.buckets = buckets;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return Arrays.equals(buckets, state.buckets);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(buckets);
        }
    }
    
    private static class Bucket {
        private int water, capacity, index;

        public Bucket(int water, int capacity, int index) {
            this.water = water;
            this.capacity = capacity;
            this.index = index;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bucket bucket = (Bucket) o;
            return water == bucket.water &&
                    capacity == bucket.capacity &&
                    index == bucket.index;
        }

        @Override
        public int hashCode() {
            return Objects.hash(water, capacity, index);
        }

        @Override
        public String toString() {
            return "Bucket{" +
                    "water=" + water +
                    ", capacity=" + capacity +
                    ", index=" + index +
                    '}';
        }
    }
}
