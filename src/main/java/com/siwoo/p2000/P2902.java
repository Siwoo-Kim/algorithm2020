package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Used(algorithm = Algorithm.KMP)
public class P2902 {

    public static void main(String[] args) throws IOException {
        String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
        List<Integer> indexes = new KMP(s, "-").match();
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        for (int i: indexes)
            sb.append(s.charAt(i+1));
        System.out.println(sb);
    }
    
    private static class KMP {
        private String S, P;

        public KMP(String s, String p) {
            S = s;
            P = p;
        }
        
        public List<Integer> match() {
            return match(S, P);
        }
        
        private int[] fail(String p) {
            int M = p.length();
            int[] fail = new int[M];
            int ps = 0;
            for (int i=1; i<M; i++) {
                while (ps > 0 && p.charAt(ps) != p.charAt(i))
                    ps = fail[ps-1];
                if (p.charAt(ps) == p.charAt(i))
                    fail[i] = ++ps;
                else
                    fail[i] = 0;
            }
            return fail;
        }
        
        private List<Integer> match(String s, String p) {
            int N = s.length(),
                    M = p.length();
            int[] fail = fail(p);
            List<Integer> indexes = new ArrayList<>();
            int ps = 0;
            for (int i=0; i<N; i++) {
                while (ps > 0 && s.charAt(i) != p.charAt(ps))
                    ps = fail[ps-1];
                if (s.charAt(i) == p.charAt(ps)) {
                    if (ps == M-1) {
                        indexes.add(i-M+1);
                        ps = fail[ps];
                    } else {
                        ps++;
                    }
                }
            }
            return indexes;
        }
    }
}
