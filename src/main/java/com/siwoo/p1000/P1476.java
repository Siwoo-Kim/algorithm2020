package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

/**
 * E, S, M (1 ≤ E ≤ 15, 1 ≤ S ≤ 28, 1 ≤ M ≤ 19) 달력에서 특정 E, S, M 이 주어졌을 때, 몇 년도인지 구하여라
 * O(E * S * M) => 7,980
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P1476 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = 1, e = 1, s = 1, m = 1,
                E = scanner.nextInt(),
                S = scanner.nextInt(),
                M = scanner.nextInt();
        while (true) {
            if (e == E && s == S && m == M) {
                System.out.println(year);
                break;
            }
            year++;
            e++;
            s++;
            m++;
            if (e > 15) e = 1;
            if (s > 28) s = 1;
            if (m > 19) m = 1;
        }
    }
}
