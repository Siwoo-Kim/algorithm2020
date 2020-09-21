package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.*;
import java.util.Stack;

@Used(algorithm = Algorithm.STACK)
public class P1874 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        Stack<Integer> stack = new Stack<>();
        // BufferedWriter 은 내부 버퍼 사이즈 이상이면 자동 flush 하므로
        // 아예 특정 로직에 따라 출력되지 않아야 하는 경우엔 쓰지 말아야 함.
        StringBuilder buffer = new StringBuilder();
        int current = 1;
        for (int i=0; i<N; i++) {
            int e = Integer.parseInt(reader.readLine());
            while (e >= current) {
                stack.push(current++);
                buffer.append("+\n");
            }
            if (stack.peek() != e) {
                System.out.println("NO");
                return;
            } else {
                stack.pop();
                buffer.append("-\n");
            }
        }
        System.out.println(buffer.toString());
    }
}
