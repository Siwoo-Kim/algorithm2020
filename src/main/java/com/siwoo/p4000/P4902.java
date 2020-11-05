package com.siwoo.p4000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.DnC)
public class P4902 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] D;
    private static int N, MAX;
    
    public static void main(String[] args) throws IOException {
        int tried = 1;
        while (true) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(token.nextToken());
            if (N == 0) break;
            MAX = N * 2 - 1;
            Integer[][] triangle = new Integer[N][MAX];
            D = new int[N][MAX];
            build(1, triangle, token);
            int answer = Integer.MIN_VALUE;
            for (int n=1; n<=N; n++) {
                int left = left(n),
                        size = sizeOf(n);
                boolean up = false;
                for (int i=0; i<size; i++) {
                    Integer x = go(n-1, left + i, up, triangle);
                    if (x != null)
                        answer = Math.max(answer, x);
                    up = !up;
                }
            }
            System.out.printf("%d. %d%n", tried++, answer);
        }
    }
    private static Integer go(int x, int y, boolean flip, Integer[][] triangle) {
        int value = 0, size = 1;
        return goD(y, y, x, value, size, triangle, flip);
    }

    
    private static Integer goD(int left, int right, int row, int value, int size, Integer[][] triangle, boolean up) {
        if (!valid(left, right, row, triangle)) return null;
//        for (int i=0; i<size; i++)    //time exceeds
//            value += triangle[row][left+i];
        int sum = value + (D[row][right] - (left == 0? 0: D[row][left-1]));
        Integer x = null;  
        if (up)
            x = goD(left-1, right+1, row-1, sum, size+2, triangle, up);
        else
            x = goD(left-1, right+1, row+1, sum, size+2, triangle, up);
        return x != null? Math.max(sum, x): sum;
    }
    
    private static boolean valid(int left, int right, int row, Integer[][] t) {
        if (row < 0 || t.length <= row) return false;
        if (left < 0) return false;
        if (right >= t[row].length) return false;
        return t[row][left] != null && t[row][right] != null;
    }
    
    private static int sizeOf(int n) {
        return n * 2 - 1;
    }

    private static int left(int n) {
        return (MAX - sizeOf(n)) / 2;
    }

    private static void build(int size, Integer[][] triangle, StringTokenizer token) {
        if (size > MAX) return;
        int max = N * 2 - 1;
        int row = size / 2;
        int left = (max - size) / 2;
        int cnt = 0;
        for (int i=left; cnt<size; i++, cnt++) {
            triangle[row][i] = Integer.parseInt(token.nextToken());
            D[row][i] = triangle[row][i];
            if (cnt != 0)
                D[row][i] += D[row][i-1];
        }
        build(size+2, triangle, token);
    }
}
