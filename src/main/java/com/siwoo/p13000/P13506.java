package com.siwoo.p13000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class P13506 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String S;

    public static void main(String[] args) throws IOException {
        S = reader.readLine();
        int[] fail = fail(S);
        int N = S.length(),
                ps = fail[N-1];
        String answer = "-1";
        while (ps != 0) {
            String prefix = S.substring(0, ps);
            if (search(S, prefix) > 2) {
                answer = prefix;
                break;
            }
            ps = fail[ps-1];
        }
        System.out.println(answer);
    }

    private static int search(String s, String p) {
        int N = S.length(), M = p.length();
        int[] fail = fail(p);
        int ps = 0;
        int cnt = 0;
        for (int i=0; i<N; i++) {
            while (ps != 0 && s.charAt(i) != p.charAt(ps))
                ps = fail[ps-1];
            if (s.charAt(i) == p.charAt(ps)) {
                if (ps == M-1) {
                    cnt++;
                    ps = fail[ps];
                } else {
                    ps++;
                }
            }
        }
        return cnt;
    }

    private static int[] fail(String s) {
        int N = s.length();
        int[] fail = new int[N];
        int ps = 0;
        for (int i=1; i<N; i++) {
            while (ps != 0 && s.charAt(i) != s.charAt(ps))
                ps = fail[ps-1];
            if (s.charAt(i) == s.charAt(ps))
                fail[i] = ++ps;
        }
        return fail;
    }
}
