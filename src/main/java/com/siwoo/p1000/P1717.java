package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.UNION_FIND)
public class P1717 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    
    private static class UF {
        private int[] components;
        private int[] sizes;
        private int size;
        
        UF(int size) {
            this.size = size;
            components = new int[size];
            sizes = new int[size];
            for (int i=0; i<size; i++) {
                components[i] = i;
                sizes[i] = 1;
            }
        }
        
        boolean connected(int v, int w) {
            return get(v) == get(w);
        }
        
        void connect(int v, int w) {
            if (connected(v, w)) return;
            v = get(v);
            w = get(w);
            if (sizes[v] < sizes[w]) {  //rank
                int t = v;
                v = w;
                w = t;
            }
            components[w] = v;
            sizes[v] += sizes[w];
            size--;
        }

        private int get(int v) {
            if (components[v] == v)
                return v;
            int p = components[v];
            return components[v] = get(p);  //zip
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        UF uf = new UF(N+1);
        for (int i=0; i<M; i++) {
            token = new StringTokenizer(reader.readLine());
            int op = Integer.parseInt(token.nextToken()),
                    v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken());
            if (op == 0) 
                uf.connect(v, w);
            else
                System.out.println(uf.connected(v, w) ? "yes": "no");
        }
    }
}
