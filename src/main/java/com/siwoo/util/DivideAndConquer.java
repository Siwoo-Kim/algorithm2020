package com.siwoo.util;

import java.util.TreeMap;

/**
 * Divide and Conquer
 *
 * 분할 정복은 문제를 2 개 이상의 작은 부분 문제로 나눈 뒤 푸는 것. (분할)
 * 이후 작은 문제를 합쳐서 정답을 구함 (정복)
 *  => DP 와 다르게 문제가 중복되면 안된다.
 *
 *  상한과 하한. (TreeMap)
 *      상한(i) UpperBound = higher = i 보다 큰 수 중 첫번 째 수.
 *      하한(i) LowerBound = ceiling? = i 와 같거나 큰 수 중 첫번 째 수.
 */
public class DivideAndConquer {

    public static void main(String[] args) {
        TreeMap<Integer, Boolean> map = new TreeMap<>();
        for (int i=1; i<=5; i++)
            map.put(i, true);
        map.put(9, true);
        System.out.println(map.higherKey(3));   //상한 4
        System.out.println(map.ceilingKey(3));  //하한 3

        System.out.println(map.higherKey(5));   //상한 9
        System.out.println(map.ceilingKey(5));  //하한 5

        System.out.println(map.higherKey(8));   //상한 9
        System.out.println(map.ceilingKey(8));  //하한 9

        System.out.println(map.higherKey(9));   //상한 null
        System.out.println(map.ceilingKey(9));  //하한 9

        System.out.println("==================");
        int[] a = {1, 2, 3, 4, 5, 9};
        System.out.println(upperBound(a, 3));
        System.out.println(lowerBound(a, 3));

        System.out.println(upperBound(a, 5));
        System.out.println(lowerBound(a, 5));

        System.out.println(upperBound(a, 8));
        System.out.println(lowerBound(a, 8));

        System.out.println(upperBound(a, 9));
        System.out.println(lowerBound(a, 9));
   }

   private static int upperBound(int[] a, int x) {
        int answer = -1,
                l = 0, r = a.length-1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (a[mid] == x) {
                answer = mid+1; //out bound?
                l = mid+1;
            } else if (a[mid] > x)
                r = mid-1;
            else
                l = mid+1;
        }
        if (answer == -1)
            return a[l] < x? -1: a[l];
        else
            return answer >= a.length?  -1: a[answer];
   }

    private static int lowerBound(int[] a, int x) {
        int answer = -1,
                l = 0, r = a.length-1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (a[mid] == x) {
                answer = mid;
                r = mid - 1;
            } else if (a[mid] > x)
                r = mid -1;
            else
                l = mid + 1;
        }
        return answer == -1? a[l]: a[answer];
    }

}