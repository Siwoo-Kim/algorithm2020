package com.siwoo.p1000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P1431 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.kidsWithCandies(new int[]{2, 3, 5, 1, 3}, 3));
        
    }
    
    private static class Solution {
        public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
            List<Boolean> result = new ArrayList<>();
            int max = Arrays.stream(candies).max().getAsInt();
            for (int i=0; i<candies.length; i++)
                result.add(candies[i]+extraCandies >= max);
            return result;
        }
    }
}
