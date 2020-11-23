package com.siwoo.p0000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

@Used(algorithm = Algorithm.BINARY_SEARCH)
public class PL938 {
    
    private static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
    }

    private static class Solution {
        public int rangeSumBST(TreeNode root, int low, int high) {
            return post(root, low, high);
        }

        private int post(TreeNode root, int low, int high) {
            if (root == null) return 0;
            int x = 0;
            if (root.val > low)
                x += post(root.left, low, high);
            if (root.val < high)
                x += post(root.right, low, high);
            return x + (root.val >= low && root.val <= high ? root.val: 0); 
        }
    }
}
