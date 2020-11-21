package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Arrays;
import java.util.Stack;

@Used(algorithm = Algorithm.STACK)
public class PL1389 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] r = solution.createTargetArray(new int[]{4, 2, 4, 3, 2}, new int[]{0, 0, 1, 3, 1});
        System.out.println(Arrays.toString(r));
    }

    private static class Solution {
        private static class Pair {
            private int index, value;

            public Pair(int value, int index) {
                this.value = value;
                this.index = index;
            }
        }

        public int[] createTargetArray(int[] nums, int[] index) {
            Stack<Pair> stack = new Stack<>();
            for (int i=0; i<nums.length; i++) {
                Pair pair = new Pair(nums[i], index[i]);
                if (stack.isEmpty())
                    stack.push(pair);
                else if (stack.peek().index >= pair.index) {
                    Stack<Pair> backup = new Stack<>();
                    while (!stack.isEmpty() && stack.peek().index >= pair.index)
                        backup.push(stack.pop());
                    stack.push(pair);
                    while (!backup.isEmpty()) {
                        Pair p = backup.pop();
                        p.index++;
                        stack.push(p);
                    }
                } else {
                    stack.push(pair);
                }
            }
            int[] answer = new int[nums.length];
            while (!stack.isEmpty()) {
                Pair p = stack.pop();
                answer[p.index] = p.value;
            }
            return answer;
        }
    }
}
