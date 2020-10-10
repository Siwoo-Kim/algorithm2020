package com.siwoo.p14000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

/**
 * O(N-1)!*N = 36,288,000
 * => 순열 연산 중 중복 요소가 있으므로 이를 제거하면 연산 횟수를 줄일 수 있음.
 *
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P14888 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int S[], P[];
    private static Operator[] OPS;

    enum Operator {
        PLUS((a, b) -> a + b), MINUS((a, b) -> a - b), MUTI((a, b) -> a * b), DIVIDE((a, b) -> a / b);

        BiFunction<Long, Long, Long> operate;

        Operator(BiFunction<Long, Long, Long> operate) {
            this.operate = operate;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        S = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        OPS = new Operator[N-1];
        int j = 0;
        StringTokenizer token = new StringTokenizer(reader.readLine());
        for (int i=0; i<4; i++) {
            Operator op = Operator.values()[i];
            int cnt = Integer.parseInt(token.nextToken());
            while (cnt != 0) {
                OPS[j++] = op;
                cnt--;
            }
        }
        P = IntStream.range(0, N-1).toArray();
        long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
        do {
            long sum = S[0];
            for (int i=1; i<N; i++) {
                Operator op = OPS[P[i-1]];
                sum = op.operate.apply(sum, (long) S[i]);
            }
            min = Math.min(min, sum);
            max = Math.max(max, sum);
        } while (nextPermutation(P));

        System.out.println(max);
        System.out.println(min);
    }

    private static boolean nextPermutation(int[] p) {
        int i = p.length-1;
        while (i > 0 && (p[i-1]>=p[i] ||
                OPS[p[i-1]] == OPS[p[i]]))  //중복 패스
            i--;
        if (i == 0) return false;
        int j = p.length-1;
        while (p[i-1]>=p[j] ||
                OPS[p[i-1]] == OPS[p[i]])   //중복 패스
            j--;
        swap(i-1, j, p);
        j = p.length-1;
        while (i<j)
            swap(i++, j--, p);
        return true;
    }

    private static void swap(int i, int j, int[] p) {
        int t = p[i];
        p[i] = p[j];
        p[j] = t;
    }

}
