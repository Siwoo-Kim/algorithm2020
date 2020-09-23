package com.siwoo.util;

import java.util.Arrays;

/**
 * 소수 (Prime)
 *  약수가 1과 자신 밖에 없는 수.
 *
 *  특정 수 N 이 소수인지 아닐지 구할때 루트 N까지 비교법 이용.
 *  특정 범위 ~N 의 소수를 구할 때 에라토스테네스의 체 이용.
 */
public class Prime {

    /**
     * N 이 소수가 아니라면,
     * 2 보다 크거나 같고, sqrt(N) 보다 작거나 같은 자연수로 나누어 떨어지면 안된다.
     * ex) 1, 2, 4, 8, 16, 32
     *       짝을 이룸. (1 - 32, 2 - 16, 4 - 8)
     *       32 의 루트는 가장 중간 지점.
     *       쌍을 이루는 작은 부분 집합에 대해서만 검사.
     */
    public boolean isPrime(int n) {
        if (n < 2)  return false;
        for (int i=2; i * i <= n;   // i <= sqrt(N)
                                    // == pow(i, 2) * pow(sqrt(N), 2)
                                    // == i * i < N
             i++)
            if (n % i == 0)
                return false;
        return true;
    }

    /**
     * 에라토스테네스의 체. (Sieve of Eratosthenes
     * 2 부터 N 까지 순회하며, primes[i] 가 true 인 수 (소수) 중
     * 그 배수를 false 로 설정.
     * i의 배수를 찾을 때, i*i 까지는 이미 순회했됬음 보장된다.
     */
    public static boolean[] primes(int n) {
        boolean[] primes = new boolean[n+1];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for (int i=2; i*i<=n; i++) {
            if (!primes[i]) continue;
            for (int j=i+i; i*j<=n; j++)    // i*i 여도 되지만 overflow 를 피하기 위
                primes[i*j] = false;
        }
        return primes;
    }

    public static void main(String[] args) {
        boolean[] primes = primes(100);
        for (int i=0; i<primes.length; i++)
            System.out.printf("isPrime? %d %s%n", i, primes[i]? "true": "false");
    }
}
