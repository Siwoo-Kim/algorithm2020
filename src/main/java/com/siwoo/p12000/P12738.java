package com.siwoo.p12000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 그리드의 기준을 새로 추가할 원소 A[i] 요소에 대해서 가장 적절한 요소 x 와 교체해준다.
 *  1. A[i] 가 마지막 원소 x 보다 큰 경우 추가한다. (lis 을 구해야 하므로)
 *  2. A[i] 가 지금까지 구한 lis 에 존재하지 않다면 A[i] 와 ceiling(A[i]) 원소와 교체한다.
 *
 */
@Used(algorithm = Algorithm.GREEDY)
public class P12738 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i=0; i<N; i++) {
            if (treeSet.isEmpty())
                treeSet.add(A[i]);
            else if (treeSet.last() < A[i])
                treeSet.add(A[i]);
            else if (!treeSet.contains(A[i])) {
                int ceil = treeSet.ceiling(A[i]);
                treeSet.remove(ceil);
                treeSet.add(A[i]);
            }
        }
        System.out.println(treeSet.size());
    }
}
