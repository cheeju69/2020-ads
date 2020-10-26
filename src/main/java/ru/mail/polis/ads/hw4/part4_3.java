package ru.mail.polis.ads.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class part4_3 {
    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] arg) {
        final part4_3.FastScanner in = new part4_3.FastScanner(System.in);
        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            int p = in.nextInt();
            a[i] = p;
        }
        int n2 = in.nextInt();
        long[] b = new long[n2];
        for (int i = 0; i < n2; i++) {
            int p = in.nextInt();
            b[i] = p;
        }
        System.out.print(cposl(a,b));
    }
    public static int cposl(long[] a,long[] b){
        int len;
        int[][] res = new int[a.length + 1][b.length + 1];
        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                if (a[i - 1] == b[j - 1]) {
                    res[i][j] =  (res[i - 1][j - 1] + 1);
                } else {
                    res[i][j] =  Math.max(res[i - 1][j], res[i][j - 1]);
                }
            }
        }
        len = res[a.length][b.length];
        return len;
    }
}
