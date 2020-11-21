package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

@Used(algorithm = Algorithm.STACK)
public class PL1614 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int depth = solution.maxDepth("8*((1*(5+6))*(8/6))");
        System.out.println(depth);
    }
    
    private static class Solution {
        
        public int maxDepth(String s) {
            int depth = 0, maxDepth = 0;
            for (int i=0; i<s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(')
                    depth--;
                else if (c == ')') 
                    depth++;
                depth = Math.max(depth, maxDepth);
            }
            return depth;
        }
    }
}
