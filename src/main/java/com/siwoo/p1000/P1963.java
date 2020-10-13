package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.*;

/**
 * bfs, 에라스토테네스의 알고리즘 그리고 특정 숫자 교체 알고리즘
 * 자리수 교체시 String 쓰기엔 heap 이 애매할 것 같아 일일히 계산하느라 조금 고생..
 */
@Used(algorithm = Algorithm.BFS)
public class P1963 {
    private static Scanner scanner = new Scanner(System.in);
    private static int T, N, M;
    private static boolean[] primes = new boolean[10000];

    static {
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for (int i=2; i<primes.length; i++) {   //eratosthenes..
            if (primes[i]) {
                for (int j=i+i; j<primes.length; j+=i)
                    primes[j] = false;
            }
        }
    }

    public static void main(String[] args) {
        T = scanner.nextInt();
        while (T != 0) {
            N = scanner.nextInt();
            M = scanner.nextInt();
            Queue<Integer> q = new LinkedList<>();
            q.add(N);
            Map<Integer, Integer> ops = new HashMap<>();
            ops.put(N, 0);
            boolean ok = false;
            while (!q.isEmpty()) {
                int p = q.poll();
                if (p == M) {
                    ok = true;
                    break;
                }
                for (int i=0; i<4; i++) {
                    for (int n: replace(p, i)) {
                        if (n >= 1000 &&
                                primes[n] &&
                                !ops.containsKey(n)) {
                            q.add(n);
                            ops.put(n, ops.get(p) + 1);
                        }
                    }
                }
            }
            if (ok)
                System.out.println(ops.get(M));
            else
                System.out.println("Impossible");
            T--;
        }
    }

    private static int[] replace(int num, int place) {
        int[] digits = new int[4];
        for (int i=digits.length-1; i>=0; i--) {
            digits[i] = num % 10;
            num /= 10;
        }
        int[] ints = new int[10];
        for (int i=0; i<10; i++) {
            digits[place] = i;
            int sum = 0;
            for (int j = 0; j < 4; j++) {
                sum = sum * 10 + digits[j];
            }
            ints[i] = sum;
        }
        return ints;
    }
}
