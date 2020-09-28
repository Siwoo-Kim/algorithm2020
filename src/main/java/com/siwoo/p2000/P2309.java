package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.IntStream;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P2309 {
    private static int MAX = 9, SUM;
    private static int[] S = new int[MAX];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i=0; i<MAX; i++)
            S[i] = scanner.nextInt();
        SUM = Arrays.stream(S).sum();
        select(0, new Stack<>());
    }

    private static boolean select(int index, Stack<Integer> select) {
        if (select.size() == 2) {
            int selectSum = select.stream().mapToInt(i -> S[i]).sum();
            boolean result = (SUM - selectSum) == 100;
            if (result) {
                IntStream.range(0, MAX)
                        .filter(i -> !select.contains(i))
                        .map(i -> S[i])
                        .sorted()
                        .forEach(System.out::println);
            }
            return result;
        }
        if (index == MAX) return false;
        if (select.size() > 2) return false;
        select.push(index);
        if (select(index+1, select))
            return true;
        select.pop();
        return select(index+1, select);
    }
}
