package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P1759 {
    private static Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int L, C;
    private static char[] CHARS;
    private static Set<Character> VOWELS = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public static void main(String[] args) {
        String[] LC = scanner.nextLine().split("\\s+");
        L = Integer.parseInt(LC[0]);
        C = Integer.parseInt(LC[1]);
        CHARS = new char[C];
        int i = 0;
        for (String s: scanner.nextLine().split("\\s+"))
            CHARS[i++] = s.charAt(0);
        Arrays.sort(CHARS);
        combination(new Stack<>(), 0, L, C);
    }

    private static void combination(Stack<Integer> stack, int index, int L, int C) {
        if (stack.size() == L) {
            StringBuilder sb = new StringBuilder();
            stack.stream().map(i -> CHARS[i]).forEach(sb::append);
            int vowels = 0, consonant = 0;
            for (int i=0; i<sb.length(); i++) {
                if (VOWELS.contains(sb.charAt(i)))
                    vowels++;
                else
                    consonant++;
            }
            if (vowels >= 1 && consonant >= 2)
                System.out.println(sb.toString());
            return;
        }
        for (int i=index; i<C; i++) {
            stack.push(i);
            combination(stack, i+1, L, C);
            stack.pop();
        }
    }
}
