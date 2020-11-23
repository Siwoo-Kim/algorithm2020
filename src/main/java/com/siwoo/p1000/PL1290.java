package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

@Used(algorithm = Algorithm.LINKED_LIST)
public class PL1290 {
    
      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }
     
    class Solution {
        public int getDecimalValue(ListNode head) {
            return pre(head, 0);
        }

        private int pre(ListNode head, int bit) {
            if (head == null) return bit;
            int x = bit << 1 | head.val;
            return pre(head.next, x);
        }
    }
}
