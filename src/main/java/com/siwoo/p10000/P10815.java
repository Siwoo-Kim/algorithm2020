package com.siwoo.p10000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.DnC)
public class P10815 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        A = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        M = Integer.parseInt(reader.readLine());
        Arrays.sort(A);
        StringTokenizer token = new StringTokenizer(reader.readLine());
        for (int i=0; i<M; i++) {
            int x = -1;
            if (N > 0)
                x = binarySearch(0, N-1, A,
                    Integer.parseInt(token.nextToken()));
            writer.write( x+ " ");
        }
        writer.flush();
    }

    private static int binarySearch(int l, int r, int[] a, int x) {
        if (l > r) return 0;
        int mid = (r + l) / 2;
        int compare = Integer.compare(x, a[mid]);
        if (compare == 0)
            return 1;
        else if (compare > 0)
            return binarySearch(mid+1, r, a, x);
        else
            return binarySearch(l, mid-1, a, x);
    }
}
