package com.siwoo.p9000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 두 정점에서 시작하여 목표정점을 향한 최소거리. 
 *  (두 정점이 한 곳에서 만난 경우 가중치는 한번만 계산한다.)
 *  (위 조건이 없으면 각각의 최소 거리를 합하면 되므로 단순 bfs 문제)
 *  
 *  bfs -> 하나의 정점에서 목표정점 (가중치가 0-1) 을 향한 최소거리 알고리즘.
 *  
 *  까다로운 점 
 *      -> 이미 p1 & p2 이 모두 방문한 정점 x 에 대한 가중치 계산이 중복됨.
 *      -> p1 & p2 이 만난 경우와 만나지 않았을 때의 상태를 bfs 로 확인하기 어렵다.
 *  
 *  해결 방법
 *      1. 3 자 정점으로 목표 정점을 도입한다. 이를 p3 이라 하자.
 *      2. p1, p2, p3 에 대해서 모두 bfs 로 가중치를 계산한다.
 *      3. p1 & p2 & p3 이 만난 모든 정점 v 에 대해서 값을 합산 한 후 -2 을 해준다. (해당 정점으로 이동 가중치가 3번 중복 되었으므로)
 *      
 *  가중치가 0, 1 인 경우의 그래프에서 bfs 알고리즘시,
 *      1. queue 대신 deque 을 사용.
 *      2. 가중치가 0 인 정점 v 은 큐의 가장 앞에, 1 인 정점은 큐의 가장 마지막에 넣는다. 
 *          (이렇게 하지 않으면 거리 계산을 제대로 하지 못함.)
 *
 *  이 문제의 특징.
 *      1. p1, p2, p3 가 만나는 정점 v 가 문이라면 중복 문은 반드시 3 개 존재하므로 -2 을 한다.
 *      2. p1, p2, p3 가 만나는 정점이 문이 아니라면 모두 각자의 문만을 열었으므로 그대로 합산한다.
 *          => 이 값은, 중복을 배제하지 않았으로 정답이 아닐 가능성이 있다. (p1 와 p2 은 만나서 이동했는데 이후에 p3 을 만난 경우)
 *          => 이 조건을 배제하여도 왜 문제를 풀 수 있는지는 모르겠음..
 *      
 */
@Used(algorithm = Algorithm.BFS)
public class P9376 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(reader.readLine());
        while (t != 0) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(token.nextToken()) + 2;
            M = Integer.parseInt(token.nextToken()) + 2;
            char[][] board = new char[N][M];
            for (int i=0; i<N; i++) {
                String line = (i == 0 || i == N-1)? 
                        null: reader.readLine();
                int k = 0;
                for (int j=0; j<M; j++) {
                    board[i][j] = (line == null || j == 0 || j == M-1)? 
                            '.': line.charAt(k++);
                }
            }
            int answer = solve(board);
            System.out.println(answer);
            t--;
        }
    }

    private static int solve(char[][] board) {
        Point p1 = null, p2 = null, p3 = new Point(0, 0);
        for (int i=0; i<N; i++)
            for (int j=0; j<M; j++)
                if (board[i][j] == '$') {
                    if (p1 == null) 
                        p1 = new Point(i, j);
                    else
                        p2 = new Point(i, j);
                }
        int[][] check1 = bfs(p1, board);
        int[][] check2 = bfs(p2, board);
        int[][] check3 = bfs(p3, board);
        
        int answer = Integer.MAX_VALUE;
        for (int i=0; i<N; i++)
            for (int j=0; j<M; j++) {
                if (board[i][j] == '*') continue;
                int d = check1[i][j] + check2[i][j] + check3[i][j];
                if (board[i][j] == '#') d -= 2;
                answer = Math.min(answer, d);
            }
        return answer;
    }

    private static int[][] bfs(Point p, char[][] board) {
        Map<Point, Integer> dist = new HashMap<>();
        Deque<Point> q = new LinkedList<>();
        q.add(p);
        dist.put(p, 0);
        int[][] check = new int[N][M];
        while (!q.isEmpty()) {
            Point v = q.poll();
            for (Point m: Point.MOVE) {
                Point w = new Point(v.x + m.x, v.y + m.y);
                if (dist.containsKey(w)) continue;
                if (!w.valid()) continue;
                if (board[w.x][w.y] == '*') continue;
                int d = dist.get(v);
                if (board[w.x][w.y] == '#') {
                    d++;
                    q.addLast(w);
                } else {
                    q.addFirst(w);
                }
                check[w.x][w.y] = d;
                dist.put(w, d);
            }
        }
        return check;
    }

    private static class Point {
        private static Point[] MOVE = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1)
        };
        private final int x, y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return  x >= 0 && x < N && y >= 0 && y < M;
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
