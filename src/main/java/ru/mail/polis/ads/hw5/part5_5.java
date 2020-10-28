package ru.mail.polis.ads.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class part5_5 {
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
        final part5_5.FastScanner in = new part5_5.FastScanner(System.in);
        int w = in.nextInt();
        int[] a = new int[w];
        for (int i = 0; i < w; i++) {
            a[i] = i + 1;
            System.out.print(a[i] + " ");
        }
        System.out.println();
        while (setsq(a, w)) {
            for (int i = 0; i < w; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println();
        }

    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean setsq(int[] a, int n) {
        int j = n - 2;
        while (j != -1 && a[j] >= a[j + 1]) j--;
        if (j == -1)
            return false;
        int k = n - 1;
        while (a[j] >= a[k]) k--;
        swap(a, j, k);
        int l = j + 1, r = n - 1;
        while (l < r)
            swap(a, l++, r--);
        return true;
    }

}
