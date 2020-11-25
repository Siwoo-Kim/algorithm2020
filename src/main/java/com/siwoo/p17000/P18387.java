package com.siwoo.p17000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

@Used(algorithm = Algorithm.CCW)
public class P18387 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Line line1 = new Line(
                new Point(scanner.nextLong(), scanner.nextLong()),
                new Point(scanner.nextLong(), scanner.nextLong()));
        Line line2 = new Line(
                new Point(scanner.nextLong(), scanner.nextLong()),
                new Point(scanner.nextLong(), scanner.nextLong()));
        System.out.println(intersect(line1, line2)? 1: 0);
    }

    private static boolean intersect(Line line1, Line line2) {
        int c1 = ccw(line1.s, line1.e, line2.s) * ccw(line1.s, line1.e, line2.e);
        int c2 = ccw(line2.s, line2.e, line1.s) * ccw(line2.s, line2.e, line1.e);
        if (c1 == 0 && c2 == 0) {
            Point p1 = line1.s, p2 = line1.e,
                    p3 = line2.s, p4 = line2.e;
            if (p1.compareTo(p2) > 0) {
                Point t = p1;
                p1 = p2;
                p2 = t;
            }
            if (p3.compareTo(p4) > 0) {
                Point t = p3;
                p3 = p4;
                p4 = t;
            }
            return p3.compareTo(p2) <= 0 && p1.compareTo(p4) <= 0;
        }
        return c1 <= 0 && c2 <= 0;
    }

    private static int ccw(Point p1, Point p2, Point p3) {
        long r = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
        r -= p1.y * p2.x + p2.y * p3.x + p3.y * p1.x;
        if (r == 0) return 0;
        return r > 0? 1: -1;
    }

    private static class Point implements Comparable<Point> {
        final long x, y;

        private Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point p) { //Long.compare 왜 틀리는지 이해할 수 없다.
            if (this.x < p.x) {
                return -1;
            } else if (this.x == p.x) {
                if (this.y < p.y) return -1;
                if (this.y > p.y) return 1;
                return 0;
            } else {
                return 1;
            }
        }
    }

    private static class Line {
        final Point s, e;

        private Line(Point s, Point e) {
            this.s = s;
            this.e = e;
        }
    }
}
