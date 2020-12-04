package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.*;

@Used(algorithm = Algorithm.BFS)
public class P1525 {
    private static Scanner scanner = new Scanner(System.in);
    private static char[][] R = new char[3][3];
    private static int[][] D = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static String answer = "123456780";
    
    public static void main(String[] args) {
        StringBuilder s = new StringBuilder();
        for (int i=0; i<9; i++)
            s.append(scanner.nextInt());
        Map<String, Integer> distance = new HashMap<>();
        distance.put(s.toString(), 0);
        Queue<String> q = new LinkedList<>();
        q.add(s.toString());
        while (!q.isEmpty()) {
            String v = q.poll();
            if (v.equals(answer)) {
                System.out.println(distance.get(v));
                return;
            }
            int index = populate(v, R),
                    x = index / 3,
                    y = index % 3;
            for (int[] d: D) {
                int dx = x + d[0],
                        dy = y + d[1];
                if (dx >= 0 && dx < 3 && dy >= 0 && dy < 3) {
                    swap(x, y, dx, dy);
                    String w = depopulate(R);
                    if (!distance.containsKey(w)) {
                        distance.put(w, distance.get(v) + 1);
                        q.add(w);
                    }
                    swap(x, y, dx, dy);
                }
            }
        }
        System.out.println(-1);
    }

    private static String depopulate(char[][] R) {
        StringBuilder s = new StringBuilder();
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                s.append(R[i][j]);
        return s.toString();
    }

    private static void swap(int x, int y, int dx, int dy) {
        char t = R[x][y];
        R[x][y] = R[dx][dy];
        R[dx][dy] = t;
    }

    private static int populate(String s, char[][] R) {
        int index = -1;
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == '0')
                index = i;
            R[i/3][i%3] = s.charAt(i);
        }
        return index;
    }
}
