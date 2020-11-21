package com.siwoo.p0000;

public class P0771 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numJewelsInStones("aA", "aAAbbbb"));
    }
    
    private static class Solution {
        private boolean[] jewels = new boolean[128];
        
        public int numJewelsInStones(String J, String S) {
            for (char c: J.toCharArray())
                jewels[c] = true;
            return count(0, S);
        }

        private int count(int index, String S) {
            if (index == S.length()) return 0;
            return count(index+1, S) + (jewels[S.charAt(index)]? 1: 0);
        }
    }
}
