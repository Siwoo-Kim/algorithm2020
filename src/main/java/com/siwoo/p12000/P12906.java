package com.siwoo.p12000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.BFS)
public class P12906 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    private static class Hanoi {
        private String[] towers = new String[3];
        
        public void add(int i, String s) {
            towers[i] = s;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Hanoi hanoi = (Hanoi) o;
            return Arrays.equals(towers, hanoi.towers);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(towers);
        }

        public boolean check() {
            char start = 'A';
            for (int i=0; i<towers.length; i++) {
                String s = towers[i];
                for (int j=0; j<s.length(); j++) {
                    if (s.charAt(j) != start)
                        return false;
                }
                start++;
            }
            return true;
        }

        @Override
        public String toString() {
            return "{" +
                    "towers=" + Arrays.toString(towers) +
                    '}';
        }
    }
    
    
    public static void main(String[] args) throws IOException {
        Hanoi hanoi = new Hanoi();
        for (int i=0; i<3; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int cnt = Integer.parseInt(token.nextToken());
            if (cnt == 0)
                hanoi.add(i, "");
            else
                hanoi.add(i, token.nextToken());
        }
        Queue<Hanoi> q = new LinkedList<>();
        q.add(hanoi);
        Map<Hanoi, Integer> dist = new HashMap<>();
        dist.put(hanoi, 0);
        while (!q.isEmpty()) {
            hanoi = q.poll();
            if (hanoi.check()) {
                System.out.println(dist.get(hanoi));
                return;
            }
            for (int from=0; from<3; from++) {
                for (int to=0; to<3; to++) {
                    if (from == to) continue;
                    int len = hanoi.towers[from].length();
                    if (len == 0) continue;
                    String s1 = hanoi.towers[to] + hanoi.towers[from].charAt(len-1);
                    String s2 = hanoi.towers[from].substring(0, len-1);
                    String s3 = hanoi.towers[3-(from+to)];
                    Hanoi newHanoi = new Hanoi();
                    newHanoi.add(to, s1);
                    newHanoi.add(from, s2);
                    newHanoi.add(3-(from+to), s3);
                    if (!dist.containsKey(newHanoi)) {
                        dist.put(newHanoi, dist.get(hanoi) + 1);
                        q.add(newHanoi);
                    }
                }
            }
        }
    }
    
}
