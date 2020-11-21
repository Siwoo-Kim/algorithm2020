package com.siwoo.p1000;

import java.util.Arrays;

public class PL1480 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.runningSum(new int[]{1, 2, 3, 4})));
    }
 
    private static class Solution {
        public int[] runningSum(int[] nums) {
            int[] sums = new int[nums.length];
            int sum = 0;
            for (int i=0; i<nums.length; i++)
                sums[i] = (sum += nums[i]);
            return sums;
        }
    }
}