package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Used(algorithm = Algorithm.RABIN_KARP)
public class P16916 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String S, P;
    private static long BASE = 256, MOD = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        S = reader.readLine();
        P = reader.readLine();
        int n = S.length(),
                m = P.length();
        if (S.length() < P.length()) {
            System.out.println(0);
            return;
        }
        long h1 = hash(S.substring(0, P.length()));
        long h2 = hash(P);
        long firstBase = 1;
        for (int i=0; i<m-1; i++)
            firstBase = (firstBase * BASE) % MOD;
        for (int i=0; i<=n-m; i++) {
            if (h1 == h2 && P.equals(S.substring(i, i+m))) {
                System.out.println(1);
                return;
            }
            if (i+m < n) {
                h1 = h1 - (S.charAt(i) * firstBase) % MOD;
                h1 = (h1 + MOD) % MOD;
                h1 = ((h1 * BASE) % MOD + S.charAt(i+m)) % MOD; 
            }
        }
        System.out.println(0);
    }

    private static long hash(String s) {
        long h = 0;
        for (int i=0; i<s.length(); i++)
            h = (h * BASE + s.charAt(i)) % MOD;
        return h;
    }
}
