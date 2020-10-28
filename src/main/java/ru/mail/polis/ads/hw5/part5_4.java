package ru.mail.polis.ads.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class part5_4 {
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
        final part5_4.FastScanner in = new part5_4.FastScanner(System.in);
        String w = in.next();
        String sh = in.next();
        if (sh.equals(w)) System.out.println("YES");
        else if (w.length() == 0 && sh.length() == 0) System.out.println("YES");
        else seqLet(sh, w);
    }

    private static void seqLet(String s, String w) {

        char[] sh = s.toCharArray();
        char[] word = w.toCharArray();
        boolean[][] d = new boolean[sh.length + 1][word.length + 1];
        d[0][0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= w.length(); j++) {
                if (sh[i - 1] == word[j - 1] || sh[i - 1] == '?' || word[j - 1] == '?')
                    d[i][j] = d[i - 1][j - 1];
                if (sh[i - 1] == '*' || word[j - 1] == '*')
                    d[i][j] = d[i - 1][j - 1] || d[i - 1][j] || d[i][j - 1];

            }
        }
        if (d[sh.length][word.length]) System.out.println("YES");
        else System.out.println("NO");
    }
}
