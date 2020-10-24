package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.*;
import java.util.*;

// 실패
@Used(algorithm = Algorithm.DnC)
public class P1933 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static Stick[] sticks;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        sticks = new Stick[N];
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int f = Integer.parseInt(token.nextToken()),
                    h = Integer.parseInt(token.nextToken()),
                    t = Integer.parseInt(token.nextToken());
            sticks[i] = new Stick(f, t, h);
        }
        Arrays.sort(sticks, Stick.c);
        Sight[] sights = dnc(0, sticks.length-1);
        for (Sight s: sights) {
            writer.write(s.x + " ");
            writer.write(s.height + " ");
        }
        writer.flush();
    }

    private static Sight[] dnc(int left, int right) {
        if (left == right) {
            Stick s = sticks[left];
            return new Sight[] {
                    new Sight(s.from, s.height),
                    new Sight(s.to, 0),
            };
        }
        int mid = (right + left) / 2;
        Sight[] leftSights = dnc(left, mid),
                rightSights = dnc(mid+1, right);
        List<Sight> mergeSights = new ArrayList<>();
        int i = 0, j = 0, lh = 0, rh = 0;
        while (i < leftSights.length
                && j < rightSights.length) {   //until finish up all one side of sights
            Sight l = leftSights[i],
                    r = rightSights[j];
            if (l.x < r.x) {
                lh = l.height;
                int height = Math.max(lh, rh);
                Sight v = new Sight(l.x, height);
                if (mergeSights.size() == 0)
                    mergeSights.add(v);
                else {
                    Sight w = mergeSights.get(mergeSights.size()-1);
                    if (w.height == v.height) {
                    }
                    else if (w.x == v.x) {
                        w.height = v.height;
                        mergeSights.remove(mergeSights.size()-1);
                        mergeSights.add(w);
                    }
                    else mergeSights.add(v);
                }
                i++;
            } else {
                rh = r.height;
                int height = Math.max(lh, rh);
                Sight v = new Sight(r.x, height);
                if (mergeSights.size() == 0)
                    mergeSights.add(v);
                else {
                    Sight w = mergeSights.get(mergeSights.size()-1);
                    if (w.height == v.height) {
                    }
                    else if (w.x == v.x) {
                        w.height = v.height;
                        mergeSights.remove(mergeSights.size()-1);
                        mergeSights.add(w);
                    }
                    else mergeSights.add(v);
                }
                j++;
            }
        }
        while (i < leftSights.length)
            mergeSights.add(leftSights[i++]);
        while (j < rightSights.length)
            mergeSights.add(rightSights[j++]);
        return mergeSights.toArray(new Sight[0]);
    }

    private static class Sight {
        private int x, height;

        public Sight(int x, int height) {
            this.x = x;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Sight{" +
                    "x=" + x +
                    ", height=" + height +
                    '}';
        }
    }

    private static class Stick {
        private final int from, to, height;
        private static Comparator<Stick> c = Comparator.comparingInt(Stick::getFrom)
                .thenComparing(Stick::getHeight)
                .thenComparing(s -> -s.getTo());

        private Stick(int from, int to, int height) {
            this.from = from;
            this.to = to;
            this.height = height;
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
    }
}
