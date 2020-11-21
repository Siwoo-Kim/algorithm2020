package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class PL1365 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] result = solution.smallerNumbersThanCurrent(new int[]{8, 1, 2, 2, 3});
        System.out.println(Arrays.toString(result));
    }
    
    private static class Solution {
        
        public int[] smallerNumbersThanCurrent(int[] nums) {
            Stack[] count = new Stack[101];
            for (int i=0; i<nums.length; i++) {
                if (count[nums[i]] == null)
                    count[nums[i]] = new Stack();
                count[nums[i]].push(i);
            }
            int sum = 0;
            for (int i=0; i<count.length; i++) {
                if (count[i] == null) continue;
                int x = count[i].size();
                while (!count[i].isEmpty())
                    nums[(int) count[i].pop()] = sum;
                sum += x;
            }
            return nums;
        }
        
    }
}
