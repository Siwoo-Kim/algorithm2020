package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.PERMUTATION)
public class P16943 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int B;
    private static int[] P;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        String line =  token.nextToken();
        P = new int[line.length()];
        for (int i=0; i<line.length(); i++)
            P[i] = line.charAt(i) - '0';
        Arrays.sort(P);
        B = Integer.parseInt(token.nextToken());

        int C = -1;
        do {
            if (P[0] == 0) continue;
            int x = 0;
            for (int i=0; i<P.length; i++)
                x = x * 10 + P[i];
            if (x < B) {
                if (C == -1 || C < x)
                    C = x;
            } else {
                break;
            }
        } while (nextPermutation(P));
        System.out.println(C);
    }

    private static boolean nextPermutation(int[] P) {
        int i = P.length-1;
        while (i > 0 && P[i-1] >= P[i]) i--;
        if (i == 0) return false;
        int j = P.length-1;
        while (j > 0 && P[i-1] >= P[j]) j--;
        swap(i-1, j, P);
        j = P.length-1;
        while (i < j)
            swap(i++, j--, P);
        return true;
    }

    private static void swap(int i, int j, int[] P) {
        int t = P[i];
        P[i] = P[j];
        P[j] = t;
    }
}
