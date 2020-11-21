package com.siwoo.p1000;

import java.util.Arrays;

public class PL1512 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numIdenticalPairs(new int[]{1, 1, 1, 1}));
    }

    private static class Solution {
        private static final int[] D = new int[101];
        private static int cnt;
        
        public int numIdenticalPairs(int[] nums) {
            goodPair(0, nums, D);
            return cnt;
        }

        private void goodPair(int index, int[] nums, int[] D) {
            if (index == nums.length) return;
            D[nums[index]]++;
            goodPair(index+1, nums, D);
            if (D[nums[index]] >= 1) {
                cnt += D[nums[index]] - 1;
                D[nums[index]]--;
            }
        }
    }
}