package com.siwoo.p11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Random;
import java.util.StringTokenizer;

public class P11650 {
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
        shuffle(points);
        sort(0, N, points);
        StringBuilder sb = new StringBuilder();
        for (Point p: points)
            sb.append(p).append("\n");
        System.out.println(sb.toString());
    }

    private static <A extends Comparable<A>>
        void sort(int left, int right, A[] E) {
        if (right - left <= 1) return;
        int i = left, j = right;
        A a = E[left];
        while (i < j) {
            while (i < j && E[--j].compareTo(a) > 0) ;
            while (i < j && E[++i].compareTo(a) < 0) ;
            if (i < j)
                swap(i, j, E);
        }
        swap(j, left, E);
        int pivot = j;
        sort(left, pivot, E);
        sort(pivot+1, right, E);
    }

    private static <A> void shuffle(A[] a) {
        Random random = new Random();
        for (int i=a.length-1; i>=0; i--){
            int x = random.nextInt(i + 1);
            swap(x, i, a);
        }
    }

    private static <A> void swap(int i, int j, A[] a) {
        A t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static class Point implements Comparable<Point> {
        private static Comparator<Point> C = Comparator.comparing(Point::getX).thenComparing(Point::getY);
        private final int x, y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public int compareTo(Point o) {
            return C.compare(this, o);
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }
}
