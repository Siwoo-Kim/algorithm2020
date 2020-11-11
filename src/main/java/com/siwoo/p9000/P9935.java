package com.siwoo.p9000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 알고리즘.
 *  문자열 S 폭발 문자열 E 의 특징
 *  
 *      1. S(i) 가 만약 E(stack.pop.index+1) (연속된 폭발문자열) 혹은 E(0) (새로운 폭발문자열) 이라면
 *          해당 S(i) 은 candidate 이다.
 *      2. 위의 경우라면 지금까지 스택에 들어온 candidate 들은 절대 답이 될 수 없다.
 *      
 *  = O(N)          
 */
@Used(algorithm = Algorithm.STACK)
public class P9935 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String FLURA;
    private static String S;
    
    public static void main(String[] args) throws IOException {
        S = reader.readLine();
        FLURA = reader.readLine();
        Stack<Tuple> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<S.length(); i++) {
            char c = S.charAt(i);
            int index = stack.isEmpty()? 0: stack.peek().len;
            if (FLURA.charAt(index) == c || FLURA.charAt(0) == c) {
                if (stack.isEmpty() || FLURA.charAt(index) != c)
                    stack.push(new Tuple(i, 1));
                else
                    stack.push(new Tuple(i, stack.peek().len+1));
                if (stack.peek().len == FLURA.length()) {
                    int end = FLURA.length();
                    for (int j=0; j<end; j++)
                        stack.pop();
                }
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(c);
                while (!stack.isEmpty())
                    sb2.append(S.charAt(stack.pop().index));
                sb.append(sb2.reverse());
            }
        }
        StringBuilder sb2 = new StringBuilder();
        while (!stack.isEmpty())
            sb2.append(S.charAt(stack.pop().index));
        sb.append(sb2.reverse());
        
        if (sb.length() == 0)
            System.out.println("FRULA");
        else {
            System.out.println(sb);
        }
    }

    private static class Tuple {
        final int index, len;

        private Tuple(int index, int len) {
            this.index = index;
            this.len = len;
        }
    }
    
}
