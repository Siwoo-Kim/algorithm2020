package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Used(algorithm = Algorithm.TWO_POINTER)
public class P1644 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, MAX = 4000000;
    private static boolean[] PRIMES = new boolean[MAX+1];
    
    static {
        Arrays.fill(PRIMES, true);
        PRIMES[0] = PRIMES[1] = false;
        for (int i=2; i<=MAX; i++) {
            if (PRIMES[i]) {
                for (int j=i+i; j<=MAX; j+=i) 
                    PRIMES[j] = false;
            }
        }
    }

    public static void main(String[] args) {
        N = scanner.nextInt();
        List<Integer> primes = new ArrayList<>();
        for (int i=0; i<=N; i++) {
            if (PRIMES[i])
               primes.add(i); 
        }
        
        int l = 0, r = 0, cnt = 0, sum = 0;
        while (l < primes.size()) {
            if (sum < N && r < primes.size())
                sum += primes.get(r++);
            else {
                if (sum == N)
                    cnt++;
                sum -= primes.get(l++);
            }
        }
        System.out.println(cnt);
    }
}
