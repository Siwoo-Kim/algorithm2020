package com.siwoo.p1000;

public class PL1486 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.xorOperation(10, 5));
    }
    
    private static class Solution {
        public int xorOperation(int n, int start) {
            int bit = start;
            for (int i=1; i<n; i++) {
                int x = start + 2 * i;
                bit ^= x;
            }
            return bit;
        }
    }
}
