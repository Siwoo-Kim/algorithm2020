package com.siwoo.p1000;

import java.util.Arrays;

public class PL1470 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.shuffle(new int[]{2, 5, 1, 3, 4, 7}, 3)));
    }
    
    private static class Solution {
        
        public int[] shuffle(int[] nums, int n) {
            return shuffle(nums, new int[nums.length], 0, 0, n, nums.length);
        }

        private int[] shuffle(int[] from, int[] dest, int index, int left, int right, int N) {
            if (right == N) return dest;
            dest[index++] = from[left];
            dest[index++] = from[right];
            return shuffle(from, dest, index, left+1, right+1, N);
        }
    }
}
