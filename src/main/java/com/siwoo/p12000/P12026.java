package com.siwoo.p12000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * D[i] = min energy for index i
 * D[i] = min(D[k] + (i - k)^2)
 */
@Used(algorithm = Algorithm.DP)
public class P12026 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Character, Character> map = new HashMap<>();
    private static int N;
    private static Integer[] D;
    private static String A;
    
    static {
        map.put('B', 'J');
        map.put('O', 'B');
        map.put('J', 'O');
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        D = new Integer[N];
        A = reader.readLine();
        System.out.println(go(N-1));
    }

    private static int go(int n) {
        if (n == 0) return 0;
        if (D[n] != null) return D[n];
        int energy = -1;
        for (int i=0; i<n; i++) {
            if (map.get(A.charAt(n)) == A.charAt(i)) {
                int x = go(i);
                if (x == -1) continue;
                if (energy == -1 || energy > (x+(n-i)*(n-i)))
                    energy = x+(n-i)*(n-i);
            }
        }
        return D[n] = energy;
    }
}
