package com.siwoo.util;

import java.util.Arrays;

/**
 * 순열 Permutation
 *  순서의 의미가 있는 줄세우기
 *  정확히는 임의의 수열 S 을 다른 순서로 섞는 연산
 *  N! 가지의 경우의 수.
 *
 *  -> 문제에 대한 경우의 수를 모두 시뮬레이션해야 할때, 순서만 변경 가능하다면 순열 문제이다.
 *  -> 문제가 "순서" 혹은 "선택" 의 경우에 유.
 *
 * 사전순 순열의 특징.
 *      1. 모든 순열은 앞의 어떤 순열로 시작하는 마지막 순열이다. 이것을 첫순열이라 한다.
 *          ex) 12 로 시작하는 마지막 순열 1, 2, 4, 3
 *      2. 그것의 다음 순열은 앞의 어떤 순열로 시작하는 첫 순열이다. 이것을 마지막 순열이라 한다.
 *          ex) 13 으로 시작하는 첫 순열 1, 3, 2, 4 -> (132 로 시작하는 첫 순열)
 *      3. 특정 숫자로 시작하는 첫순열은 오름차순이다. (특정 부분을 제외한)
 *      4. 특정 숫자로 시작하는 마지막 순열은 비오름차순이다.  (특정 부분을 제외한)
 *
 * 다음 순열 알고리즘.  O(N)
 *  1. A[i-1] < A[i] 을 만족하는 i 을 뒤에서 찾는다.    (오름차순 검증)
 *  2. A[i-1] < A[j] 을 만족하는 j 을 뒤에서 찾는다.  열  (교환 요소 검증) i-1 가 j 보다 크다면 이미 방문한 순열
 *  3. swap(A[i-1], A[j])
 *  4. A[i] 부터 순서를 뒤집는다.    (오름차순을 뒤집는다 -> 내림차순)
 *              i-1 i
 *      ex) 7, 2, 3, 6, 5, 4, 1 -> 723 으로 시작하는 마지막 순열
 *          7, 2, 4  ?  ?  3  ? -> 724 로 시작하는 첫 순열.
 *          7, 2, 4, 1, 3, 5, 6 -> 순서를 뒤집는다.
 *
 * 다음 순열을 이용한 모든 순열 순회 -> O(N) * N!
 */
public class Permutation {

    public static boolean nextPermutation(int[] S) {
        int i = S.length - 1;
        while (i > 0 && S[i-1] >= S[i]) //오름차순 검증
            i--;
        if (i == 0) return false;
        int j = S.length - 1;
        while (S[i-1] >= S[j]) //교환 요소 검증
            j--;
        swap(i-1, j, S);
        j = S.length - 1;
        while (i < j)
            swap(i++, j--, S);
        return true;
    }

    private static void swap(int i, int j, int[] S) {
        int t = S[i];
        S[i] = S[j];
        S[j] = t;
    }

    public static void main(String[] args) {
        int[] S = {1, 2, 2, 3, 4};
        do {
            System.out.println(Arrays.toString(S));
        } while (nextPermutation(S));
    }
}
