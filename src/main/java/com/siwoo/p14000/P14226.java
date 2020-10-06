package com.siwoo.p14000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.*;

/**
 * 문제에 조건이 달리는 간선.
 *  조건이 있는 간선을 이용할 때, 순회 도중 도착한 정점 v 은 어떤 간선을 이용했냐에 따라 다른 정점으로 분류해야 한다.
 *  이런 경우를 "정점을 나눈다" 라고 한다.
 *
 *  pv + condition1 -> v1
 *  pv + condition2 -> v2
 *  pv + condition3 -> v3
 */
@Used(algorithm = Algorithm.BFS)
public class P14226 {
    private static Scanner scanner = new Scanner(System.in);
    private static int S;

    private static class State {
        int emoticons;
        int clip;

        public State(int e, int c) {
            this.emoticons = e;
            this.clip = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return emoticons == state.emoticons &&
                    clip == state.clip;
        }

        @Override
        public int hashCode() {
            return Objects.hash(emoticons, clip);
        }
    }

    public static void main(String[] args) {
        S = scanner.nextInt();
        Map<State, Integer> operations = new HashMap<>();
        Queue<State> q = new LinkedList<>();
        State start = new State(1, 0);
        q.add(start);
        operations.put(start, 0);
        while (!q.isEmpty()) {
            State current = q.poll();
            int d = operations.get(current);
            if (current.emoticons == S) {
                System.out.println(d);
                return;
            }
            //copy
            State copy = new State(current.emoticons, current.emoticons);
            State paste = null;
            if (current.clip != 0)
                paste = new State(current.emoticons + current.clip, current.clip);
            State delete = null;
            if (current.emoticons > 0)
                delete = new State(current.emoticons-1, current.clip);
            for (State next: Arrays.asList(copy, paste, delete))
                if (next != null &&
                        !operations.containsKey(next)) {
                    operations.put(next, d + 1);
                    q.add(next);
                }
        }
    }

}
