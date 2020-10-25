package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * dnc 가 왼쪽, 오른쪽으로 구분 된 부분에서 가장 적은 거리를 리턴한다고 가정한다면,
 *
 * 1. 왼쪽과 오른쪽으로 점을 나눈 후 각각에 대해 dnc 을 호출. (왼쪽의 최소 거리, 오른쪽의 최소 거리)
 * 2. 이때 min(dnc(left), dnc(right) 은 지금까지 알고있는 최소 거리이다. 이 거리를 d 라고 하자.
 * 3. 여기서 유효한 점의 좌표들의 범위는 mid-d ~ mid+d 이다. (이 외의 모든 쌍의 점들의 거리는 d 를 초과한다)
 * 4. mid-d ~ mid+d 점들에 x (y 좌표로 정렬된) 에 대해서 조사해야할 y 의 범위또한 [y-d, y+d] 이다. (이 범위를 벗어났다면 d 보다 클 수밖에 없다.)
 *
 */
@Used(algorithm = Algorithm.DnC)
public class P2261 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static Point[] points;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        points = new Point[N];
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            points[i] = new Point(Integer.parseInt(token.nextToken()),
                    Integer.parseInt(token.nextToken()));
        }
        Arrays.sort(points, Point.CX);
        int d = dnc(0, points.length, points);
        System.out.println(d);
    }

    private static int dnc(int left, int right, Point[] points) {
        if (right - left <= 3) {
            int d = Integer.MAX_VALUE;
            for (int i=left; i<right-1; i++)
                for (int j=i+1; j<right; j++)
                     d = Math.min(d, Point.distance(points[i], points[j]));
            return d;
        };
        int mid = (right + left) / 2;
        int d = Math.min(dnc(left, mid, points), dnc(mid, right, points));
        List<Point> candidates = new ArrayList<>();
        //mid-d ~ mid+d
        for (int i=left; i<right; i++) {
           int dv = Math.abs(points[i].x - points[mid].x);  //no require abs
           if (dv*dv < d)
               candidates.add(points[i]);
        }
        Collections.sort(candidates, Point.CY); //sort by y axis
        for (int i=0; i<candidates.size()-1; i++)
            for (int j=i+1; j<candidates.size(); j++) {
                int y = Math.abs(candidates.get(j).y - candidates.get(i).y);    //no require abs
                if (y*y < d)
                    d = Math.min(d, Point.distance(candidates.get(i), candidates.get(j)));
                else
                    break;  //over d
            }
        return d;
    }

    private static class Point {
        private static Comparator<Point> CX = Comparator.comparing(p -> p.x);
        private static Comparator<Point> CY = Comparator.comparing(p -> p.y);
        private final int x, y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static int distance(Point p1, Point p2) {
            return (int) (Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
        }
    }
}
