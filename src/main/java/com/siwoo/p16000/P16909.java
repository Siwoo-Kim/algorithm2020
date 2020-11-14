package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * N 개의 카드 A 에서 모든 i..j 구간의 최대값-최솟값의 합 S 구하기.
 *  
 *  S = 모든 i..j 구간의 최대값 - 모든 i..j 구간의 최솟값 
 *    = sum (A[i] 의 최대 구간 * A[i] - A[i] 의 최소 구간 * A[i])
 *    
 *  len (구간의 갯수) = N*(N+1) / 2 - (i 을 포함하지 않은 왼쪽 구간) - (i 을 포함하지 않은 오른쪽 구간)
 * 
 *  A[i] 의 구간의 범위 구하기
 *      오른쪽 방향, 왼쪽 방향으로 각각 구하기.
 *      스택을 이용한 candidate 정하기.
 *          ex) 오른쪽 방향으로 A[i] 에 대한 최대 구간을 구한다고 하자.
 *          => A[i] 가 스택의 top 보다 크다면 스택의 top 은 그 이상 절대 최대 구간을 늘릴 수 없다.
 *          반대로 A[i] 가 top 보다 작다면 스택안의 candidate 은 오른쪽으로 구간을 더 넓힐 수 있다.
 *          
 *          
 */
@Used(algorithm = Algorithm.STACK)
public class P16909 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] A, RIGHT_G, RIGHT_L, LEFT_G, LEFT_L;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = new int[N+1];
        RIGHT_G = new int[N+1];
        RIGHT_L = new int[N+1];
        LEFT_G = new int[N+1];
        LEFT_L = new int[N+1];
        StringTokenizer token = new StringTokenizer(reader.readLine());
        for (int i=0; i<N; i++)
            A[i+1] = Integer.parseInt(token.nextToken());
        Stack<Integer> max = new Stack<>(),
                min = new Stack<>();
        // =>
        Arrays.fill(RIGHT_G, N+1);
        Arrays.fill(RIGHT_L, N+1);
        for (int i=1; i<=N; i++) {
            while (!max.isEmpty() && A[i] > A[max.peek()])
                RIGHT_G[max.pop()] = i;
            max.push(i);
            while (!min.isEmpty() && A[i] < A[min.peek()])
                RIGHT_L[min.pop()] = i;
            min.push(i);
        }
        max = new Stack<>();
        min = new Stack<>();
        Arrays.fill(LEFT_G, 0);
        Arrays.fill(LEFT_L, 0);
        // <=
        for (int i=N; i>=1; i--) {
            while (!max.isEmpty() && A[i] >= A[max.peek()])
                LEFT_G[max.pop()] = i;
            max.push(i);
            while (!min.isEmpty() && A[i] <= A[min.peek()])
                LEFT_L[min.pop()] = i;
            min.push(i);
        }
        long answer = 0;
        //max sections
        for (int i=1; i<=N; i++) {
            int left = LEFT_G[i] + 1;
            int right = RIGHT_G[i] - 1;
            long len = right - left + 1;
            answer += (numSections(len) - numSections(i-left) - numSections(right-i)) * A[i];
        }
        //min sections
        for (int i=1; i<=N; i++) {
            int left = LEFT_L[i] + 1;
            int right = RIGHT_L[i] - 1;
            long len = right - left + 1;
            answer -= (numSections(len) - numSections(i-left) - numSections(right-i)) * A[i];
        }
        System.out.println(answer);
    }

    private static long numSections(long n) {
        return n * (n + 1) / 2;
    }
}
























