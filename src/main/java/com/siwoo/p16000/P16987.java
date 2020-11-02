package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P16987 {
    private static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static Egg[] EGGS;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(READER.readLine());
        EGGS = new Egg[N];
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(READER.readLine());
            EGGS[i] = new Egg(Integer.parseInt(token.nextToken()), 
                    Integer.parseInt(token.nextToken()));
        }
        int x = go(0);
        System.out.println(x);
    }

    private static int go(int index) {
        if (index == N)
            return Arrays.stream(EGGS).mapToInt(e -> e.hp <= 0? 1: 0).sum();
        if (EGGS[index].hp <= 0) 
            return go(index + 1);
        int x = 0;
        boolean ok = false;
        for (int i=0; i<EGGS.length; i++) {
            if (i == index) continue;
            if (EGGS[i].hp > 0) {
                ok = true;
                EGGS[index].hp -= EGGS[i].weight;
                EGGS[i].hp -= EGGS[index].weight;
                x = Math.max(go(index+1), x);
                EGGS[index].hp += EGGS[i].weight;
                EGGS[i].hp += EGGS[index].weight;
            }
        }
        //return Math.max(x, go(index+1));  칠 계란이 있는데 안치는 경우는 제외해야 한다
        return !ok ? go(index+1): x;
    }

    private static class Egg {
        private int hp, weight;

        public Egg(int hp, int weight) {
            this.hp = hp;
            this.weight = weight;
        }
    }
}
