package ru.mail.polis.ads.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class part5_1 {
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
        final part5_1.FastScanner in = new part5_1.FastScanner(System.in);
        String n = in.next();
        double c = Double.parseDouble(n);

        System.out.print((double) Math.round(sqrtCust(c) * 1000000000) / 1000000000);
    }

    private static double sqrtCust(double c) {
        double R = 1e9, L = 0, mid;
        while (Math.abs(R - L) > 1e-9) {
            mid = (L + R) / 2;
            if ((Math.pow(mid, 2) + Math.sqrt(mid)) - c < 0) {
                L = mid;
            } else {
                R = mid;
            }
        }
        return R;

    }
}
