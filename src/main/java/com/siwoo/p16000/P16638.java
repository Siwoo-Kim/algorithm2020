package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static java.util.stream.Collectors.toList;

/**
 *  1. 괄호가 쳐진 연산을 처리한다.
 *  2. 위의 연산에서 처리된 연산자 op[i] 와 num[i]+1 을 지운다.
 *  3. 곱셈 연산을 처리한다, 위와 같은 과정을 진행한다.
 *  4. 최종 연산을 수행한다.
 *  
 *  O(2^N/2 * N), N = 19 = 2^9 * 19 = 9,728
 *  
 */
@Used(algorithm = Algorithm.BINARY_SELECTION)
public class P16638 {
    private static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private static int OP, N;
    private static String s;
    private static Character[] OPS;
    private static Integer[] NUMBERS;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(READER.readLine());
        OP = N / 2;
        N = N / 2 + 1;
        s = READER.readLine();
        OPS = new Character[OP];
        NUMBERS = new Integer[N];
        int x = 0, y = 0;
        for (int i=0; i<s.length(); i++) {
            if (Character.isDigit(s.charAt(i)))
                NUMBERS[x++] = s.charAt(i) - '0';
            else
                OPS[y++] = s.charAt(i);
        }
        int answer = go(0, new Stack<>());
        System.out.println(answer);
    }

    private static int go(int index, Stack<Integer> select) {
        if (index >= OP) {
            Integer[] indexes = select.toArray(new Integer[0]);
            List<Integer> numbers = Arrays.stream(NUMBERS).collect(toList());
            List<Character> ops = Arrays.stream(OPS).collect(toList());
            for (int i=0; i<indexes.length; i++) {
                int opIndex = indexes[i];
                int x = calc(numbers.get(opIndex), 
                        numbers.get(opIndex+1), 
                        ops.get(opIndex));
                numbers.set(opIndex, x);
                ops.set(opIndex, '?');
            }
            for (int i=0; i<ops.size(); i++) {
                if (ops.get(i) == '?') {
                    ops.remove(i);
                    numbers.remove(i+1);
                }
            }
            for (int i=0; i<ops.size(); i++) {
                if (ops.get(i) == '*') {
                    int x = calc(numbers.get(i),
                            numbers.get(i + 1),
                            ops.get(i));
                    numbers.set(i, x);
                    numbers.remove(i+1);
                    ops.remove(i);
                    i--;
                }
            }
            int value = numbers.get(0);
            for (int i=1; i<numbers.size(); i++)
                value = calc(value, numbers.get(i), ops.get(i-1));
            return value;
        }
        select.add(index);
        int x = go(index + 2, select);
        select.pop();
        int y = go(index+1, select);
        return Math.max(x, y);
    }

    private static int calc(int x, int y, char op) {
        if (op == '+')
            return x + y;
        if (op == '-')
            return x - y;
        if (op == '*')
            return x * y;
        return x;
    }
}
