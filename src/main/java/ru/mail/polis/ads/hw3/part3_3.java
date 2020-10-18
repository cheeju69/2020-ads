package ru.mail.polis.ads.hw3;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class part3_3 {

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

    public static void main(String[] arg) throws IOException {

        PriorityQueue<Integer> left = new PriorityQueue<Integer>();
        PriorityQueue<Integer> right = new PriorityQueue<Integer>(Collections.reverseOrder());

        final part3_3.FastScanner in = new part3_3.FastScanner(System.in);

        String x;
        try (PrintWriter pw = new PrintWriter(System.out)) {
            int i = 1, med = 0;
            while ((x = in.reader.readLine()) != null) {
                int n = Integer.parseInt(x);
                if (i % 2 != 0) {
                    if (n > med) {
                        left.add(n);
                        med = left.poll();
                    } else {
                        right.add(n);
                        med = right.poll();
                    }
                } else {
                    if (n > med) {
                        left.add(n);
                        right.add(med);
                    } else {
                        right.add(n);
                        left.add(med);
                    }
                    med = (left.peek() + right.peek()) / 2;
                }
                i++;
                pw.write(med + "\n");
            }
        }
    }
}