package com.siwoo.p13000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * S 의 prefix==suffix 은 while(ps!=0) ps=fail[ps-1]  => S[0 .. ps]
 * 
 * PS 가 몇 번 등장했는가?
 *  각 ps 의 길이을 cnt 에 누적.
 *  ps 가 fail[ps-1] 의 등장을 의미하므로 값을 누적.
 * 
 */
public class P13576 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String S;
    
    public static void main(String[] args) throws IOException {
        S = reader.readLine();
        int N = S.length();
        int[] fail = fail(S);
        int[] cnt = new int[N+1];
        for (int ps=0; ps<N; ps++)
            cnt[fail[ps]]++;
        for (int ps=N; ps>0; ps--)
            cnt[fail[ps-1]] += cnt[ps];
        Stack<Pair> stack = new Stack<>();
        for (int i=N; i>0; i=fail[i-1])
            stack.push(new Pair(i, cnt[i] + 1));
        System.out.println(stack.size());
        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            System.out.println(pair.len + " " + pair.cnt);
        }
    }
    
    private static class Pair {
        int len, cnt;

        public Pair(int len, int cnt) {
            this.len = len;
            this.cnt = cnt;
        }
    }
    
    private static int[] fail(String s) {
        int N = s.length();
        int[] fail = new int[s.length()];
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
