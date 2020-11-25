package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

@Used(algorithm = Algorithm.CCW)
public class P11758 {
    private static Scanner scanner = new Scanner(System.in);
    private static Point p1, p2, p3;

    public static void main(String[] args) {
        p1 = new Point(scanner.nextInt(), scanner.nextInt());
        p2 = new Point(scanner.nextInt(), scanner.nextInt());
        p3 = new Point(scanner.nextInt(), scanner.nextInt());
        System.out.println(ccw(p1, p2, p3));
    }

    private static int ccw(Point p1, Point p2, Point p3) {
        int direction = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
        direction -= p1.y * p2.x + p2.y * p3.x + p3.y * p1.x;
        if (direction == 0) return direction;
        return direction > 0? 1: -1;
    }

    private static class Point {
        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
