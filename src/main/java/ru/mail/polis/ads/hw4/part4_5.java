package ru.mail.polis.ads.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class part4_5 {
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
        final part4_5.FastScanner in = new part4_5.FastScanner(System.in);
        int x = in.nextInt();
        int[] arr = new int[x];
        for (int i = 0; i < x; i++) {
            int n = in.nextInt();
            arr[i] = n;
        }
        System.out.print(countinv(arr));
    }

    public static int countinv(int[] arr) {
        if (arr.length <= 1) return 0;
        int mid = (arr.length) / 2;
        int[] l = new int[mid];
        int[] r = new int[arr.length-mid];

        System.arraycopy(arr, 0, l, 0, mid);
        System.arraycopy(arr, mid, r, 0, arr.length-mid);

        return countinv(l) + countinv(r) + countinvsplit(arr, l, r);

    }

    public static int countinvsplit(int[] arr, int[] l, int[] r) {
        int i = 0;
        int j = 0;
        int inv = 0;
        int k = 0;
        while (i < l.length && j < r.length) {
            if (l[i] <= r[j]) {
                arr[k] = l[i];
                i++;
            } else {
                arr[k] = r[j];
                j++;
                inv += l.length - i;
            }
            k++;
        }
        while (i < l.length) {
            arr[k] = l[i];
            i++;
            k++;
        }
        while (j < r.length) {
            arr[k] = r[j];
            j++;
            k++;
        }
        return inv;
    }
}
