package com.siwoo.util;

import java.util.ArrayList;
import java.util.List;

/**
 * KMP 
 *  문자열 S 에서 패턴 P 검색 알고리즘.
 *  
 *  fail 실패 함수을 이용한 문자열 검색.
 *     fail[i] 은 P[0..i] 까지 부분 문자열에서 
 *     prefix==suffix 가 될 수 있는 부분 문자열 중 가진 긴 것의 길이.
 *     
 *     prefix 접두사.
 *     P=ABCABE
 *     A
 *     AB
 *     ABC
 *     ABCA
 *     ABCAB
 *     ABCABE
 *     
 *     suffix 접미사
 *     P=ABCABE
 *     E
 *     BE
 *     ABE
 *     CABE
 *     BCABE
 *     ABCABE
 *     
 *     ABCABDABCABEABC 의 fail
 *              [prefix, suffix]
 *     i        부분 문자열           fail[i]
 *     0        A                       0
 *     1        AB                      0
 *     2        ABC                     0
 *     3        [A]BC[A]                1   
 *     4        [AB]C[AB]               2
 *     5        ABCABD                  0
 *     6        [A]BCABD[A]             1 
 *     7        [AB]CABD[AB]            2
 *     8        [ABC]ABD[ABC]           3
 *     9        [ABCA]BD[ABCA]          4   => fail[4] 은 3 번의 정보를 가지고 있다.
 *                                          => fail[fail[4]-1]
 *     10       [ABCAB]D[ABCAB]         5   => fail[5] 은 4 번의 정보를 가지고 있다.
 *                                          => fail[fail[5]-1] 
 *                                          => 이 정보를 통해 ps 순회 검색이 가능하다.
 *     11       ABCABDABCABE            0
 *     12       [A]BCABDABCABE[A]       1
 *     13       [AB]CABDABCABE[AB]      2
 *     14      [ABC]ABDABCABE[ABC]      3
 *     
 *     fail 구현하기
 *      prefix==suffix 가 될 수 있는 최장 부분 문자열을 ps 라 하자.
 *      
 *      i 번째 문자에 대해서 
 *          fail[i-1] 에 이전 ps 의 길이가 저장되어 있으므로,
 *          P[i] 와 비교할 문자열은 문자열은 P[ps] 이다. 
 *              (문자열에서 s.length-1 은 마지막 문자열이므로)
 *          
 *          1. P[ps] == P[i] 인 경우.
 *              ps 의 길이가 1 증가한다.
 *              fail[i] 에 fail[i-1]+1 을 저장.
 *              
 *          2. P[fail[i-1]] != P[i] 인 경우.
 *              이때는 fail[0..i] 에 저장된 정보를 이용해 
 *              현재 i 에 대한 가능한 다음 후보 ps 을 찾아야 한다.
 *
 *              i=7, ABACABAB    인 경우. 이전 ps 은 3
 *                  P[ps] != P[i] 이므로 다음 후보 ps 을 검색해야 함.
 *                  
 *              다음 후보 ps 검색하기.                  
 *                  fail[ps-1] 의 정보를 사용해 순회하며 적합한 ps 을 찾아준다. 
 *                  until (P[fail[ps-1]] != P[i]) 
 *                  위의 9, 10 case 참고.
 *  
 *  그래서 fail 을 어떻게 사용하는데?
 *      S[i] 와 P[ps] 을 검사하다, 
 *      다른 경우가 나온다면 ps = fail[ps-1] 로 이동하며 
 *          P[ps] == S[i] 을 비교해준다. (이미 저장된 ps 을 이용해 불필요한 검사를 skip)
 *      
 *      만약 ps 의 길이가 P.length-1 이고, P[ps] == S[i] 라면 일치한 문자열을 찾음.
 *      
 *      "ABCABE"
 *      
 *      P[i] = A B C A B E
 *      fail = 0 0 0 1 2 0
 *      
 *      S[i] = A B C A B D A B C A B E
 *             A B C A B E              ps = 5 => 검사 중 S[i] != P[ps]
 *                   A B C A B E        ps = fail[ps-1] = 2 => S[i] != P[ps]
 *                       A B C A B E    ps = 0 이므로 i++
 *                         A B C A B E  
 */
public class KMP {
    
    public static int[] fail(String p) {
        int n = p.length();
        int[] fail = new int[n];
        fail[0] = 0;
        int ps = 0;
        for (int i=1; i<n; i++) {
            while (ps > 0 && p.charAt(ps) != p.charAt(i))
                ps = fail[ps-1];
            if (p.charAt(ps) == p.charAt(i)) {
                fail[i] = ps + 1; //find ps.. increase by 1
                ps++;
            } else {
                fail[i] = 0;
            }
        }
        return fail;
    }
    
    public static List<Integer> match(String s, String p) {
        int[] fail = fail(p);
        List<Integer> indexes = new ArrayList<>();
        int N = s.length(), M = p.length();
        int ps = 0;
        for (int i=0; i<N; i++) {
            while (ps > 0 && s.charAt(i) != p.charAt(ps))   //skipping
                ps = fail[ps-1];
            if (s.charAt(i) == p.charAt(ps)) {
                if (ps == M-1) {
                    indexes.add(i-M+1);
                    ps = fail[ps];  //skipping
                } else {
                    ps++;
                }
            }
        }
        return indexes;
    } 

    public static void main(String[] args) {
        String s = "ABCABDABCABCABEF",
                p = "ABCABE";
        List<Integer> answers = match(s, p);
        for (int index: answers)
            System.out.println(s.substring(index, index + p.length()));
    }
    
}
