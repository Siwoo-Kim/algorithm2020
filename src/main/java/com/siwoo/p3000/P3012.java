package com.siwoo.p3000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Used(algorithm = Algorithm.DP)
public class P3012 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, MOD = 100000;
    private static String s;
    private static char[] openBrackets = {'(', '[', '{'};
    private static char[] closeBrackets = {')', ']', '}'};
    private static Long[][] D;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        s = reader.readLine();
        D = new Long[N][N];
        long cnt = go(0, N-1);
        if (cnt >= MOD)
            System.out.printf("%05d", cnt % MOD);
        else
            System.out.println(cnt);
    }

    private static long go(int left, int right) {
        if (left > right) return 1;
        if (D[left][right] != null)
            return D[left][right];
        char c = s.charAt(left);
        long cnt = 0;
        for (int i=left+1; i<=right; i+=2) {
            char c2 = s.charAt(i);
            for (int k=0; k<3; k++) {
                if ((c == '?' || c == openBrackets[k]) 
                        && (c2 == '?' || c2 == closeBrackets[k])) {
                    cnt += go(left + 1, i - 1) * go(i + 1, right);
                    if (cnt >= MOD)
                        cnt = MOD + cnt % MOD;
                }
            }
        }
        return D[left][right] = cnt;
    }
}
