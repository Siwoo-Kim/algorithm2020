package com.siwoo.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 그리디 알고리즘 Greedy Algorithm
 *  그 순간에 가장 좋다고 생각하는 것을 선택하면서 답을 찾아가는 알고리즘.
 *  그 순간엔 최적해일 수 있지만, 최종적으론 답이 최적이 아닐 수도 있다.
 *
 *  그리디 알고리즘 tip
 *      => 정답 선택의 기준이 가장 중요.
 *
 *  A -> D 로 이동해야할때,
 *  A -> B (10)
 *  A -> C (30)
 *  B -> D (?)
 *  C -> D (?)
 *  라면 그리디는 A -> B -> D 가 최소 경로임을 판단.
 *
 *  그리디는 그 순간의 선택으로 문제의 정답을 구할 수 있음을 증명해야 한다.
 *
 *  정답을 알때, 쉬운 증명
 *      기준의 선택 하나와 문제의 정답의 선택을 바꿔보고,
 *      전체 정답이 변하는지를 관찰한다.
 *
 */
public class Greedy {
    //배수일때만 성립함.
    private static int[] coins = {1, 5, 10, 50, 100, 500, 1000, 5000};

    public Map<Integer, Integer> getChange(int N) {
        Map<Integer, Integer> changes = new HashMap<>();
        Arrays.stream(coins).forEach(c -> changes.put(c, 0));
        int i = coins.length-1;
        while (N != 0) {
            if (coins[i] > N)
                i--;
            else {
                N -= coins[i];
                changes.put(coins[i], changes.get(coins[i]) + 1);
            }
        }
        return changes;
    }

    public static void main(String[] args) {
        Greedy greedy = new Greedy();
        System.out.println(greedy.getChange(7570));
    }
}
