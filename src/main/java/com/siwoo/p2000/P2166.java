package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.CCW)
public class P2166 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static Point[] points;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        points = new Point[N];
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            points[i] = new Point(Long.parseLong(token.nextToken()), Long.parseLong(token.nextToken()));
        }
        long area = ccw(points);
        if (area % 2 == 0)
            System.out.printf("%d.0", area / 2);
        else
            System.out.printf("%d.5", area / 2);
    }

    private static long ccw(Point[] points) {
        long r = 0;
        for (int i=0; i<points.length-1; i++)
            r += points[i].x * points[i+1].y;
        r += points[points.length-1].x * points[0].y;
        for (int i=0; i<points.length-1; i++)
            r -= points[i].y * points[i+1].x;
        r -= points[points.length-1].y * points[0].x;
        return Math.abs(r);
    }

    private static class Point {
        final long x, y;

        private Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}
