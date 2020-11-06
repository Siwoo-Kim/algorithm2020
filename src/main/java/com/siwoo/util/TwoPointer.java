package com.siwoo.util;

/**
 * 투포인터 - 배열에서 A, 특정 조건 (크다, 작다, 같다로 표현할 수 있는)에 맞는 모든 부분 집합을 구하는 알고리즘.
 *
 * 두 개의 포인터 left, right, 목표값 = m, 현재 agg 값 = s
 * 
 * 
 * left 가 A.length 보다 작을 동안 아래를 진행한다.
 *  1. s 가 m 보다 작거나, right < A.length 라면 right 의 값을 s 에 누적하고 +1.
 *  2. s 가 m 보다 작거나 같다면 left 의 값을 s 에서 빼고 + 1
 *      이때 s == m 이라면 조건에 만족하는 하나의 부분 집합을 찾은 것.
 *  
 */
public class TwoPointer {
    
    private static int twoPointer(int[] a, int m) {
        int l = 0, r = 0, cnt = 0, sum = 0;
        while (l < a.length) {
            if (sum < m && r < a.length)
                sum += a[r++];
            else {
                if (sum == m)
                    cnt++;
                sum -= a[l++];
            }
        }
        return cnt;
    }
    
}
