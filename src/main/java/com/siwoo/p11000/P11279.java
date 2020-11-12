package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Used(algorithm = Algorithm.HEAP)
public class P11279 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    
    private static class Heap {
        private int[] heap = new int[100001];
        private int size;
        
        public void push(int v) {
            heap[++size] = v;
            swim(size);
        }

        private void swim(int v) {
            while (v > 1 && heap[v] > heap[v/2]) {
                swap(v, v/2);
                v = v/2;
            }
        }

        public int poll() {
            if (size == 0) return 0;
            int min = heap[1];
            swap(1, size--);
            heap[size+1] = 0;
            sink(1);
            return min;
        }

        private void sink(int v) {
            while ((v << 1) <= size) {
                int w = v << 1;
                if (w < size && heap[w] < heap[w+1])
                    w++;
                if (heap[v] >= heap[w]) break;
                swap(v, w);
                v = w;
            }
        }

        private void swap(int v, int w) {
            int t = heap[v];
            heap[v] = heap[w];
            heap[w] = t;
        }
    }
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        Heap heap = new Heap();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            int v  = Integer.parseInt(reader.readLine());
            if (v == 0)
                sb.append(heap.poll()).append("\n");
            else
                heap.push(v);
        }
        System.out.println(sb.toString());
    }
}
