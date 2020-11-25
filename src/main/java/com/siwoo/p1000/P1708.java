package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.CCW)
public class P1708 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    
    private static class Point implements Comparable<Point> {
        final long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point p) {
            int c = Long.compare(x, p.x);
            if (c != 0) return c;
            return Long.compare(y, p.y);
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        List<Point> points = new ArrayList<>();
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            points.add(new Point(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken())));
        }
        Collection<Point> shell = convexHull(points);
        System.out.println(shell.size());
    }

    public static long dist(Point p1, Point p2) {
        long d1 = p1.x - p2.x;
        long d2 = p1.y - p2.y;
        return d1*d1 + d2*d2;
    }
    
    private static Collection<Point> convexHull(List<Point> points) {
        final Comparator<Point> lowerC = Comparator.comparingLong((Point p) -> p.y)
                .thenComparingLong(p -> p.x);
        final Point base = points.stream().min(lowerC).get();
        final Comparator<Point> degreeC = (p1, p2) -> {
            if (p1 == base) return -1;
            if (p2 == base) return 1;
            int c = ccw(base, p1, p2);
            if (c == 0)  //need compare (base - p1) with (base - p2)
                return Long.compare(dist(base, p1), dist(base, p2));
            return -c;
        };
        points.sort(degreeC);
        Stack<Point> stack = new Stack<>();
        for (int i=0; i<2; i++)
            stack.push(points.get(i));
        for (int i=2; i<points.size(); i++) {
            Point p = points.get(i);
            while (stack.size() >= 2) {
                Point t1 = stack.pop(),
                        t2 = stack.pop();
                if (ccw(t2, t1, p) <= 0) {
                    stack.push(t2);
                } else {
                    stack.push(t2);
                    stack.push(t1);
                    break;
                }
            }
            stack.push(p);
        }
        return stack;
    }

    private static int ccw(Point p1, Point p2, Point p3) {
        long d = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
        d -= p1.y * p2.x + p2.y * p3.x + p3.y * p1.x;
        if (d == 0) return 0;
        return d > 0? 1: -1;
    }
}
