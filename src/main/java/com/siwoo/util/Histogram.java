package com.siwoo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *  히스토그램에서 가장 큰 직사각형 r 찾기.
 *  
 *  히스토그램 요소 h 에 대해
 *  특징. 
 *      1. 가장 큰 직사각형의 높이는 h 중 하나일 수 밖에 없다.
 *      2. h 을 높이로 하면서 만들 수 있는 가장 큰 직사각형은
 *      h 보다 작은 첫 왼쪽 막대 left 와 오른쪽 막대 right 을 이용하여 구할 수 있다.
 *  
 *  스택을 이용한 유효한 candidate 처리법.
 *      막대 h 의 차례에서 h 보자 큰 막대들은 더이상 오른쪽으로 확장할 수 없으므로
 *      모두 계산하고 스택을 비어준다. (stack candidate)
 *      
 *  히스토그램의 넓이 구하기.
 *      스택이 비어 있지 않다면 =  (right - left - 1) * h
 *      스택이 비어 있다면 = i
 */
public class Histogram {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    private static class Pair {
        int index;
        long height;

        public Pair(int index, long height) {
            this.index = index;
            this.height = height;
        }
    }
    
    public static long max(int[] histogram) {
        histogram = Arrays.copyOf(histogram, histogram.length+1);
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(0, histogram[0]));
        long max = histogram[0];
        for (int i=1; i<histogram.length; i++) {
            while (!stack.isEmpty() 
                    && stack.peek().height > histogram[i]) {
                Pair p = stack.pop();
                int width = i;
                if (!stack.isEmpty()) {
                    int left = stack.peek().index;
                    int right = i;
                    width = right - left - 1;
                }
                max = java.lang.Math.max(max, width * p.height);
            }
            stack.push(new Pair(i, histogram[i]));
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int N = Integer.parseInt(token.nextToken());
            if (N == 0) return;
            int[] histogram = new int[N];
            for (int i=0; i<N; i++)
                histogram[i] = Integer.parseInt(token.nextToken());
            long answer = max(histogram);
            System.out.println(answer);
        }
    }
}
