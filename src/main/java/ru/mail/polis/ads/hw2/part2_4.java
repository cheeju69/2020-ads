package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;

public class part2_4 {
    public static void main(String[] arg) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        int k = Integer.parseInt(reader.readLine());
        String com = reader.readLine();
        String del = " ";
        String[] subStr = com.split(del);
        int len = subStr.length;

        BigInteger[] arr = new BigInteger[len];
        for (int i = 0; i<subStr.length; i++){
            BigInteger temp = new BigInteger(subStr[i]);
            arr[i] = temp;
        }
        System.out.print(kstat(arr,arr.length - k));
    }
    public static BigInteger kstat(BigInteger[] arr, int k) {
        int l = 0, r = arr.length;
        while (true) {
            int mid = partition(arr, l, r);
            if (mid == k) {
                return arr[mid];
            }
            if (k < mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
    }

    private static int partition(BigInteger[] arr, int l, int r) {
        int j = l;
        BigInteger x = arr[l];
        for (int i = l + 1; i < r; i++) {
            if (arr[i].compareTo(x) < 0) {
                j++;
                swap(arr,i,j);
            }
        }
        swap(arr,j,l);
        return j;
    }
    public static void swap(BigInteger[] arr,int l,int r){
        BigInteger temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
    }