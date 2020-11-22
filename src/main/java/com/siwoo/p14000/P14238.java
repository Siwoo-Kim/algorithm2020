package com.siwoo.p14000;

import com.siwoo.util.Marker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//fail
@Marker
public class P14238 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String s;
    private static char[] answer;
    private static int[] cnt;
    private static Boolean[][][][][] visit = new Boolean[51][51][51][4][4];

    public static void main(String[] args) throws IOException {
        s = reader.readLine();
        cnt = new int[3];
        for (int i=0; i<s.length(); i++) {
            cnt[s.charAt(i)-'A']++;
        }
        answer = new char[s.length()];
        boolean result = go(0, 0, 0, 'D', 'D');
        if (!result)
            System.out.println(-1);
    }

    private static boolean go(int a, int b, int c, char day2, char day1) {
        if (a + b + c == s.length()) {
            if (check(answer) 
                    && cnt[0] == a 
                    && cnt[1] == b && 
                    cnt[2] == c) {
                System.out.println(new String(answer));
                return true;
            }
            return false;
        }
        if (visit[a][b][c][day1-'A'][day2-'A'] != null)
            return false;
        visit[a][b][c][day1-'A'][day2-'A'] = false;
        answer[a+b+c] = 'A';
        if (go(a+1, b, c, 'A', day1))
            return true;
        answer[a+b+c] = 'B';
        if (day1 != 'B')
            if (go(a, b+1, c, 'B', day1))
                return true;
        answer[a+b+c] = 'C';
        if (day1 != 'C' && day2 != 'C')
            if (go(a, b, c+1, 'C', day1))
                return true;
        return false;
    }
    
    private static boolean check(char[] p) {
        for (int i=0; i<p.length; i++) {
            if (p[i] == 'B' && i - 1 >= 0)
                if (p[i-1] == 'B') return false;
            if (p[i] == 'C' && i - 2 >= 0)
                if (p[i-1] == 'C' || p[i-2] == 'c')
                    return false;
        }
        return true;
    }

}
