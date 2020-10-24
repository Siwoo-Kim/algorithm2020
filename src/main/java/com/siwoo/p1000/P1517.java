package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.DnC)
public class P1517 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = new int[N];
        StringTokenizer token = new StringTokenizer(reader.readLine());
        for (int i=0; i<N; i++)
            A[i] = Integer.parseInt(token.nextToken());
//        int cnt = find(0, A);
//        System.out.println(cnt);
        long cnt = divide(0, A.length, A);
        System.out.println(cnt);
    }

    private static long divide(int l, int r, int[] A) {
        if (r - l < 2) return 0;
        int mid = (l + r) / 2;
        long cnt = divide(l, mid, A);
        cnt += divide(mid, r, A);
        return cnt + merge(l, mid, r, A);
    }

    private static long merge(int l, int mid, int r, int[] A) {
        int[] B = new int[r - l];
        int i = l, j = mid;
        long cnt = 0;
        for (int t=0; t<B.length; t++) {
            if (j >= r)
                B[t] = A[i++];
            else if (i >= mid)  // i doesn't move when equals
                B[t] = A[j++];
            else if (A[i] <= A[j])
                B[t] = A[i++];
            else {
                cnt += mid - i;
                B[t] = A[j++];
            }
        }
        for (i=0, j=l; i<B.length; i++, j++)
            A[j] = B[i];
        return cnt;
    }

//    private static int find(int index, int[] a) {
//        if (index == a.length-1) return 0;
//        int cnt = 0;
//        for (int i=index+1; i<a.length; i++) {
//            if (a[index] > a[i])
//                cnt++;
//        }
//        return cnt + find(index + 1, a);
//    }
}
