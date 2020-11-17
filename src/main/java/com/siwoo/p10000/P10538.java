package com.siwoo.p10000;

import com.siwoo.util.Marker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

@Marker
public class P10538 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static long MOD = Long.MAX_VALUE;
    private static int BASE = 2, B_HEIGHT, B_WIDTH, S_HEIGHT, S_WIDTH;
    private static int[][] SMALL, BIG;
    
    static long hash(int[] a, int start, int end) {
        long hash = 0;
        for (int i=start; i<end; i++)
            hash = (hash * BASE + a[i]) % MOD;
        return hash;
    }
    
    public int[] toInt(String s) {
        int[] a = new int[s.length()];
        for (int i=0; i<s.length(); i++)
            a[i] = s.charAt(i) == '0'? 1: 0;
        return a;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        S_HEIGHT = Integer.parseInt(token.nextToken());
        S_WIDTH = Integer.parseInt(token.nextToken());
        B_HEIGHT = Integer.parseInt(token.nextToken());
        B_WIDTH = Integer.parseInt(token.nextToken());
        SMALL = new int[S_HEIGHT][S_WIDTH];
        BIG = new int[B_HEIGHT][B_WIDTH];
        for (int i=0; i<S_HEIGHT; i++)
            SMALL[i] = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(s -> "o".equals(s) ? 1: 0)
                    .toArray();
        for (int i=0; i<B_HEIGHT; i++)
            BIG[i] = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(s -> "o".equals(s) ? 1: 0)
                    .toArray();
        long colBase = 1;
        for (int i=0; i<S_WIDTH-1; i++)
            colBase = (BASE * colBase) % MOD;   //2^0 ~ 2^(WIDTH-1)
        
        long rowHash = (colBase * BASE) & MOD;  //hash per row
        long rowBase = 1;
        for (int i=0; i<S_HEIGHT-1; i++)        //2^S_WIDTH*0 .. 2^S_WIDTH*1 .. 2^S_WIDTH*2 .. 2*S_WIDTH*(S_HEIGHT-1)
            rowBase = (BASE * rowHash) % MOD;
        
        long hash = 0;
        for (int i=0; i<S_HEIGHT; i++)
            hash = (hash * rowBase + hash(SMALL[i], 0, S_WIDTH)) % MOD;
        long[][] HASH_COL = new long[B_HEIGHT][B_WIDTH],    //hash for i..i+S_WIDTH-1
                HASH_RECT =  new long[B_HEIGHT][B_WIDTH];   //hash for rectangle (S_WIDTH * S_HEIGHT) start with (i, j)  
        for (int i=0; i<B_HEIGHT; i++) {
            HASH_COL[i][0] = hash(BIG[i], 0, S_WIDTH);
            for (int j=1; j<B_WIDTH-S_WIDTH; j++) {
                HASH_COL[i][j] = HASH_COL[i][j-1] - (BIG[i][j-1] * colBase) % MOD;  //reuse hash
                HASH_COL[i][j] = (HASH_COL[i][j] + MOD) % MOD;  //not allow negative
                HASH_COL[i][j] = ((HASH_COL[i][j] * BASE) % MOD + BIG[i][j+S_WIDTH-1]) % MOD;
            }
        }
        for (int j=0; j<B_WIDTH-S_WIDTH; j++) {
            HASH_RECT[0][j] = 0;
           for (int i=0; j<S_HEIGHT; j++)
               HASH_RECT[0][j] = (HASH_RECT[0][j] * rowBase + HASH_COL[i][j]) % MOD;
           for (int i=1; j<=B_HEIGHT-S_HEIGHT; j++) {   
               HASH_RECT[i][j] = HASH_RECT[i-1][j] - (HASH_COL[i-1][j] * rowBase) % MOD; //reuse hash
               HASH_RECT[i][j] = (HASH_RECT[i][j] + MOD) % MOD;   //not allow negative
               HASH_RECT[i][j] = ((HASH_RECT[i][j] * rowBase) % MOD + HASH_COL[i+S_HEIGHT-1][j]) % MOD;
           }
        }
        long cnt = 0;
        for (int i=0; i<B_HEIGHT-S_HEIGHT; i++)
            for (int j=0; j<B_WIDTH-S_WIDTH; j++)
                if (hash == HASH_RECT[i][j])
                    cnt++;
        System.out.println(cnt);
    }
}
