package com.siwoo.p16000;

import java.util.Scanner;

public class P16968 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String s = scanner.nextLine();
        String x = "";
        for (int i=0; i<s.length(); i++) {
            long unit = s.charAt(i) == 'c'? 26: 10;
            if (i != 0 && s.charAt(i-1) == s.charAt(i))
                unit--;
            x += unit + "|";
        }
        long value = 1;
        for (String sx: x.split("\\|"))
            value *= Integer.parseInt(sx);
        System.out.println(value);
    }
}
