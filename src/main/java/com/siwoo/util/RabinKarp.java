package com.siwoo.util;

/**
 * 라빈 카프 (RabinKarp)
 *  - 해쉬 함수를 이용한 문자열 검색 알고리즘.
 *  
 *  알고리즘.
 *      문자열 S, 패턴 P 에 대해서.
 *          1. P 을 hash 한다, 이를 h1 라 하자.
 *          2. S 을 순회하며 S[i..i+P.length] 에 대해서 해쉬한다, 이를 h2 라 하자.
 *          3. h1 과 h2 가 다르다면 i++
 *          4. h1 과 h2 가 같다면 S[i..i+P.length] 와 P 을 실제 비교하고 같으면 ok.
 *      
 *      이때 h2 을 구하기 위해서 P.length 만큼 연산하므로 한번 생성된 "해쉬를 재사용"해야 한다.
 *      
 *      해쉬 함수 재사용하기.
 *          S[i..i+P.length-1] 이후의 다음 해쉬할 문자열은 S[i..i+P.length] 이다.
 *          이 둘의 차이는 S[i] 와 S[i+P.length].
 *          
 *          h = h - (S[i] * BASE) % MOD //S[i] 제거
 *          h = ((h * BASE) + S[i+P.length]) % MOD //S[i+P.length] 추가.
 *          
 *      라빈 핑거프린트 해쉬 함수
 *          문자의 ASCII 값, 256 진법과 소수를 사용한 모듈러 해쉬 함수.
 *              => 소수를 크게 하여, hash conflict 이 없도록 한다.
 *          h = (hash * BASE + ascii) % MOD
 *  
 */
public class RabinKarp {
    private static final long BASE = 256;
    private static final long MOD = Integer.MAX_VALUE;
    
    private static boolean match(String s, String p) {
        assert s != null && p != null && s.length() >= p.length();
        long ph = hash(p);
        long h = hash(s.substring(0, p.length()));
        long first = 1;
        for (int i=0; i<p.length()-1; i++)
            first = (first * BASE) % MOD;
        for (int i=0; i<=s.length(); i++) {
            if (h == ph && p.equals(s.substring(i, i+p.length())))
                return true;
            else if (i+p.length() < s.length()) {
                h = h - (s.charAt(i) * first) % MOD;
                h = (h + MOD) % MOD;
                h = ((h * BASE) % MOD + s.charAt(i+p.length())) % MOD;
            }
        }
        return false;
    }

    private static long hash(String s) {
        long h = 0;
        for (int i=0; i<s.length(); i++)
            h = (h * BASE + s.charAt(i)) % MOD;
        return h;
    }

    public static void main(String[] args) {
        System.out.println(RabinKarp.match("abcdef", "cde"));
        System.out.println(RabinKarp.match("abcdef", "cdf"));
    }
}
