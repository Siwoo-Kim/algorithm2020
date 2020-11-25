package com.siwoo.util;

import java.util.*;

/**
 *  Convex null (블록 껍질) 찾기 - 그라함 스캔
 *      점의 집합 S 가 있을 때, 모든 S 을 감싸는 블록 다각형을 찾기.
 *    
 *    블록 다각형의 특징. 
 *         블록 다각형의 점들은 반드시 한 방향으로만 순회해야 한다. (시 방향 or 반시계 방향)
 *          
 *      1. 맨 아래점 v 을 기준으로 각도순 정렬한다. 
 *          정렬 법.
 *          v 을 기준으로 w1, w2 을 각도 비교할때
 *            ccw(v, w1, w2) 수행시
 *            1 이라면   (반시계 방향)
 *                w1 < w2
 *            0 이라면   (일직선이므로 거리순 비교)
 *                w1 == w2
 *            -1 이라면  (시계 방향)
 *                w1 > w2
 *     
 *        2. 순서대로 스택에 각 점 v 을 넣어준다.
 *            
 *            1. 다음에 넣을 점 v 에 대해서 ccw(top2, top1, v) != 1 (시계 방) 이라면 v 을 추가.
 *            2. ccw(top2, top1, v) 가 -1 이라면 stack pop 을 하고 1을 수행.
 */
public class ConvexHull {
   
    public static int ccw(Point p1, Point p2, Point p3) {
        long d = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
        d -= p1.y * p2.x + p2.y * p3.x + p3.y * p1.x;
        if (d == 0) return 0;
        return d > 0 ? 1: -1;
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
            if (c != 0) return c;
            return Integer.compare(y, p.y);
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static void main(String[] args) {
        Point base = new Point(-3, -6);
        List<Point> points = Arrays.asList(
           base,
            new Point(-6, 3),
            new Point(-1, -1),
            new Point(3, -3),
            new Point(6, -6),
            new Point(6, 9),
            new Point(0, 9),
            new Point(3, 4),
            new Point(12, 0));
        System.out.println(points);
        
        Collection<Point> shell = convexHull(points);
        System.out.println(shell);
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
}
