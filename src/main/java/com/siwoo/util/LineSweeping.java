package com.siwoo.util;

import java.util.*;

/**
 * 라인 스위핑 (Line Sweeping)
 * 
 *  어떠한 공간에 N 개의 요소가 있고 "정렬"되어 있을 때,
 *      "한쪽 반향으로 훑으면서" "순서대로 요소"를 탐색 (bst 을 활용) 하며 정답을 구하는 알고리즘.
 *  
 *  컴포넌트.
 *      1. 후보 candidate (bst)
 *      2. 지금까지 정답 d (i-1 까지의)
 *      3. 후보 중 가장 첫번째 k
 *      4. 스위핑을 진행할 방향 (혹은 정렬 기준.)
 *      
 *  공간을 좌표평면 N 에 좌표 집합 S 가 있고, 가장 가까운 두 점의 거리를 찾는다고 가정하자.
 *  
 *  1. x 좌표가 증가하는 순으로 정렬.
 *  
 *  2. 이때 후보 탐색을 수행.
 *      후보 탐색. 
 *          
 *          가정1) 1 번부터 i-1 의 가장 가까운 거리를 d 라고 하자.
 *          i 번의 점 m 이 추가되었을 때, 후보가 될 수 있는 점은
 *          m 과의 거리가 d 보다 작은 점이 되어야 한다.
 *          
 *          후보 탐색 1 차 검색.
 *              x 좌표의 차이가 d 이하인 점만을 선택.
 *          
 *          후보 탐색 2  검색.
 *              y 좌표의 차이가 d 이하인 점만을 선택.
 *              
 *          위의 후보 탐색에서 결정된 요소 집합 S 의 요소와 m 의 모든 쌍의 거리를 계
 *          선택된 집합 S 의 갯수는 반드시 6 개 이하이다. (6 개 이상이라면 가정1 이 위반되므로)
 *      
 *      후보 관리. (candidate)
 *          M 번째 점이 추가되었을때,
 *          후보가 되는 점은 k 번째 점부터 M-1 번째 점까지와 같이 번호가 연속된 형태.
 *          후보가 되는 점을 y 좌표로 정렬하여 관리하면 "후보 탐색 이차 검색" 에서 
 *          d 이하인 점 s 을 logN 에 찾을 수 있다. 
 */
public class LineSweeping {
    
    private static class Point {
        private static final Comparator<Point> XC = Comparator.comparingInt(p -> p.x);
        private static final Comparator<Point> YC = Comparator.comparingInt((Point p) -> p.y)
                .thenComparing(p -> p.x);
        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public static int distance(Point v, Point w) {
            return (v.x - w.x) * (v.x - w.x) + (v.y - w.y) * (v.y - w.y);
        }
    }
    
    public static int lineSweeping(List<Point> points) {
        points.sort(Point.XC);
        TreeSet<Point> candidate = new TreeSet<>(Point.YC);
        candidate.add(points.get(0));
        candidate.add(points.get(1));
        int d = Point.distance(points.get(0), points.get(1));
        int k = 0;
        for (int i=2, N=points.size(); i<N; i++) {
            Point v = points.get(i);
            while (k < i) { //x 의 차이가 d 이하인 후보만 선택
                Point w = points.get(k);
                int x = v.x - w.x;
                if (x*x > d) { // 후보들을 걸러준다.
                    candidate.remove(w);
                    k++;
                } else {
                    break;  //이후의 모든 후보 x 의 거리는 d 이하.
                            // =>x로 기준 정렬이므
                }
            }
            int dy = (int) (java.lang.Math.sqrt(d) + 1);    // y 을 기준으로 후보 검사
            Point lowerBound = new Point(Integer.MIN_VALUE, v.y - dy);  //아래축
            Point upperBound = new Point(Integer.MAX_VALUE, v.y + dy);  //위축
            Point w = candidate.ceiling(lowerBound);
            while (w != null && Point.YC.compare(w, upperBound) != 0) {
                int dt = Point.distance(v, w);
                if (d > dt)
                    d = dt;
                w = candidate.higher(w);
            }
            candidate.add(v);
        }
        return d;
    }

    public static void main(String[] args) {
        List<Point> ps = Arrays.asList(
                new Point(-5, 0),
                new Point(-3, 4),
                new Point(2, 1),
                new Point(3, 4),
                new Point(-1, 1),
                new Point(8, 8),
                new Point(1, 7));
        System.out.println(lineSweeping(ps));
    }
}
