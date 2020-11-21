package com.siwoo.p1000;

public class PL1528 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.restoreString("aiohn", new int[]{3, 1, 4, 2, 0}));
    }
    
    private static class Solution {
        public String restoreString(String s, int[] indices) {
            char[] chars = new char[s.length()];
            for (int i=0; i<indices.length; i++) 
                chars[indices[i]] = s.charAt(i);
            return new String(chars);
        }
    }
}
