package com.siwoo;

import java.util.*;

public class KaKao {
    private static int ROWS = 4, COLS = 3;
    public static char[][] BOARD = {
            {'1', '2', '3',},
            {'4', '5', '6',},
            {'7', '8', '9',},
            {'*', '0', '#',}
    };

    private static class Pair {
        int[] a;
        String hand;

        public Pair(int[] a, String hand) {
            this.a = a;
            this.hand = hand;
        }
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        for (Pair p: Arrays.asList(
                new Pair(new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right"),
                new Pair(new int[]{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}, "left"),
                        new Pair(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, "right"))) {
            String answer = solution.solution(p.a, p.hand);
            System.out.println(answer);
        }
    }

    private static class Solution {
        private static int ROWS = 4, COLS = 3;
        public static char[][] BOARD = {
                {'1', '2', '3',},
                {'4', '5', '6',},
                {'7', '8', '9',},
                {'*', '0', '#',}
        };

        Point left = new Point(3, 0, false), right = new Point(3, 2, true);
        boolean rightHand;
        public String solution(int[] numbers, String hand) {
            StringBuilder answer = new StringBuilder();
            rightHand = "right".equals(hand);
            for (int i = 0; i < numbers.length; i++) {
                int index = numbers[i] - 1;
                int row = index / COLS;
                int col = index % COLS;
                if (col == 1 || numbers[i] == 0) {
                    answer.append(bfs(numbers[i]));
                } else if (col == 0) {
                    left = new Point(row, col, false);
                    answer.append("L");
                } else if (col == 2) {
                    right = new Point(row, col, true);
                    answer.append("R");
                }
            }
            return answer.toString();
        }

        private String bfs(int v) {
            Queue<Point> q = new LinkedList<>();
            Map<Point, Integer> distance = new HashMap<>();
            if (rightHand) {
                q.add(right);
                q.add(left);
            } else {
                q.add(left);
                q.add(right);
            }
            distance.put(left, 0);
            distance.put(right, 0);
            while (!q.isEmpty()) {
                Point p = q.poll();
                if ((BOARD[p.x][p.y] - '0') == v) {
                    if (p.right)
                        right = new Point(p.x, p.y, true);
                    else
                        left = new Point(p.x, p.y, false);
                    return p.right ? "R" : "L";
                }
                for (Point m : Point.POINTS) {
                    Point w = new Point(p.x + m.x, p.y + m.y, p.right);
                    if (w.isValid() && !distance.containsKey(w)) {
                        distance.put(w, distance.get(p) + 1);
                        q.add(w);
                    }
                }
            }
            throw new AssertionError();
        }
    }

    private static class Point {
        private static Point[] POINTS = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1)
        };
        private boolean right;
        private final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, boolean right) {
            this.x = x;
            this.y = y;
            this.right = right;
        }

        public boolean isValid() {
            return x >= 0 && x < ROWS && y >= 0 && y < COLS;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
