package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class part2_5 {
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
        final part2_5.FastScanner in = new part2_5.FastScanner(System.in);
        int len = in.nextInt();
        Map<Integer, LinkedList<Integer>> states = new HashMap<>();
        int[] main_arr = new int[len];
        int[] arr = new int[len];
        int[] numb_arr = new int[len];
        for (int i = 0; i < len; i++)
            numb_arr[i] = i;
        for (int i = 0; i< len; i++){
            main_arr[i] = in.nextInt();
            arr[i] = in.nextInt();
            LinkedList<Integer> val = new LinkedList<Integer>();
            if (states.containsKey(main_arr[i])){
                val = states.get(Integer.valueOf(main_arr[i]));
                val.add(i);
            }else{
                val.add(i);
        }
            states.put(main_arr[i],val);
        }
        int [] res = part2_5.sortArray(main_arr);
        for (int i = 0; i< len; i++){
            for (int j = 0; j< states.get(Integer.valueOf(res[i])).size(); j++){
                Integer min = states.get(Integer.valueOf(res[i])).pop();
                System.out.println(res[i]+" "+arr[min]);
        }
    }}
    public static int [] mergeArray(int[] arr, int[] arr2) {

        int [] arr3 = new int[arr.length + arr2.length];
        int pos = 0, pos2 = 0;

        for (int i = 0; i < arr3.length; i++) {
            if (pos == arr.length){
                arr3[i] = arr2[pos2];
                pos2++;
            } else if (pos2 == arr2.length) {
                arr3[i] = arr[pos];
                pos++;
            } else if (arr[pos] < arr2[pos2]) {
                arr3[i] = arr[pos];
                pos++;
            } else {
                arr3[i] = arr2[pos2];
                pos2++;
            }
        }
        return arr3;
    }
    public static int [] sortArray(int[] arr){
        if (arr == null) {
            return null;
        }
        if (arr.length == 1) {
            return arr;
        }
        int [] arr2 = new int[arr.length / 2];
        System.arraycopy(arr, 0, arr2, 0, arr.length / 2);
        int len3 = arr.length - arr.length / 2;
        int [] arr3 = new int[len3];
        System.arraycopy(arr, arr.length / 2, arr3, 0, len3);

        arr2 = sortArray(arr2);
        arr3 = sortArray(arr3);


        return mergeArray(arr2, arr3);
    }
}