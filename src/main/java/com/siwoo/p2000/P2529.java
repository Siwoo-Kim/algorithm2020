package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

@Used(algorithm = Algorithm.BACK_TRACKING)
public class P2529 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String[] signs;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine()) + 1;
        signs = reader.readLine().split("\\s+");
        String[] answers = select();
        System.out.printf("%s%n%s", answers[0], answers[1]);
    }

    private static String[] select() {
        return new String[] {
                select(0, new Stack<>(), "r"),  // 오름차순 중 첫번째 찾은 결과 - min
                select(9, new Stack<>(), "")};  // 내림차순 중 첫번째 찾은 결과 - max
    }

    private static String select(int current, Stack<Integer> stack, String mode) {
        if (stack.size() == N) return check(stack);
        if (current > 9 || current < 0) return null;
        if ("r".equals(mode)) {
            for (int i=9; i>=0; i--) {
                if (stack.contains(i)) continue;
                stack.push(i);
                if (check(stack) != null) {  //backtracking
                    String answer = select(i, stack, mode);
                    if (answer != null && answer.length() == N)
                        return answer;
                }
                stack.pop();
            }
        } else {
            for (int i=0; i<=9; i++) {
                if (stack.contains(i)) continue;
                stack.push(i);
                if (check(stack) != null) {  //backtracking
                    String answer = select(i, stack, mode);
                    if (answer != null && answer.length() == N)
                        return answer;
                }
                stack.pop();
            }
        }
        return null;
    }

    private static String check(Stack<Integer> stack) {
        if (stack.size() == 1) return Integer.toString(stack.get(0));
        boolean ok = true;
        int[] values = stack.stream().mapToInt(i -> i).toArray();
        for (int i=1; i<stack.size(); i++) {
            String sign = signs[i-1];
            if ("<".equals(sign))
                if (!(values[i-1] < values[i])) {
                    ok = false;
                    break;
                }
            if (">".equals(sign))
                if (!(values[i-1] > values[i])) {
                    ok = false;
                    break;
                }
        }
        if (ok)
            return Arrays.stream(values).mapToObj(Integer::toString).collect(Collectors.joining());
        else
            return null;
    }
}
