package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 나누기 3 을 한다면, 3 의 배수들은 줄어들어야만 한다. => X 을 이루는 3 의 지수가 높을수록 순서가 가장 빠르다.
 * 곱하기 2 을 한다면, 2 의 배수들을 늘어나야만 한다. => X 을 이루는 2 의 지수가 높을수록 순서가 늦다.
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P16936 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static E[] B;

    private static class E implements Comparable<E> {
        private final Long value;

        public E(long value) {
            this.value = value;
        }

        @Override
        public int compareTo(E o) {
            long three = counts(3);
            long oThree = o.counts(3);
            if (three == oThree)
                return Long.compare(counts(2), o.counts(2));
            else
                return Long.compare(oThree, three);
        }

        private long counts(int x) {
            long cnt = 0;
            Long value = this.value;
            while (value % x == 0) {
                cnt++;
                value /= x;
            }
            return cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        B = new E[N];
        StringTokenizer token = new StringTokenizer(reader.readLine());
        for (int i=0; i<N; i++)
            B[i] = new E(Long.parseLong(token.nextToken()));
        Arrays.sort(B);
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++)
            sb.append(B[i].value).append(" ");
        System.out.println(sb);
    }
}
