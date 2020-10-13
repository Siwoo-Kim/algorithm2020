package com.siwoo.util;

import java.util.Arrays;

public class Numbers {

    /**
     * @param num
     * @param place 교체할 자리수 높은 자리수부 0-base
     * @return
     */
    public static int[] replace(int num, int place) {
        int[] digits = new int[4];  //4자리수만 지원?
        for (int i=digits.length-1; i>=0; i--) {
            digits[i] = num % 10;
            num /= 10;
        }
        int[] ints = new int[10];
        for (int i=0; i<10; i++) {
            digits[place] = i;
            int sum = 0;
            for (int j = 0; j < 4; j++) {
                sum = sum * 10 + digits[j];
            }
            ints[i] = sum;
        }
        return ints;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(replace(1000, 0)));
        System.out.println(Arrays.toString(replace(9999, 1)));
        System.out.println(Arrays.toString(replace(2000, 2)));
        System.out.println(Arrays.toString(replace(8828, 3)));

    }
}
