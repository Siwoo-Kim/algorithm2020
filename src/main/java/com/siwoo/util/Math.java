package com.siwoo.util;

public class Math {
    
    public static long pow(long a, long b) {
        //O(b)
        /*
            long answer = 1;
            for (int i=1; i<=b; i++)
                answer *= a;
            return answer;
        */
        // O(log2b)
        // if b is even: a^b = a^b/2 * a^b/2
        // if b is odd: a^b = a * a^b-1
//        if (b == 0) return 1;
//        if (b == 1) return a;
//        if (b % 2 == 0) {
//            long r = pow(a, b/2);
//            return r * r;
//        }
//        else return pow(a, 1) * pow(a, b-1);
        /*
            3^27, 27 = 11011(2)
            3       = 11011
            *
            3^2     = 1101
            (3^4     = 110 skip)
            *
            3^8     = 11
            *
            3^16    = 1
         */
        long answer = 1;
        while (b > 0) {
            if (b % 2 == 1)
                answer *= a;
            a *= a;
            b /= 2;
        }
        return answer;
    }
    
    public int[][] plus(int[][] m1, int[][] m2) {
        int[][] r = new int[m1.length][m1[0].length];
        for (int i=0; i<r.length; i++)
            for (int j=0; j<r[0].length; j++)
                r[i][j] = m1[i][j] + m2[i][j];
        return r;
    }
    
    //O(Math.max(n, k, m)^3)
    public int[][] multi(int[][] m1, int[][] m2, int n, int k, int m) {
        int[][] r = new int[n][m];
        for (int i=0; i<n; i++)
            for (int j=0; j<m; j++)
                for (int z=0; z<k; z++)
                    r[i][j] += m1[i][j] * m2[z][j];
        return r;
    }
    

    public static void main(String[] args) {
        System.out.println(pow(2, 4));
        System.out.println(pow(2, 0));
        System.out.println(pow(2, 32));
        System.out.println(pow(3, 27));
    }
}
