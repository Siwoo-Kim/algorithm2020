package com.siwoo.p3000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 알고리즘
 *  현재 막대기를 c, 스택의 마지막 막대기를 t 라 했을 때,
 *  1. 스택이 비어있지 않다면 c 와 t 는 서로를 볼 수 있다.
 *  2. c 가 t 보다 크다면 t 은 더이상 candidate 가 아니다. (이후의 막대기는 t 를 볼 수 없다.)
 *      c 와 t 가 만족하는 동안 스택을 비우며 +1 을 해준다.
 */
@Used(algorithm = Algorithm.STACK)
public class P3015 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        long pairs = 0;
        Stack<Tuple> stack = new Stack<>();
        for (int i=0; i<N; i++) {
            Tuple tuple = new Tuple(1, Integer.parseInt(reader.readLine()));
            while (!stack.isEmpty() && stack.peek().height <= tuple.height) {
                //remove sticks no longer can be candidate
                Tuple top = stack.pop();
                pairs += top.cnt;
                if (tuple.height == top.height)
                    tuple.cnt += top.cnt;
            }
            if (!stack.isEmpty()) //top stick and current stick can see each other?
                pairs++;
            stack.push(tuple);
        }
        System.out.println(pairs);
    }
    
    private static class Tuple {
        private int cnt, height;

        public Tuple(int cnt, int height) {
            this.cnt = cnt;
            this.height = height;
        }
    }
}
