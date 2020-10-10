package com.siwoo.util;

public class Print {

    public static void print(int[][] a) {
        for (int i=0; i<a.length; i++) {
            System.out.println();
            for (int j=0; j<a[i].length; j++)
                System.out.print(a[i][j] + " ");
        }
        System.out.println();
    }
}
