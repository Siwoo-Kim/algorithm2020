package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 문자열 S 에서 두 번 이상 등장하는 가장 긴 부분 문자열.
 * 
 * 모든 문자열에 대해 max(substring -> max(fail))
 *  
 *  fail 자체가 suffix == prefix 이므로 두 번 이상 등장함을 보장.
 *  단, fail 은 i..j 에 대한 연속 부분 문자열이므로
 *  S 을 앞에서 잘라가는 모든 문자열에 대해서도 fail 을 구해줘야 된다. 
 *  
 *  => 두 부분 문자열이 겹쳐짐이 가능하기 때문에 fail 로 풀 수 있음. 
 *  => 겹쳐지지 않는다면 추가 검증 작업이 요구.
 *  
 */
@Used(algorithm = Algorithm.KMP)
public class P1701 {

    public static void main(String[] args) throws IOException {
        String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
        Stack<Character> stack = new Stack<>();
        for (char c: s.toCharArray())
            stack.push(c);
        int answer = 0;
        String ss = "";
        while (!stack.isEmpty()) {
            ss = stack.pop() + ss;
            int[] fail = fail(ss);
            for (int j=0; j<fail.length; j++)
                answer = Math.max(answer, fail[j]);
        }
        System.out.println(answer);
    }
    
    public static int[] fail(String s) {
        int n = s.length();
        int[] fail = new int[n];
        int ps = 0;
        for (int i=1; i<n; i++) {
            while (ps > 0 && s.charAt(ps) != s.charAt(i))
                ps = fail[ps-1];
            if (s.charAt(ps) == s.charAt(i))
                fail[i] = ++ps;
            else
                fail[i] = 0;
        }
        return fail;
    }
    
}
