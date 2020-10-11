package ru.mail.polis.ads;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class part2_1 {
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
        final FastScanner in = new FastScanner(System.in);
        int len = in.nextInt();
        int[] arr = new int[len];
//        in.nextLine();
//        String str = in.nextLine();
//        String[] subStr = str.split(" ");
        for (int i = 0; i< len; i++){
            arr[i] = in.nextInt();
        }
        mergeSort(arr,len);
        for(int i = 0; i< arr.length;i++){
            System.out.print(arr[i]);
        if(i!= arr.length-1){
            System.out.print(" ");
        }
        }
    }
    public static void merge(int[] arr, int[] l, int[] r, int lt, int rt){
        int i = 0, j = 0, k = 0;
        while (i < lt && j < rt) {
            if (l[i] <= r[j]) {
                arr[k++] = l[i++];
            }
            else {
                arr[k++] = r[j++];
            }
        }
        while (i < lt) {
            arr[k++] = l[i++];
        }
        while (j < rt) {
            arr[k++] = r[j++];
        }
    }
    public static void mergeSort(int[] arr, int j){
        if (j < 2){
            return;
        }
        int mid = j / 2;
        int[] l = new int[mid];
        int[] r = new int[j - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = arr[i];
        }
        for (int i = mid; i < j; i++) {
            r[i - mid] = arr[i];
        }
        mergeSort(l, mid);
        mergeSort(r, j - mid);

        merge(arr, l, r, mid, j - mid);
    }

}