package com.siwoo.util;

import java.util.ArrayList;
import java.util.List;

/**
 * CCW (Counter Clockwise)
 * 
 * given p1, p2, p3 find out the direction
 *      1 = 반시계방향
 *      -1 = 시계방향
 *      0 = 직선
 * 
 * 외적 = 벡터곱
 *  벡터곱의 부호는 p1, p2, p3 의 방향을 의미.
 * 
 * 외적 = 벡터곱 (outer product)
 *  p1(x1, y1), p2(x2, y2), p3(x3, y3)
 *  x1*y2 + x2*y3 + x3*y1 - y1*x2 - y2*x3 - y3 *x1
 *  
 *  x1  x2  x3  x1      ( / = 덧셈, \ = 뺄셈)
 *    x   x   x
 *  y1  y2  y3  y1
 *  
 * CCW 로 다각형의 넓이 구하기.
 *  p1, p2, p3 을 이루는 삼각형의 넓이 = ccw(p1, p2, p3) / 2;
 *  
 *  다각형을 이루는 점이 p1, p2, p3, p4, p5 일 때 
 *      ccw(p1, p2, p3)/2 + ccw(p2, p3, p5)/2 + ccw(p2, p4, p5)/2
 *      = ccw(p1, p2, p3, p4, p5)
 * 
 * 
 * CCW 로 선분의 교차하는지 알아내기. 
 *  Intersection of two line segments.
 *  
 *  P1P2 P2P3 을 선분이라 할때
 *      ccw(p1,p2,p3) * ccw(p1,p2,p4) 을 a,
 *      ccw(p3,p4,p1) * ccw(p3,p4,p2) 을 b 으로 가정한다면
 *      
 *      a <= 0 && b <= 0
 *      
 *      이때 a == 0 && b == 0 이라면 반례가 발생
 *          p1 p2 p3 p4 = no
 *          p1 p3 p2 p4 = yes
 *          p3 p1 p4 p2 = yes
 *          p3 p1 p2 p4 = yes
 *      
 *      이 경우 p1 <= p4 && p2 <= p3 을 이라면 교차가 없다.
 * 
 * 선분 S(s,e) 위에 좌표 x 의 여부
 *      ccw(s,e,x) == 0 && s <= x <= e
 *      
 * 점 x 와 다각형 p 가 주어질 때, x 가 p 의 내부, 외부인지 알아내기.
 *  
 *  중심점 w (p 의 꼭지점과 겹치지 않는) 을 정하고 x 와 w 와의 선분 s 을 그린 후
 *      intersection(s, p의 변) 의 합이 짝수라면 외부, 홀수라면 내부
 *
 */
public class CCW {
    
    public static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
        int direction = x1 * y2 + x2 * y3 + x3 * y1;
        direction -= y1 * x2 + y2 * x3 + y3 * x1;
        if (direction == 0) return direction;   //straight
        if (direction > 0) return 1;    //counter clockwise
        else return -1; //clockwise
    }

    public static int ccw(Point p1, Point p2, Point p3) {
        long direction = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
        direction -= p1.y * p2.x + p2.y * p3.x + p3.y * p1.x;
        if (direction == 0) return 0;   //straight
        if (direction > 0) return 1;    //counter clockwise
        else return -1; //clockwise
    }
    
    public static boolean intersection(Line line1, Line line2) {
        int x1 = ccw(line1.s, line1.e, line2.s) * ccw(line1.s, line1.e, line2.e);
        int x2 = ccw(line2.s, line2.e, line1.s) * ccw(line2.s, line2.e, line1.e);
        if (x1 == 0 && x2 == 0) {
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
        return x1 <= 0 && x2 <= 0;
    }
    
    public static boolean inScope(Polygon polygon, Point v) {
        Point w = new Point(1, Integer.MAX_VALUE);
        for (Line line: polygon.lines) {
            Point s = line.s, e = line.e;
            if (s.compareTo(e) > 0) {
                Point t = s;
                s = e;
                e = t;
            }
            // s - v - e
            if (v.compareTo(s) >= 0 && e.compareTo(v) >= 0)
                return true;
        }
        Line s = new Line(v, w);
        int cnt = 0;
        for (Line line: polygon.lines)
            cnt += intersection(line, s)? 1: 0;
        return cnt % 2 == 1;
    }
    
    private static class Polygon {
        private List<Line> lines = new ArrayList<>();
        
        public Polygon(Point... points) {
            for (int i=0; i<points.length-1; i++)
                lines.add(new Line(points[i], points[i+1]));
            lines.add(new Line(points[points.length-1], points[0]));
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
        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point p) {
            int c = Integer.compare(x, p.x);
            return c == 0? 0: Integer.compare(y, p.y);
        }
    }

    public static void main(String[] args) {
        Line line1 = new Line(new Point(0, 0), new Point(6, 6));
        Line line2 = new Line(new Point(5, 0), new Point(0, 5));
        System.out.println(intersection(line1, line2));
        line1 = new Line(new Point(1, 1), new Point(5, 5));
        line2 = new Line(new Point(1, 5), new Point(5, 1));
        System.out.println(intersection(line1, line2));
        line1 = new Line(new Point(1, 1), new Point(5, 5));
        line2 = new Line(new Point(6, 10), new Point(10, 6));
        System.out.println(intersection(line1, line2));
        line2 = new Line(new Point(5, 5), new Point(10, 10));
        System.out.println(intersection(line1, line2));
        line1 = new Line(new Point(1, 1), new Point(5, 5));
        line2 = new Line(new Point(6, 6), new Point(1, 5));
        System.out.println(intersection(line1, line2));
        
        Polygon polygon = new Polygon(new Point(1, 0), new Point(5, 0), new Point(3, 3));
        System.out.println(inScope(polygon, new Point(2, 2)));
        System.out.println(inScope(polygon, new Point(3, 2)));
        System.out.println(inScope(polygon, new Point(4, 3)));
    }
}
























