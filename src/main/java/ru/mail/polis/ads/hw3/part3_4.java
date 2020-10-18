package ru.mail.polis.ads.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class part3_4 {
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
        final part3_4.FastScanner in = new part3_4.FastScanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i<n; i++){
            arr[i] =in.nextInt();
        }
        for (int i = 0; i<q; i++){
            int x = in.nextInt();
            if (binarysrh(arr, x)) {
            System.out.println("YES");
            }else System.out.println("NO");
            }
        }
    public static boolean binarysrh(int[] arr, int x){
    int left = 0;
    int right = arr.length;
    int mid ;
    while(right>=left) {
        mid = (right+left) / 2;
        if (mid>= arr.length) break;
        if (arr[mid] == x) return true;
        if (x > arr[mid]) {
            left = mid + 1;
        } else right = mid - 1;
    }
    return false;
    }
}
