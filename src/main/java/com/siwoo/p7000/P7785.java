package com.siwoo.p7000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P7785 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        Set<String> logs = new HashSet<>();
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            String p = token.nextToken();
            String state = token.nextToken();
            if (state.equals("enter"))
                logs.add(p);
            else
                logs.remove(p);
        }
        StringBuilder sb = new StringBuilder();
        logs.stream().sorted(Comparator.reverseOrder()).forEach(p -> sb.append(p).append("\n"));
        System.out.println(sb);
    }
}
