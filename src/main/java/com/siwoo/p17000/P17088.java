package com.siwoo.p17000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Ai = A1 + (N-1)*D
 * D = Ai+1 - Ai
 *
 * A[0], A[1] 에서 공차를 구한 뒤 연산의 횟수를 계산.
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P17088 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[] A;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        if (N == 1) {
            System.out.println(0);
            return;
        }
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int answer = -1;
        int a0 = A[0],
                a1 = A[1];
        for (int i=-1; i<=1; i++)
            for (int j=-1; j<=1; j++) {
                int x = sequence(a0 + i, a1 + j);
                if (x == -1) continue;
                x += Math.abs(i) + Math.abs(j);
                if (answer == -1 || answer > x)
                    answer = x;
            }
        System.out.println(answer);
    }

    private static int sequence(int a0, int a1) {
        int d = a1 - a0;
        int a = a0 + d;
        boolean ok = true;
        int cnt = 0;
        for (int i=2; i<A.length; i++) {
            a += d;
            if (A[i] == a) continue;
            if (A[i]-1 == a) cnt++;
            else if (A[i]+1 == a) cnt++;
            else {
                ok = false;
                break;
            }
        }
        return !ok? -1: cnt;
    }
}
