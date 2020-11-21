package com.siwoo.p1000;

public class PL1342 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numberOfSteps(123));
    }
    
    private static class Solution {
        public int numberOfSteps (int num) {
            int cnt = 0;
            while (num != 0) {
                if (num % 2 == 0)
                    num /= 2;
                else
                    num--;
                cnt++;
            }
            return cnt;
        }
    }
}
