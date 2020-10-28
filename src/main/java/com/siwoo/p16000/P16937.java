package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P16937 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int H, W, N;
    private static Sticker[] stickers;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        H = Integer.parseInt(token.nextToken());
        W = Integer.parseInt(token.nextToken());
        token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        stickers = new Sticker[N];
        for (int i=0; i<N; i++)  {
            token = new StringTokenizer(reader.readLine());
            stickers[i] = new Sticker(
                    Integer.parseInt(token.nextToken()),
                    Integer.parseInt(token.nextToken()));
        }
        List<Sticker[]> candidates = nCr(0, N, 2, new Stack<>(), new ArrayList<>());
        int max = 0;
        for (Sticker[] pair: candidates)
            max = Math.max(getArea(pair), max);
        System.out.println(max);
    }

    private static int getArea(Sticker[] pair) {
        int max = 0;
        Sticker s1 = pair[0], s2 = pair[1];
        for (int i=0; i<2; i++) {
            s1 = s1.rotate();
            for (int j=0; j<2; j++) {
                s2 = s2.rotate();
                if (s1.x + s2.x <= H && Math.max(s1.y, s2.y) <= W)
                    max = Math.max(max, s1.area() + s2.area());
                if (Math.max(s1.x, s2.x) <= H && s1.y + s2.y <= W)
                    max = Math.max(max, s1.area() + s2.area());
            }
        }
        return max;
    }

    private static List<Sticker[]> nCr(int index, int N, int R, Stack<Sticker> stack, List<Sticker[]> result) {
        if (stack.size() == R) {
            result.add(stack.toArray(new Sticker[2]));
            return result;
        }
        for (int i=index; i<N; i++) {
            stack.push(stickers[i]);
            nCr(i+1, N, R, stack, result);
            stack.pop();
        }
        return result;
    }

    private static class Sticker {
        private final int x, y;

        private Sticker(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int area() {
            return x * y;
        }

        public Sticker rotate() {
            return new Sticker(y, x);
        }
    }
}
