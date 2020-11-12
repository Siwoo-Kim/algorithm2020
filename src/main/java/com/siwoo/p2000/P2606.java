package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.UNION_FIND)
public class P2606 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    
    private static class UF {
        private int[] components;
        private int[] sizes;
        private int size;

        public UF(int size) {
            this.size = size;
            components = new int[size];
            sizes = new int[size];
            for (int i=1; i<size; i++) {
                components[i] = i;
                sizes[i] = 1;
            }
        }

        public void connect(int v, int w) {
            if (get(v) == get(w)) return;
            v = get(v);
            w = get(w);
            if (sizes[v] < sizes[w]) {
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
            return components[v] = get(p);
        }

        public int size(int v) {
            return sizes[get(v)];
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        M = Integer.parseInt(reader.readLine());
        UF uf = new UF(N+1);
        for (int i=0; i<M; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken());
            uf.connect(v, w);
        }
        System.out.println(uf.size(1)-1);
    }
}
