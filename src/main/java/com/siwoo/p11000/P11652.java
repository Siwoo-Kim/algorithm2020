package com.siwoo.p11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class P11652 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static BigInteger[] A;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = new BigInteger[N];
        for (int i=0; i<N; i++)
            A[i] = new BigInteger(reader.readLine());
        shuffle(A);
        Arrays.sort(A);
        int max = 1, cnt = 1;
        BigInteger found = A[0];
        for (int i=1; i<N; i++) {
            if (A[i-1].equals(A[i]))
                cnt++;
            else {
                if (max < cnt) {
                    found = A[i-1];
                    max = cnt;
                }
                cnt = 1;
            }
        }
        if (max < cnt) {
            found = A[N-1];
            max = cnt;
        }
        System.out.println(found);
    }

    private static void shuffle(BigInteger[] A) {
        Random random = new Random();
        for (int i=A.length-1; i>0; i--) {
            int x = random.nextInt(i+1);
            BigInteger t = A[i];
            A[i] = A[x];
            A[x] = t;
        }
    }

}
