package ru.mail.polis.ads.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import static java.lang.Math.max;

public class part4_4 {
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
        final part4_4.FastScanner in = new part4_4.FastScanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n+2];
        for (int i = 1; i < n+1; i++) {
            int t = in.nextInt();
            arr[i] = t;
        }
        int k = in.nextInt();
        System.out.print(stairCost(arr,n,k));
    }
    public static int stairCost(int[] arr,int n,int k){
        int[] d = new int[n+2];
        for (int i = 1; i < d.length; i++) {
            int m = -2147483648;
            for (int j = i - 1; j >= max(i - k, 0); j--) {
                if (d[j] > m) {
                    m = d[j];
                }
            }
            d[i] = m + arr[i];
        }
        return d[n+1];
    }
}
