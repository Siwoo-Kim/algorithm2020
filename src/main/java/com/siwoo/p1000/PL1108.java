package com.siwoo.p1000;

public class PL1108 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.defangIPaddr("1.1.1.1"));
        System.out.println(solution.defangIPaddr("255.100.50.0"));
    };
    
    private static class Solution {
        
        public String defangIPaddr(String address) {
            return defangIPaddr(0, new StringBuilder(), address);
        }

        private String defangIPaddr(int index, StringBuilder sb, String address) {
            if (index == address.length()) return sb.toString();
            if (address.charAt(index) == '.')
                sb.append("[.]");
            else
                sb.append(address.charAt(index));
            return defangIPaddr(index+1, sb, address);
        }

    }
}
