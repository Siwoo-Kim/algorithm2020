package com.siwoo.p3000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

@Used(algorithm = Algorithm.STACK)
public class P3111 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String S, A;

    public static void main(String[] args) throws IOException {
        A = reader.readLine();
        S = reader.readLine();
        int i = 0, j = S.length()-1;
        Stack<Character> left = new Stack<>(),
                right = new Stack<>();
        boolean r = false;
        while (i <= j) {
            Stack<Character> stack;
            if (!r) {
                left.add(S.charAt(i++));
                stack = left;
            } else {
                right.add(S.charAt(j--));
                stack = right;
            }
            if (check(stack, A, r))
                r = !r;
        }
        while (!left.isEmpty()) {
            right.push(left.pop());
            check(right, A, true);
        }
        StringBuilder sb = new StringBuilder();
        while (!right.isEmpty())
            sb.append(right.pop());
        System.out.println(sb);
    }
    
    private static boolean check(Stack<Character> stack, String A, boolean reverse) {
        if (stack.size() < A.length()) return false;
        int start = stack.size() - A.length();
        boolean ok = true;
        for (int i=0;i<A.length(); i++) {
            char sc = stack.get(i+start);
            char sa = A.charAt(reverse? A.length()-1-i: i);
            if (sc != sa) {
                ok = false;
                break;
            }
        }
        if (!ok) return false;
        int i = A.length();
        while (i != 0) {
            stack.pop();
            i--;
        }
        return true;
    }

}
