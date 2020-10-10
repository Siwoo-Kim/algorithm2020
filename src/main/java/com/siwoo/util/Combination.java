package com.siwoo.util;

import java.util.*;

/**
 * 조합 - 선택의 순서가 중요하지 않은 경우의 모든 경우의 수.
 */
public class Combination {

    public static <E> void combination(List<E> elements, int select) {
        assert elements != null && elements.size() >= select;
        //elements = new ArrayList<>(new HashSet<>(elements)); - real combination
        combination(0, elements, elements.size(), select, new Stack<E>());
    }

    private static <E> void combination(int start, List<E> elements, int N, int M, Stack<E> stack) {
        if (stack.size() == M) {
            StringBuilder sb = new StringBuilder();
            stack.forEach(e -> sb.append(e).append(" "));
            sb.append("\n");
            System.out.println(sb.toString());
            return;
        }
        for (int i=start; i<N; i++) {
            stack.push(elements.get(i));
            combination(i+1, elements, N, M, stack);
            stack.pop();
        }
    }

    public static void main(String[] args) {
        combination(Arrays.asList(1, 2, 3, 4, 5), 3);
    }
}
