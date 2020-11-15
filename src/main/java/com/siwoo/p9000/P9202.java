package com.siwoo.p9000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P9202 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static char[][] BOGGLE;
    private static Set<String> words;
    private static int N, M;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        words = new String[N];
        for (int i=0; i<N; i++)
            words[i] = reader.readLine();
        reader.readLine();
        M = Integer.parseInt(reader.readLine());
        while (M != 0) {
            BOGGLE = new char[4][4];
             
            reader.readLine();
        }
    }
}
