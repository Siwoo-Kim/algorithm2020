package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 그리디 기준 - 가장 큰 수끼리 묶어준다. (양수, 음수 구분하여)
 *  예외 -> 0, 1 은 더하는게 최우선.
 *  음수의 원소 i 가 짝이 없을 때, 0 이 있었다면 두 수를 묶어야 한다.
 */
@Used(algorithm = Algorithm.GREEDY)
public class P1744 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static PriorityQueue<Integer> pos =
            new PriorityQueue<>((a, b) -> Integer.compare(b, a)),   // 왜 (a, b) -> a < b? b: a 안됨?
            neg = new PriorityQueue<>();
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        for (int i=0; i<N; i++) {
            int d = Integer.parseInt(reader.readLine());
            if (d < 0) neg.add(d);
            else pos.add(d);
        }

        int sum = 0;
        boolean zero = false;
        while (pos.size() > 1) {
            int v1 = pos.poll();
            int v2 = pos.poll();    //zero & one
            if (v2 == 0 || v2 == 1) {
                sum += v1 + v2;
                zero = v2 == 0;
            } else
                sum += v1 * v2;
        }
        sum += pos.isEmpty()? 0: pos.poll();
        while (neg.size() > 1)
            sum += neg.poll() * neg.poll();
        sum += neg.isEmpty() || zero? 0: neg.poll();
        System.out.println(sum);
    }
}
