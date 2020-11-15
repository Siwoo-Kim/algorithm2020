package com.siwoo.p12000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 문자열 검색은 O(N) 으로 해결할 수 있지만,
 * 순환 순열 생성은 10^(5*2) 로 제한 시간에 풀지 못함.
 * 
 * 순환 순열 흉내내기.
 *  문자열 s 의 뒤에 s 을 s.length-1 덧붙인다.
 *  
 *   eg) s="abcd", p="abcdabc"
 *   
 *   i  0   1   2   3   4   5   6  
 *      a   b   c   d   a   b   c
 *      [           ]
 *          [           ]
 *              [           ]
 *                  [           ]
 *  p="abcdabc" 에서 패턴 a xor b (위의 가로친 sequence) 가 0 이 되는 순열이 몇번 등장하는지 확인.
 *  
 *  a xor b = 0 => (a == b)
 *  
 */
@Used(algorithm = Algorithm.KMP)
public class P12104 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String A, B;
    
    public static void main(String[] args) throws IOException {
        A = reader.readLine();
        B = reader.readLine();
        B += B.substring(0, B.length()-1);
        System.out.println(new KMP().search(B, A).size());
    }
    
    private static class KMP {
        
        public List<Integer> search(String s, String p) {
            int N = s.length(), M = p.length();
            int[] fail = fail(p);
            int ps = 0;
            List<Integer> indexes = new ArrayList<>();
            for (int i=0; i<N; i++) {
                while (ps != 0 && s.charAt(i) != p.charAt(ps))
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

        private int[] fail(String p) {
            int N = p.length();
            int[] fail = new int[p.length()];
            int ps = 0;
            for (int i=1; i<N; i++) {
                while (ps != 0 && p.charAt(i) != p.charAt(ps))
                    ps = fail[ps-1];
                if (p.charAt(i) == p.charAt(ps))
                    fail[i] = ++ps;
                else
                    fail[i] = 0;
            }
            return fail;
        }
    }
}
