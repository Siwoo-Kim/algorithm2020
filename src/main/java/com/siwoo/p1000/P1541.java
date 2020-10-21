package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 그리디 기준 = - 가 나오는 부분에 대해서 괄호를 최대한 묶어준다. (- 값을 최대로 키워준다)
 */
@Used(algorithm = Algorithm.GREEDY)
public class P1541 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String S;

    public static void main(String[] args) throws IOException {
        S = reader.readLine();
        int sum = 0;
        for (int i=0; i<S.length(); i++) {
            char c = S.charAt(i);
            if (c == '-') {
                int temp = 0;
                int j=i+1;
                for (;j<S.length() && S.charAt(j) != '-'; j++) {
                    if (S.charAt(j) == '+') continue;
                    Parse parse = getNumber(S, j);
                    temp += parse.value;
                    j = parse.index;
                }
                sum -= temp;
                if (S.length() == j) break;
                else i = j - 1;
            } else if (c == '+')
                continue;
            else {
                Parse parse = getNumber(S, i);
                sum += parse.value;
                i = parse.index;
            }
        }
        System.out.println(sum);
    }

    private static class Parse {
        int value, index;

        public Parse(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    private static Parse getNumber(String s, int i) {
        int v = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i)))
             v = v * 10 + s.charAt(i++) - '0';
        return new Parse(v, i-1);
    }

}
