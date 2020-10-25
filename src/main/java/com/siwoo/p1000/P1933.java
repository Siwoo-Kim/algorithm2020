package com.siwoo.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class P1933 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static Stick[] sticks;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        sticks = new Stick[N];
        for (int i=0; i<N; i++)
            sticks[i] = new Stick(reader.readLine());
        Pair[] pairs = dnc(0, sticks.length, sticks);
        StringBuilder sb = new StringBuilder();
        for (Pair p: pairs)
            sb.append(p.x()).append(" ")
                .append(p.height()).append(" ");
        System.out.println(sb.toString());
    }

    private static Pair[] dnc(int l, int r, Stick[] sticks) {
        if (r - l <= 1) {
            return new Pair[] {
                    sticks[l].toPair(),
                    new Stick(sticks[l].to + " 0 0").toPair()
            };
        }
        int mid = (r + l) / 2;
        return merge(dnc(l, mid, sticks), dnc(mid, r, sticks));
    }

    private static Pair[] merge(Pair[] left, Pair[] right) {
        List<Pair> merge = new ArrayList<>();
        int i = 0, j = 0, lh = 0, rh = 0;
        while (true) {
            if (i == left.length
                    || j == right.length)
                break;
            int x;
            if (left[i].x() < right[j].x()) {
                lh = left[i].height();
                x = left[i++].x();
            } else {
                rh = right[j].height();
                x = right[j++].x();
            }
            Pair pair = Pair.of(x, Math.max(lh, rh));
            if (valid(merge, pair))
                merge.add(pair);
        }
        while (i < left.length) {
            if (valid(merge, left[i]))
                merge.add(left[i]);
            i++;
        }
        while (j < right.length) {
            if (valid(merge, right[j]))
                merge.add(right[j]);
            j++;
        }
        return merge.toArray(new Pair[0]);
    }

    private static boolean valid(List<Pair> pairs, Pair v) {
        if (pairs.isEmpty()) return true;
        Pair w = pairs.get(pairs.size()-1);
        if (w.height() == v.height()) return false; //cross sticks
        if (w.x() == v.x()) {   // because of sort, last one is bigger
            pairs.set(pairs.size()-1, Pair.of(w.x(), v.height()));  //side effect
            return false;
        }
        return true;
    }

    private interface Pair {
        int x();
        int height();

        static Pair of(int x, int height) {
            return new Pair() {
                @Override
                public int x() {
                    return x;
                }

                @Override
                public int height() {
                    return height;
                }
            };
        }
    }

    private static class Stick {
        private static final Comparator<Stick> c = Comparator
                .comparing(Stick::getFrom)
                .thenComparing(Stick::getHeight)
                .thenComparing(Stick::getTo);
        private final int from, to, height;

        public Stick(String s) {
            String[] data = s.split("\\s+");
            this.from = Integer.parseInt(data[0]);
            this.height = Integer.parseInt(data[1]);
            this.to = Integer.parseInt(data[2]);
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public int getHeight() {
            return height;
        }

        public Pair toPair() {
            return Pair.of(from, height);
        }
    }
}
