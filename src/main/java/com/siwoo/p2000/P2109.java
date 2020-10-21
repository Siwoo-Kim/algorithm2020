package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 그리드의 기준은 가장 뒷 날짜를 기준으로 가장 큰 가치있는 강의를 선택한다.
 *
 * 특정 날짜 d 은 d 이후로는 강의하지 못하니 뒤에서 부터 순회하며 가장 큰 pay 을 선택.
 *  => 가장 마지막 날짜 d 이후로는 어떤 강의도 할 수 없다.
 *
 */
@Used(algorithm = Algorithm.GREEDY)
public class P2109 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static Map<Integer, List<Pair>> group = new HashMap<>();

    private static class Pair implements Comparable<Pair> {
        private int pay, day;

        public Pair(int pay, int day) {
            this.pay = pay;
            this.day = day;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(o.pay, pay);
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        int max = 0;
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            Pair pair = new Pair(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));
            group.computeIfAbsent(pair.day, k -> new ArrayList<>());
            group.get(pair.day).add(pair);
            max = Math.max(pair.day, max);
        }
        int earn = 0;
        PriorityQueue<Pair> q = new PriorityQueue<>();
        for (int d=max; d>=1; d--) {
            if (group.containsKey(d))
                q.addAll(group.get(d));
            if (!q.isEmpty())
                earn += q.poll().pay;
        }
        System.out.println(earn);
    }
}
