package com.siwoo.util;

/**
 * DP (Dynamic Programming)
 *  - 문제를 쪼개 작은 문제를 풀어 전체 문제를 푸는 알고리즘.
 *  - 문제를 구현하기전 점화식으로 풀이해야 됨.
 *
 * 특징
 *  1.  optimized substructure (최적해 구조)
 *      * 작은 문제를 풀어 큰 문제를 풀 수 있는 구조.
 *      즉, 작은 문제를 집계하여 큰 문제의 해가 정답임을 증명해야 한다.
 *
 *  2.  overlapping subproblem (중복된 부분 문제)
 *      쪼갠 문제가 중복되지 않으면, 성능 향상을 볼 수 없다.
 *
 *  BruteForce 푼 문제를 DP 로 풀 수 있을까?
 *      쪼개진 문제 (재귀 하나의 호출) 이 다른 호출에 영향을 끼치지 않는다면 (의존성)
 *      DP 을 활용할 수 있다. -> 퇴사 문
 */
public class DynamicProgramming {
}
