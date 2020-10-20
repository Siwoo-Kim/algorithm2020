package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * A[0] 에 집중해서 풀면된다.
 *
 * A[0] 의 전구에 경우 바뀔 수 있는 경우의 수는 0 번 스위치와 1번 스위치에 영향을 받는다.
 * 이때 A[0] 에 대해, 누른 경우와 안누른 경우라고 가정하고 따로 계산한다면
 * A[0] 을 바꿀 수 있는 경우의 수는 1번 스위치를 누른다, 누르지 않는다는 두 가지 경우의 수로 좁혀진다.
 * 따라서 각각의 A1(0 번 스위치를 누른 경우), A2(0 번 스위치를 누르지 않은 경우) 을 나누면서 A[i-1] != B[i-1]
 *  을 확인하여 스위치를 눌러주면 된다.
 */
@Used(algorithm = Algorithm.GREEDY)
public class P2138 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] A1, A2, B;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A1 = Arrays.stream(reader.readLine().split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        A2 = Arrays.copyOf(A1, N);
        B = Arrays.stream(reader.readLine().split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        A1[0] = 1 - A1[0];
        A1[1] = 1 - A1[1];
        int cnt1 = click(A1, B, 1, 1, N-1);
        int cnt2 = click(A2, B, 0, 1, N-1);

        if (A1[N-1] != B[N-1]) {
            A1[N-2] = 1 - A1[N-2];
            A1[N-1] = 1 - A1[N-1];
            cnt1++;
        }
        if (A2[N-1] != B[N-1]) {
            A2[N-2] = 1 - A2[N-2];
            A2[N-1] = 1 - A2[N-1];
            cnt2++;
        }
        boolean ok1 = true, ok2 = true;
        for (int i=0; i<N; i++) {
            if (A1[i] != B[i]) ok1 = false;
            if (A2[i] != B[i]) ok2 = false;
        }
        if (ok2)
            System.out.println(cnt2);
        else if (ok1)
            System.out.println(cnt1);
        else
            System.out.println(-1);
    }

    private static int click(int[] A, int[] B, int cnt, int i, int end) {
        if (i == end) return cnt;
        if (A[i-1] != B[i-1]) {
            A[i-1] = 1 - A[i-1];
            A[i] = 1 - A[i];
            A[i+1] = 1 - A[i+1];
            cnt++;
        }
        return click(A, B, cnt, i+1, end);
    }


}
