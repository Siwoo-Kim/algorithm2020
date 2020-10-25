package com.siwoo.p10000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.StringTokenizer;

public class P10814 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static People[] people;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        people = new People[N];
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            people[i] = new People(Integer.parseInt(token.nextToken()), i, token.nextToken());
        }
        shuffle(people);
        sort(0, N, people);
        StringBuilder sb = new StringBuilder();
        for (People p: people)
            sb.append(p).append("\n");
        System.out.println(sb.toString());
    }

    private static void sort(int left, int right, People[] P) {
        if (right - left <= 1) return;
        int i = left, j = right;
        People e = P[i];
        while (i < j) {
            while (i < j && P[--j].compareTo(e) > 0) ;
            while (i < j && P[++i].compareTo(e) < 0) ;
            if (i < j)
                swap(i, j, P);
        }
        swap(left, j, P);
        int pivot = j;
        sort(left, pivot, P);
        sort(pivot+1, right, P);
    }

    private static void shuffle(People[] people) {
        Random random = new Random();
        for (int i=people.length-1; i>0; i--) {
            int x = random.nextInt(i + 1);
            swap(x, i, people);
        }
    }

    private static void swap(int i, int j, People[] people) {
        People p = people[i];
        people[i] = people[j];
        people[j] = p;
    }

    private static class People implements Comparable<People> {
        private static Comparator<People> C = Comparator.comparing(People::getAge).thenComparing(People::getIndex);
        int age, index;
        String name;

        public People(int age, int index, String name) {
            this.age = age;
            this.index = index;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(People o) {
            return C.compare(this, o);
        }

        @Override
        public String toString() {
            return age + " " + name;
        }
    }
}
