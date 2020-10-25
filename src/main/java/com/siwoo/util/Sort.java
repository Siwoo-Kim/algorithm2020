package com.siwoo.util;

import java.util.Random;

/**
 * 퀵소트
 *  1. 피봇의 위치을 구한다.
 *  2. 문제를 피봇의 왼쪽 부분, 피봇의 오른쪽 부분을 쪼갠다.
 *
 *  정렬된 배열에 관해선 퀵소트는 N^2 이므로 셔플해야됨.
 *      => 퀵소트는 binary search 의 특성과 같으므로.
 *
 *  stable sort -> 정렬하기 전의 순서가 유지 (순서가 바뀔 필요가 없다면) 유지되는 정렬 => merge sort
 */
public class Sort {

    public static void sort(int[] A) {
        shuffle(A);
        sort(0, A.length, A);
    }

    private static void sort(int left, int right, int[] A) {
        if (right - left <= 1) return;
        int i = left, j = right, e = A[left];
        while (i < j) {
            while (i < j && A[--j] > e) ;   //last index = right-1
            while (i < j && A[++i] < e) ;   //first exclude pivot
            if (i < j)
                swap(i, j, A);
        }
        swap(j, left, A);   //locate pivot
        int pivot = j;
        sort(left, pivot, A);   //right exclusive
        sort(pivot+1, right, A);
    }

    private static void shuffle(int[] A) {  //fisher-yates shuffle
        Random random = new Random();
        for (int i=A.length-1; i>0; i++) {
            int x = random.nextInt(i+1);    //i+1 exclusive
            swap(i, x, A);
        }
    }

    private static void swap(int i, int j, int[] A) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
}
