package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

@Used(algorithm = Algorithm.STACK)
public class P1313 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] r = solution.decompressRLElist(new int[]{1, 1, 2, 3});
        System.out.println(Arrays.toString(r));
    }
    
    private static class Solution {
        public int[] decompressRLElist(int[] nums) { 
            Queue<Integer> q = encode(0, nums, new LinkedList<>());
            int[] answer = new int[q.size()];
            for (int i=0; i<answer.length; i++)
                answer[i] = q.poll();
            return answer;
        }

        private Queue<Integer> encode(int index, int[] nums, Queue<Integer> q) {
            if (index == nums.length) return q;
            int freq = nums[index];
            int val = nums[index+1];
            while (freq != 0) {
                q.add(val);
                freq--;
            }
            return encode(index + 2, nums, q);
        }
    }
}
