package com.siwoo.p15000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * DP 문제이자 combination 문제.
 * 
 * combination 문제에 있어서, (같은 원소를 이루지만 순서만 다른 집합)
 * 특정 원소를 기준으로 순서를 정해주고 집합을 생성하면 조합 구할 수 있다. 
 * 
 * 1,2,3 의 합으로 N을 만들 수 있는 경우의 수
 * N=4
 * 
 * 1 을 기준으로 집합을 만든다.
 * DP[1] = 1
 * DP[2] = 1+1
 * DP[3] = 1+1+1
 * DP[4] = 1+1+1+1
 * 
 * 2 을 기준으로 집합을 만든다
 * DP[1] = 1
 * DP[2] = 1+1, 2
 * DP[3] = 1+1+1, 2+1
 * DP[4] = 1+1+1+1, 2+1+1, 2+2
 * 
 * 3 을 기준으로 집합을 만든다
 * DP[1] = 1
 * DP[2] = 1+1, 2
 * DP[3] = 1+1+1, 2+1, 3
 * DP[4] = 1+1+1+1, 2+1+1, 2+2, 3+1
 *  
 * 점화
 * DP[i] 가 1,2,3 의 합으로 i 을 만들 수 있는 방법의 수라 했을때
 *  DP[i] = DP[i-1] + DP[i-2] + DP[i-3]
 */
@Used(algorithm = Algorithm.DP)
public class P15989 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N = 10000;
    private static int[] D = new int[N+1];
    
    public static void main(String[] args) throws IOException {
        D[0] = 1;
        for (int i=0; i<=N; i++)
            if (i-1 >= 0)
                D[i] += D[i-1];
        for (int i=0; i<=N; i++)
            if (i-2 >= 0)
                D[i] += D[i-2];
        for (int i=0; i<=N; i++)
            if (i-3 >= 0)
                D[i] += D[i-3];
        int T = Integer.parseInt(reader.readLine());
        for (int i=0; i<T; i++)
            System.out.println(D[Integer.parseInt(reader.readLine())]);
    }
}
