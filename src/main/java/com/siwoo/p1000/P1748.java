package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

/**
 * 1 의 자리수 -> 갯수 (10-1)  * 1
 * 2 의 자리수 -> 갯수 (100-1 - (10+1)) * 2
 * 3 의 자리수 -> 갯수 (1000-1 - (100+1)) * 3
 *
 * O(length(N))
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P1748 {
    private static int N;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        int len = 1, start=1;
        long cnt = 0;
        while (start <= N) {
            int last = start * 10 -1;
            if (last > N)
                last = N;
            cnt += (last - start + 1) * len;
            start *= 10;
            len++;
        }
        System.out.println(cnt);
    }
}
