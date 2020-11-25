package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.CCW)
public class P1688 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static Polygon polygon;
    private static Point v = new Point( 1, Integer.MAX_VALUE/2);
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        Point[] points = new Point[N];
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            points[i] = new Point(Integer.parseInt(token.nextToken()), 
                    Integer.parseInt(token.nextToken()));
        }
        polygon = new Polygon(points);
        for (int i = 0; i<3; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            Point p = new Point(Integer.parseInt(token.nextToken()),
                    Integer.parseInt(token.nextToken()));
            System.out.println(inScope(polygon, p)? 1: 0);
        }
        
    }

    private static boolean inScope(Polygon polygon, Point p) {
        for (Line line: polygon.lines) {
            if (ccw(line.s, line.e, p) == 0) {
                Point s = line.s, e = line.e;
                if (s.compareTo(e) > 0) {
                    Point t = s;
                    s = e;
                    e = t;
                }
                if (p.compareTo(s) >= 0 && e.compareTo(p) >= 0)
                    return true;
            }
        }
        int cnt = 0;
        Line s = new Line(p, v);
        for (Line line: polygon.lines)
            cnt += intersect(line, s)? 1: 0;
        return cnt % 2 == 1;
    }

    private static boolean intersect(Line line1, Line line2) {
        int c1 = ccw(line1.s, line1.e, line2.s) * ccw(line1.s, line1.e, line2.e);
        int c2 = ccw(line2.s, line2.e, line1.s) * ccw(line2.s, line2.e, line1.e);
        if (c1 == 0 && c2 == 0) {   //이런 경우는 없을듯
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
            return p3.compareTo(p2) <= 0 && p4.compareTo(p1) >= 0;
        }
        return c1 <= 0 && c2 <= 0;
    }

    private static int ccw(Point p1, Point p2, Point p3) {
        long direction = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
        direction -= p1.y * p2.x + p2.y * p3.x + p3.y * p1.x;
        if (direction == 0) return 0;   //straight
        if (direction > 0) return 1;    //counter clockwise
        else return -1; //clockwise
    }

    private static class Polygon {
        final Line[] lines;

        public Polygon(Point... points) {
            lines = new Line[points.length];
            for (int i=0; i<lines.length-1; i++)
                lines[i] = new Line(points[i], points[i+1]);
            lines[lines.length-1] = new Line(points[lines.length-1], points[0]);
        }
    }
    
    private static class Line {
        final Point s, e;

        public Line(Point s, Point e) {
            this.s = s;
            this.e = e;
        }

    }
    
    private static class Point implements Comparable<Point> {
        final long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point p) {
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
}
