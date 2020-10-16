package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 그리디 문제.
 *  기준 -> 빨리 끝나는 강의 순으로 선택하면, 주어진 시간 s - e 에서 배정가능한 최대 수의 회의를 찾을 수 있다.
 *  빨리 끝나는 강의 순의 선택의 의미는 다른 회의 시간을 더 배정할 수 있는 pool 이 커진다는 것.
 */
@Used(algorithm = Algorithm.GREEDY)
public class P1931 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static Range[] ranges;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        ranges = new Range[N];
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            ranges[i] = new Range(token.nextToken(), token.nextToken());
        }
        Arrays.sort(ranges, Range.comparator);

        int cnt = 1, last = ranges[0].end;
        for (int i=1; i<N; i++) {
            if (last <= ranges[i].start) {
                cnt++;
                last = ranges[i].end;
            }
        }
        System.out.println(cnt);
    }

    private static class Range {
        private static Comparator<Range> comparator = Comparator.comparingInt(Range::getEnd).thenComparing(Range::getStart);

        private final int start, end;

        public Range(String start, String end) {
            this.start = Integer.parseInt(start);
            this.end = Integer.parseInt(end);
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }
}
