package ru.mail.polis.ads.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class part5_2 {
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
        final part5_2.FastScanner in = new part5_2.FastScanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();
        long n = in.nextInt();

        System.out.print(wallSize(w, h, n));
    }

    private static long wallSize(int w, int h, long n) {
        long r = Math.max(h, w) * n;
        long l = Math.max(h, w);
        long mid, count;
        while (l < r) {
            mid = (l + r) / 2;
            count = (mid / h) * (mid / w);
            if (n <= count) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
