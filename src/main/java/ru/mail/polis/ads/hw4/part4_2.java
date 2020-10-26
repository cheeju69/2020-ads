package ru.mail.polis.ads.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class part4_2 {
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
        final part4_2.FastScanner in = new part4_2.FastScanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        int[][] map = new int[x][y];
        for (int i = x - 1; i >= 0; i--) {
            for (int j = 0; j < y; j++) {
                int n = in.nextInt();
                map[i][j] = n;
            }
        }
        int[] res = GetGrains(map);
        for (int i = res.length - 1; i >= 0; i--) {
            if (res[i] == 0) continue;
            if (res[i] == 1) System.out.print("R");
            if (res[i] == 2) System.out.print("F");

        }
    }

    public static int[] GetGrains(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        int[] maxGrainsWay = new int[n + m - 1];
        int i, j;
        int[] fr = new int[m + n];
        int ifr = 0;

        int[][] grains = new int[m + 1][n + 1];
        for (i = 0; i <= m; i++) {
            for (j = 0; j <= n; j++) {
                grains[i][j] = i == 0 || j == 0 ? -1 : arr[i - 1][j - 1];
            }
        }

        grains[0][1] = 0;
        for (i = 1; i <= m; i++) {
            for (j = 1; j <= n; j++) {
                grains[i][j] = Math.max(grains[i - 1][j], grains[i][j - 1]) + grains[i][j];
            }
        }

        i = m;
        j = n;
        int index = 1;
        maxGrainsWay[0] = arr[i - 1][j - 1];
        while (i + j > 2) {
            if (grains[i - 1][j] > grains[i][j - 1]) {
                maxGrainsWay[index] = arr[i - 2][j - 1];
                fr[ifr] = 2;
                ifr++;

                i--;
            } else {
                maxGrainsWay[index] = arr[i - 1][j - 2];
                j--;
                fr[ifr] = 1;
                ifr++;
            }
            index++;
        }
        return fr;
    }
}
