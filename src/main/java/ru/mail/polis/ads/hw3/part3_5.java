package ru.mail.polis.ads.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class part3_5 {
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
        final ru.mail.polis.ads.hw3.part3_5.FastScanner in = new ru.mail.polis.ads.hw3.part3_5.FastScanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.print(cow(arr,k));
    }

    public static boolean cowcheck(int[] arr, int x, int k) {
        int cows = 1;
        int lcow = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] - lcow >= x) {
                cows++;
                lcow = arr[i];
            }
        }
        return cows >= k;
    }

    public static int cow(int[] arr, int k) {
        int left = 0;
        int right = arr[arr.length - 1] - arr[0] + 1;
        while (right > left + 1) {
            int mid = (left + right) / 2;
            if (cowcheck(arr, mid, k)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

