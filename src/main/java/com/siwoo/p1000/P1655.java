package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 힙을 이용한 연속된 데이터에서 중간값 구하기.
 *  1. 현재 n 에 대해 자료를 left(maxHeap), right(minHeap) 으로 나눈다. (홀수 일 경우 왼쪽을 n/2+1)
 *      -> 이를 위반하면 S 상태라고 하자.
 *  2. left 의 top (혹은 조건에 따라 right.top) 은 항상 중간 값이다.
 *  
 *  자료 추가하기
 *      1. x 가 left.top 보다 작다면 left 에 추가.
 *      2. 위 경우가 아니라면 right 에 추가.
 *      3. S 상태인 경우 큰 쪽에서 작은 쪽으로 top 을 옮겨준다.
 */
@Used(algorithm = Algorithm.HEAP)
public class P1655 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        PriorityQueue<Integer> left = new PriorityQueue<>((x, y) -> Integer.compare(y, x)),
                right = new PriorityQueue<>();
        for (int i=0; i<N; i++) {
            int x = Integer.parseInt(reader.readLine());
            if (left.isEmpty() || left.peek() > x)
                left.add(x);
            else 
                right.add(x);
            if (left.size() != right.size() 
                    && left.size() != right.size() + 1) {
                PriorityQueue<Integer> from = left.size() > right.size()? left: right,
                        to = left == from? right: left;
                to.add(from.poll());
            }
            System.out.println(left.peek());
        }
    }
}
