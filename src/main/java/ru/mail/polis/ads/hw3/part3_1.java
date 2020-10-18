package ru.mail.polis.ads.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class part3_1 {
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
        final part3_1.FastScanner in = new part3_1.FastScanner(System.in);
        int len = in.nextInt();
        int[] arr = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            arr[i] = in.nextInt();
        }
        if (heap(arr, len)) {
            System.out.print("YES");
        } else {
            System.out.print("NO");
        }
    }

    public static boolean heap(int[] arr, int len) {
        boolean result = true;
        for (int i = 1; i < len; i++) {
            if (2 * i <= len) {
                if (arr[i] > arr[2 * i]) {
                    result = false;
                }
            }
            if (2 * i + 1 <= len) {
                if (arr[i] > arr[2 * i + 1]) {
                    result = false;
                }
            }
        }
        return result;
    }
}
