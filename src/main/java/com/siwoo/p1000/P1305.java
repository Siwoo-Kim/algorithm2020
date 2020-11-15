package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 광고 문구 T 을 만들 수 있는 광고의 최소 길이.
 *  T.length - fail[T.length-1]
 *  
 *  ababacdeabab
 *                      => fail[T.length-1] = 4
 *  T.length-fail[T.length-1] 
 *      = acdeabab 의 길이 
 *      acdeabab 보다 짧으면서 해당 문구를 만들 수 있는 광고는 없다. 
 *  
 */

@Used(algorithm = Algorithm.KMP)
public class P1305 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String S;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        S = reader.readLine();
        int[] fail = new int[N];
        int ps = 0;
        for (int i=1; i<N; i++) {
            while (ps > 0 && S.charAt(i) != S.charAt(ps))
                ps = fail[ps-1];
            if (S.charAt(i) == S.charAt(ps))
                fail[i] = ++ps;
            else
                fail[i] = 0;
        }
        System.out.println(N - fail[N-1]);
    }
}
