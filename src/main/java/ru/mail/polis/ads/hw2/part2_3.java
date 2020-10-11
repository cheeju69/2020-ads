package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class part2_3 {
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
    public static void main(String[] arg){
        final part2_3.FastScanner in = new part2_3.FastScanner(System.in);
        int len = in.nextInt();
        int[] arr = new int[len];
        for (int i = 0; i< len; i++){
            arr[i] = in.nextInt();
        }
        System.out.print(bubble(arr,len));
    }
    public static void swap(int[] arr,int l,int r){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
    public static int bubble(int[] arr, int len){
        int k = 0;
        for(int j = len -1;j >= 1; j--){
            for (int i = 0; i < j; i++){
                if(arr[i] > arr[i+1]){
                    k++;
                    swap(arr,i,i+1);
                }
            }
        }
        return k;
    }
}