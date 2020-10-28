package ru.mail.polis.ads.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class part5_3 {
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
        final FastScanner in = new FastScanner(System.in);
        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            int p = in.nextInt();
            a[i] = p;
        }
        System.out.print(cposl(a));
    }

    public static int cposl(long[] a) {
        int len = 1;
        int[] d = new int[a.length];
        d[0] = 1;
        for (int i = 1; i < d.length; i++) {
            d[i] = 0;
            for (int j = 0; j < i; j++) {
                if (a[j] == 0) continue;
                if (a[i] % a[j] == 0) {
                    if (d[j] > d[i]) d[i] = d[j];
                }
            }
            d[i]++;
            if (d[i] > len) len = d[i];
        }
        return len;
    }
}
