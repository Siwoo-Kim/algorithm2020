package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

@Used(algorithm = Algorithm.LINE_SWEEPING)
public class P2261_LINE_SWEEPING {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Point[] points;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        points = new Point[N];
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            points[i] = new Point(Integer.parseInt(token.nextToken()), 
                    Integer.parseInt(token.nextToken()));
        }
        int x = lineSweeping(points);
        System.out.println(x);
    }

    private static int lineSweeping(Point[] points) {
        Arrays.sort(points, Point.CX);
        TreeSet<Point> bst = new TreeSet<>(Point.CY);
        bst.add(points[0]);
        bst.add(points[1]);
        int k = 0;
        int d = distance(points[0], points[1]);
        for (int i=2; i<points.length; i++) {
            Point v = points[i];
            while (k < i) {
                Point w = points[k];
                int x = v.x - w.x;
                if (x*x > d) {
                    bst.remove(w);
                    k++;
                } else {
                    break;  //모든 후보 x 의 거리는 d 이하.
                }
            }
            int dy = (int) (Math.sqrt(d) + 1);
            Point lowerBound = new Point(Integer.MIN_VALUE, v.y - dy);
            Point upperBound = new Point(Integer.MAX_VALUE, v.y + dy);
            Point w = bst.ceiling(lowerBound);
            while (w != null && Point.CY.compare(w, upperBound) <= 0) {
                int dt = distance(v, w);
                if (d > dt)
                    d = dt;
                w = bst.higher(w);
            }
            bst.add(v);
        }
        return d;
    }

    private static int distance(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    private static class Point {
        private static Comparator<Point> CX = Comparator.comparingInt(p -> p.x);
        private static Comparator<Point> CY = Comparator.comparingInt((Point p) -> p.y).thenComparingInt(p -> p.x);
        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
