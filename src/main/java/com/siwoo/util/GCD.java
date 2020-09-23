package com.siwoo.util;

/**
 * 최대공약수 (GCD)
 * 두 수 A, B 의 공통된 약수 중 가장 큰 정수.
 *
 * 최소공배수 (LCM)
 * 두 수 A, B 의 공통된 배수 중 가장 작은 정수.
 *  A * B = GCD * LCM
 *  LCM = A * B / GCD
 *
 */
public class GCD {

    /**
     *  유클리드 호제법 (euclidean algorithm)
     *  gcd(a, b) = gcd(b, gcd(b, a % b)),
     *  이때 b == 0 이면 a 가 최대 공약수
     */
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }

    /**
     * a * b = gcd * lcm
     * lcm = a * b / gcd
     */
    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    public static void main(String[] args) {
        System.out.println(gcd(24, 16));
        System.out.println(lcm(24, 16));
    }
}
