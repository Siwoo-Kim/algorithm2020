package com.siwoo.p2000;

import java.io.*;
import java.util.Random;

public class P2751 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = new int[N];
        for (int i=0; i<N; i++)
            A[i] = Integer.parseInt(reader.readLine());
        shuffle(A);
        sort(0, A.length, A);
        for (int e: A)
            writer.write(e + "\n");
        writer.flush();
    }

    private static void sort(int left, int right, int[] A) {
        if (right - left <= 1) return;
        int i = left, j = right, e = A[left];
        while (i < j) {
            while (i < j && A[--j] > e) ;
            while (i < j && A[++i] < e) ;
            if (i < j)
                swap(i, j, A);
        }
        swap(j, left, A);
        int pivot = j;
        sort(left, pivot, A);
        sort(pivot+1, right, A);
    }

    private static void shuffle(int[] A) {
        Random random = new Random();
        for (int i=0; i<A.length; i++) {
            int x = random.nextInt(A.length);
            swap(i, x, A);
        }
    }

    private static void swap(int i, int j, int[] A) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
}
