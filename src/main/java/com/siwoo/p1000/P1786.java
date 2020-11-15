package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Used(algorithm = Algorithm.KMP)
public class P1786 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String S, P;

    public static void main(String[] args) throws IOException {
        S = reader.readLine();
        P = reader.readLine();
        List<Integer> indexes = new KMP(S, P).match();
        System.out.println(indexes.size());
        StringBuilder sb = new StringBuilder();
        for (int i: indexes)
            sb.append(i+1).append("\n");
        System.out.println(sb);
    }
    
    private static class KMP {
        private final String S, P;

        public KMP(String s, String p) {
            S = s;
            P = p;
        }
        
        public List<Integer> match() {
            return match(S, P);
        }

        private List<Integer> match(String s, String p) {
            List<Integer> indexes = new ArrayList<>();
            int N = s.length(),
                    M = p.length();
            int[] fail = fail(p);
            int ps = 0;
            for (int i=0; i<N; i++) {
                while (ps > 0 && s.charAt(i) != p.charAt(ps))  //skipping
                    ps = fail[ps-1];
                if (p.charAt(ps) == s.charAt(i)) {
                    if (ps == M-1) {    //found
                        indexes.add(i-M+1);
                        ps = fail[ps];
                    } else {
                        ps++;
                    }
                }
            }
            return indexes;
        }

        private int[] fail(String p) {
            int N = p.length();
            int[] fail = new int[N];
            int ps = 0;
            for (int i=1; i<N; i++) {
                while (ps > 0 && p.charAt(ps) != p.charAt(i))   //find another ps
                    ps = fail[ps-1];
                if (p.charAt(ps) == p.charAt(i))    //increase ps by 1
                    fail[i] = ++ps;
                else
                    fail[i] = 0;
            }
            return fail;
        }
    }
}