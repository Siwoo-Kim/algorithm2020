package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class PL1281 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.subtractProductAndSum(4421));
    }
    
    private static class Solution {
        private int product = 1, sum = 0;
        
        public int subtractProductAndSum(int n) {
            calc(n);
            return product - sum;
        }

        private void calc(int n) {
            if (n == 0) return;
            int x = n % 10;
            product *= x;
            sum += x;
            calc(n / 10);
        }
    }
}
