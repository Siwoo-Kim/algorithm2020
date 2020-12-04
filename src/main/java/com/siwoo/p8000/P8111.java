package com.siwoo.p8000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.*;

/**
 * 어떤 수 N 의 배수 S 은 S / N 의 나머지가 0인 수이다.
 *      이때 나머지 의 범위는 0 ~ N-1
 *  
 * 어떤 수 x 에 0 과 1 만 붙여 N 의 배수 중 가장 짧은 S 을 구한다면,
 *  어떤 수 x % N 의 나머지 R 이 한번 등장한다면 그 이후론 항상 등장한다.
 *  우리가 원하는 것은 N 의 배수이니, R == 0 이라면 정답을 구한 것. (가장 짧은 S)
 *  
 * 나머지 연산
 *  (a+b)%c = ((a%c)+(b%c))%c
 *             이전 자리수
 *  위를 이용하여 이전 자리수의 나머지을 r 이라 했을 때
 *  (r*10+{1,0})%n 을 이용하면 실제 숫자가 만들지 않더라도 나머지를 구할 수 있다.
 *  
 */
@Used(algorithm = Algorithm.BFS)
public class P8111 {
    private static Scanner scanner = new Scanner(System.in);
    private static int T;
    
    public static void main(String[] args) {
        T = scanner.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<T; i++) {
            String s = solve(scanner.nextInt());
            sb.append(s).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static String solve(int n) {
        if (n == 1) return "1";
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Integer> value = new HashMap<>();
        Set<Integer> visit = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        parent.put(1, -1);
        value.put(1, 1);
        visit.add(1);
        q.add(1);
        while (!q.isEmpty()) {
            int v = q.poll();
            if (v == 0) {
                StringBuilder sb = new StringBuilder();
                for (int i=v; i!=-1; i=parent.get(i))
                    sb.append(value.get(i));
                return sb.reverse().toString();
            }
            for (int i=0; i<=1; i++) {
                int w = (v*10+i) % n;
                if (!visit.contains(w)) {
                    visit.add(w);
                    parent.put(w, v);
                    value.put(w, i);
                    q.add(w);
                }
            }
        }
        return "BRAK";
    }
}
