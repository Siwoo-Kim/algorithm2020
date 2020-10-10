package com.siwoo.p14000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 각 인원 i 에 대하여 어떤 팀에 소속하는 지에 대한 순열을 만듬. eg) {0, 1, 1} -> 0팀, 1팀, 1팀.
 * 순열을 돌며 두 팀의 최소 능력치를 구함.
 *
 * N(4 ≤ N ≤ 20)
 * O((N!/ N/2! * N/2!) * N^2) = 73,902,400
 *
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P14889_PERMUTATION {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, ANSWER = Integer.MAX_VALUE;
    private static int[][] S;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        S = new int[N][N];
        for (int i=0; i<N; i++)
            S[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        int[] P = new int[N];
        for (int i=N/2; i<N; i++)
            P[i] = 1;

        do {
            Set<Integer> start = new HashSet<>(),
                    link = new HashSet<>();
            int scoreA = 0, scoreB = 0;
            for (int i=0; i<P.length; i++) {
                if (P[i] == 1)
                    start.add(i);
                else
                    link.add(i);
            }
            for (int i=0; i<S.length; i++) {
                boolean isStartTeam = start.contains(i);
                int sum = 0;
                for (int j = 0; j < S.length; j++) {
                    if (i == j) continue;
                    if (isStartTeam && start.contains(j))
                         sum += S[i][j];
                    if (!isStartTeam && link.contains(j))
                         sum += S[i][j];
                }
                if (isStartTeam)
                    scoreA += sum;
                else
                    scoreB += sum;
            }
            ANSWER = Math.min(ANSWER, Math.abs(scoreA - scoreB));
        } while (nextPermutation(P));
        System.out.println(ANSWER);
    }

    private static boolean nextPermutation(int[] p) {
        int i = p.length-1;
        while (i>0 && p[i-1] >= p[i])
            i--;
        if (i == 0) return false;
        int j = p.length-1;
        while (p[i-1]>=p[j])
            j--;
        swap(i-1, j, p);
        j = p.length-1;
        while (i < j)
            swap(i++, j--, p);
        return true;
    }

    private static void swap(int i, int j, int[] p) {
        int t = p[i];
        p[i] = p[j];
        p[j] = t;
    }
}
