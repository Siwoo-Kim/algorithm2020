package com.siwoo.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1062_BITMASK {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K;
    private static final int MUST;
    private static int[] WORDS;

    static {
        int bits = 0;
        for (char c: "antatica".toCharArray())
            bits |= 1 << c;
        MUST = bits;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());
        WORDS = new int[N];
        for (int i=0; i<N; i++) {
            String line = reader.readLine();
            int bits = 0;
            for (char c: line.toCharArray())
                bits |= 1 << c;
            WORDS[i] = bits;
        }
        int cnt = combination('a', 0, 0);
        System.out.println(cnt);
    }

    public static int size(int bits) {
        int cnt = 0;
        for (char c='a'; c<='z'; c++)
            if ((bits & (1<<c)) != 0)
                cnt++;
        return cnt;
    }

    private static int combination(char start, int learn, int current) {
        if (current == K - size(MUST)) {
            int cnt = 0;
            for (int word: WORDS) {
                boolean ok = true;
                for (char c='a'; c<='z'; c++) {
                    if ((MUST & (1 << c)) != 0) continue;
                    if ((word & (1 << c)) != 0) {
                        if ((learn & (1 <<c)) == 0) {
                            ok = false;
                            break;
                        }
                    }
                }
                if (ok) cnt++;
            }
            return cnt;
        }
        int max = 0;
        for (char c=start; c<='z'; c++) {
            if ((MUST & (1<<c)) != 0) continue;
            max = Math.max(combination((char) (c + 1), learn | (1 << c), current + 1), max);
        }
        return max;
    }
}
