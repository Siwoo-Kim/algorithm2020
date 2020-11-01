package com.siwoo.util;

public class Print {

    public static void print(int[][] a) {
        for (int i=0; i<a.length; i++) {
            System.out.println();
            for (int j=0; j<a[i].length; j++)
                System.out.printf("%2d ", a[i][j]);
        }
        System.out.println();
    }

    public static void print(char[][] a) {
        for (int i=0; i<a.length; i++) {
            System.out.println();
            for (int j=0; j<a[i].length; j++)
                System.out.printf("%2c ", a[i][j]);
        }
        System.out.println();
    }

    public static void print(boolean[][] a) {
        for (int i=0; i<a.length; i++) {
            System.out.println();
            for (int j=0; j<a[i].length; j++)
                System.out.printf("%2s ", a[i][j]);
        }
        System.out.println();
    }

    public static void print(String[][] a) {
        for (int i=0; i<a.length; i++) {
            System.out.println();
            for (int j=0; j<a[i].length; j++)
                System.out.printf("%2s ", a[i][j]);
        }
        System.out.println();
    }
}
