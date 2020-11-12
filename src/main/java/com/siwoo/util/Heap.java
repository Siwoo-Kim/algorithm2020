package com.siwoo.util;

import java.util.NoSuchElementException;

/**
 * 완전 이진 트리 힙. Complete binary tree
 * 
 *  1. 자식이 부모보다 작은 것을 만족하는 트리.
 *  2. 최소값 혹은 최대값을 찾을 때 O(logN) 을 보장.
 *  3. 출력의 끝을 알 수 없는 데이터에 대한 정렬에도 사용 가능.
 *  
 *  불변식
 *      n (부모)은 n*2, n*2+1 (자식) 보다 커야 한다.
 *      위의 불변식을 만족하지 않은 경우 heap 의 상태를 바꾸는 일을 heapify 라 한다.
 *      
 *      heapify
 *          1. swim (n -> n/2) 을 수행하며 상태를 고쳐준다.
 *          2. sink (n -> n*2 or n*2+1) 을 수행하며 상태를 고쳐준다.
 *      
 */
public class Heap {
    private char[] heap = new char[10];
    private int size;

    public void push(char c) {
        heap[++size] = c;
        swim(size);
        if (size+1 == heap.length) {
            char[] array = new char[size<<1];
            System.arraycopy(heap, 0, array, 0, size+1);
        }
        assert isHeap(1);
    }

    private void swim(int v) {
        while (v > 1 && heap[v] > heap[v/2]) {
            swap(v, v/2);
            v = v/2;
        }
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
    
    public char poll() {
        if (isEmpty()) throw new NoSuchElementException();
        char min = heap[1];
        swap(1, size);
        heap[size--] = 0;
        sink(1);
        assert isHeap(1);
        return min;
    }

    private boolean isHeap(int v) {
        if (v > size) return true;
        int left = v << 1;
        int right = left + 1;
        if (left <= size && heap[v] < heap[left]) return false;
        if (right <= size && heap[v] < heap[right]) return false;
        return isHeap(left) && isHeap(right);
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private void swap(int v, int w) {
        char t = heap[v];
        heap[v] = heap[w];
        heap[w] = t;
    }

    public static void main(String[] args) {
        String s = "P R I O * R * * I * T * Y * * * Q U E * * * U * E";
        Heap heap = new Heap();
        for (char c: s.toCharArray()) {
            if (c == ' ') continue;
            if (c == '*')
                System.out.print(heap.poll() + " ");
            else
                heap.push(c);
        }
    }
}
