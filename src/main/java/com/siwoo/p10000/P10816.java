package com.siwoo.p10000;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P10816 {
    private static Map<String, Integer> map = new HashMap<>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        StringTokenizer token = new StringTokenizer(reader.readLine());
        for (int i=0; i<N; i++) {
            String key = token.nextToken();
            map.putIfAbsent(key, 0);
            map.put(key, map.get(key) + 1);
        }
        M = Integer.parseInt(reader.readLine());
        token = new StringTokenizer(reader.readLine());
        for (int i=0; i<M; i++) {
            String key = token.nextToken();
            if (map.containsKey(key))
                writer.write(map.get(key) + " ");
            else
                writer.write("0 ");
        }
        writer.flush();
    }
}
