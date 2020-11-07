package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.TWO_POINTER)
public class P2143 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static long N, M, T;
    private static long[] A, B;
    
    public static void main(String[] args) throws IOException {
        T = Long.parseLong(reader.readLine());
        N = Long.parseLong(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();
        M = Long.parseLong(reader.readLine());
        B = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();
        List<Long> left = build(A);
        List<Long> right = build(B);
        Collections.sort(left);
        Collections.sort(right);
        Collections.reverse(right);
        int l = 0, r = 0;
        long cnt = 0;
        while (l < left.size() && r < right.size()) {
            long candidate = left.get(l) + right.get(r);
            if (candidate == T) {
                long leftCnt = 1, rightCnt = 1;
                l++;
                r++;
                while (l < left.size() && left.get(l).equals(left.get(l - 1))) {
                    l++;
                    leftCnt++;
                }
                while (r < right.size() && right.get(r).equals(right.get(r - 1))) {
                    r++;
                    rightCnt++;
                }
                cnt += leftCnt * rightCnt;
            } else if (candidate < T) {
                l++;
            } else {
                r++;
            }
        }
        System.out.println(cnt);
    }

    private static List<Long> build(long[] A) {
        List<Long> longs = new ArrayList<>();
        for (int i=0; i<A.length; i++) {
            long sum = 0;
            for (int j=i; j<A.length; j++) {
                sum += A[j];
                longs.add(sum);
            }
        }
        return longs;
    }
}
