package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

@Used(algorithm = Algorithm.STACK)
public class PL1221 {
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        int x = solution.balancedStringSplit("RLRRLLRLRL");
        System.out.println(x);
    }
    
    private static class Solution {
        public int balancedStringSplit(String s) {
            int depth = 0, cnt = 0;
            for (int i=0; i<s.length(); i++) {
                if (s.charAt(i) == 'L')
                    depth++;
                else
                    depth--;
                if (depth == 0)
                    cnt++;
            } 
            return cnt;
        }
    }
}
