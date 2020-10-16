package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 그리디 문제
 *  기준 -> 빨리 끝나는 사람부터 줄을 세운다.
 *  빨리 끝나는 사람부터 줄을 세울시, 뒤의 사람의 기다리는 시간은 줄어든다.
 *
 *  -> S[i] 의 값은 S[i..N] 까지 값에 누적되므로, S[i] 의 값이 최소여야 최소값을 구할 수 있다.
 *  -> S[i] + (S[i]+S[i+1]) + (S[i]+S[i+1]+S[i+2]) ...
 */
@Used(algorithm = Algorithm.GREEDY)
public class P11399 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(A);
        int wait = 0;
        for (int i=0; i<N-1; i++) {
            A[i+1] += A[i];
            wait += A[i];
        }
        System.out.println(wait + A[N-1]);
    }
}
