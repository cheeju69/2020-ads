package ru.mail.polis.ads.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class part3_2 {
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
        final part3_2.FastScanner in = new part3_2.FastScanner(System.in);
        Heap heap = new Heap();
        int len = in.nextInt();
        for (int i = 0; i < len; i++) {
            int n = in.nextInt();
            if (n == 0) {
                heap.insert(in.nextInt());
//                heap.hprint();
            } else {
                int e = heap.extract();
                System.out.println(e);
            }
        }
    }

    public static class Heap {
        private ArrayList<Integer> arr = new ArrayList<>();
        public Heap() {
            arr.add(1);
        }
        public int size() {
            return arr.size()-1;
        }
        public void hprint() {
            for (int i = 0; i < arr.size(); i++) {
                System.out.print(" " + arr.get(i));
            }
            System.out.println();
        }

        public void sink(int x){
            while (2 * x <= this.size()) {
                int j = 2 * x;
                if (j < this.size() && arr.get(j) < arr.get(j + 1)) j++;
                if (arr.get(x) >= arr.get(j)) break;
                int temp = arr.get(x);
                arr.set(x, arr.get(j));
                arr.set(j, temp);
                x = j;
            }
        }
        private void swim(int x) {
            while (x > 1 && arr.get(x) > arr.get(x / 2)) {
                int temp = arr.get(x);
                arr.set(x, arr.get(x/2));
                arr.set(x / 2, temp);
                x = x / 2;
            }
        }
        public void insert(int value) {
            arr.add(value);
            swim(this.size());

        }
        public int extract() {
            int res = arr.get(1);
            arr.set(1, arr.get(this.size()));
            arr.set(this.size(), res);
            arr.remove(this.size());
            sink(1);
            return res;
        }
    }
}
