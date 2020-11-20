package com.siwoo.p12000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.*;

@Used(algorithm = Algorithm.BACK_TRACKING)
public class P12869 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, ANSWER = 60;
    private static final int[] SCV = new int[3];
    private static final Set<State> VISIT = new HashSet<>();
    private static final List<int[]> PERMS = new ArrayList<>();
    
    static {
        int[] p = {0, 1, 2};
        do {
            PERMS.add(Arrays.copyOf(p, p.length));
        } while (nextPermutations(p));
    }

    private static boolean nextPermutations(int[] p) {
        int i = p.length-1;
        while (i>0 && p[i-1] >= p[i]) i--;
        if (i == 0) return false;
        int j = p.length-1;
        while (p[i-1]>=p[j]) j--;
        swap(i-1, j, p);
        j = p.length-1;
        while (j>i)
            swap(i++, j--, p);
        return true;
    }

    private static void swap(int i, int j, int[] p) {
        int t = p[i];
        p[i] = p[j];
        p[j] = t;
    }
    
    private static class State {
        private int time;
        private Set<Integer> sets = new HashSet<>();

        public State(int time, int s1, int s2, int s3) {
            this.time = time;
            sets.add(s1);
            sets.add(s2);
            sets.add(s3);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return time == state.time &&
                    Objects.equals(sets, state.sets);
        }

        @Override
        public int hashCode() {
            return Objects.hash(time, sets);
        }
    }
    
    public static void main(String[] args) {
        N = scanner.nextInt();
        for (int i=0; i<N; i++)
            SCV[i] = scanner.nextInt();
        attack(0, SCV[0], SCV[1], SCV[2]);
        System.out.println(ANSWER);
    }

    private static void attack(int time, int s1, int s2, int s3) {
        if (time > 60) {
            //shouldn't happen
            throw new AssertionError();
        }
        int[] hp = {s1, s2, s3};
        for (int[] p: PERMS) {
            int ss1 = tab(hp[p[0]], 9);
            int ss2 = tab(hp[p[1]], 3);
            int ss3 = tab(hp[p[2]], 1);
            State state = new State(time+1, ss1, ss2, ss3);
            if (ss1 == 0 && ss2 == 0 && ss3 == 0)   //end game
                ANSWER = Math.min(ANSWER, time+1);
            else if (!VISIT.contains(state)) {
                VISIT.add(state);
                attack(time + 1, ss1, ss2, ss3);
            }
        }
    }

    private static int tab(int hp, int dmg) {
        return Math.max(hp - dmg, 0);
    }
}
