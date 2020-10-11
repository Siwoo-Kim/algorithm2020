package com.siwoo.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * => 문제를 이해하기 어려움.
 *
 * K 개의 글자을 배울 경우
 * 주어진 단어 N 중 읽을 수 있는 최대 단어의 갯수.
 * 이때 K 개에서 (antatica) -> antic 은 무조건 배워야 함.
 * - 경우의 수 (2^26-5) * (N * 단어 검색 (26)) = 1,090,519,040 = OK?..
 *  -> 2^26-5 론 문제를 풀 수 없음. (K 제한을 놓침)
 *  -> 21Ck-5 로 문제를 풀어야 함.
 *
 *  단어 검색. -> boolean['a'-'z'] = 해당 글자를 배웠는지 확인
 *
 */
public class P1062 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Set[] S;
    private static int N, K;
    private static Set<Character> must = new HashSet<>();

    static {
        for (char c: "antatica".toCharArray())
            must.add(c);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());
        S = new HashSet[N];
        for (int i=0; i<N; i++) {
            String line = reader.readLine();
            S[i] = new HashSet();
            for (char c: line.toCharArray())
                S[i].add(c);
        }
        int cnt = combination('a', K, new Stack<>());
        System.out.println(cnt);
    }

    private static int combination(char start, int K, Stack<Character> stack) {
        if (stack.size() == K-must.size()) {
            int cnt = 0;
            for (Set word: S) {
                boolean ok = true;
                for (Object o: word) {
                    char c = (char) o;
                    if (must.contains(c)) continue;
                    if (!stack.contains(c)) {
                        ok = false;
                        break;
                    }
                }
                if (ok) cnt++;
            }
            return cnt;
        }
        int max = 0;
        for (char c=start; c<='z'; c++) {
            if (must.contains(c)) continue;
            stack.push(c);
            max = Math.max(combination((char)(c + 1), K, stack), max);
            stack.pop();
        }
        return max;
    }
}
