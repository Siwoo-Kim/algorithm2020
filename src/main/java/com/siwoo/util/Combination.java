package com.siwoo.util;

import java.math.BigInteger;
import java.util.*;

/**
 * 조합 - 선택의 순서가 중요하지 않은 경우의 모든 경우의 수.
 * 
 * 이항 계수 (nCr):  pascal`s triangle (n, r) 
 *  pascal`s triangle: t[n][r] = t[n-1][r-1] + t[n-1][r]
 *  
 *  증명
 *      d[n][k] = n 개 중에서 k 을 중복없이 고르는 경우의 수.
 *         n 번을 고르는 경우 = d[n-1][k-1]
 *         n 번을 고르지 않는 경우 = d[n-1][k]
 */
public class Combination {
    
    public static <E> void combination(List<E> elements, int select) {
        assert elements != null && elements.size() >= select;
        //elements = new ArrayList<>(new HashSet<>(elements)); - real combination
        combination(0, elements, elements.size(), select, new Stack<E>());
    }

    private static <E> void combination(int start, List<E> elements, int N, int M, Stack<E> stack) {
        if (stack.size() == M) {
            StringBuilder sb = new StringBuilder();
            stack.forEach(e -> sb.append(e).append(" "));
            sb.append("\n");
            System.out.println(sb.toString());
            return;
        }
        for (int i=start; i<N; i++) {
            stack.push(elements.get(i));
            combination(i+1, elements, N, M, stack);
            stack.pop();
        }
    }

    public static long nCr(long N, long R) {
        // nPr(N, R) / R!
        return BigInteger.valueOf(nPr(N, R)).divide(factorial(R)).longValue();
    }

    private static long nPr(long N, long R) {
        // N! / N-R!
        return factorial(N).divide(factorial(N-R)).longValue();
    }

    private static BigInteger factorial(long N) {
        if (N == 0) return BigInteger.ONE;
        return factorial(N-1).multiply(BigInteger.valueOf(N));
    }

    public static void main(String[] args) {
        //combination(Arrays.asList(1, 2, 3, 4, 5), 3);
        
//        System.out.println(factorial(10));
//        System.out.println(nPr(10, 3));
//        System.out.println(nPr(20, 3));
//        System.out.println(nCr(200, 3));

        System.out.printf("%,d%n", nCr(10, 5));   //xss
    }
}
