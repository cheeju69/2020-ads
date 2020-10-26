package ru.mail.polis.ads.hw4;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class part4_1 {
    public static void main(String[] arg) throws IOException {

        final part4_1.FastScanner in = new part4_1.FastScanner(System.in);
        int countRdO = 0, countSqO = 0;//открывающих скобок круглых и квадратных всего
        int countRdC = 0, countSqC = 0;//закрывающих скобок круглых и квадратных всего
        int countRdO2 = 0, countSqO2 = 0;//открывающих скобок круглых и квадратных слева от текущей позиции
        int countRdC2 = 0, countSqC2 = 0;//закрывающих скобок круглых и квадратных слева от текущей позиции
        ArrayList<Character> seq = new ArrayList<>();
        String x;
        try (PrintWriter pw = new PrintWriter(System.out)) {
            while ((x = in.reader.readLine()) != null) {
                char[] str = x.toCharArray();
                for (int i = 0; i < str.length; i++) {
                    switch (str[i]) {
                        case ('('): {
                            countRdO++;
                            break;
                        }
                        case (')'): {
                            countRdC++;
                            break;
                        }
                        case ('['): {
                            countSqO++;
                            break;
                        }
                        case (']'): {
                            countSqC++;
                            break;
                        }
                    }
                }
                for (int i = 0; i < str.length; i++) {
                    switch (str[i]) {
                        case ('('): {
                            countRdO2++;
                            if (countRdO > countRdC) {
                                seq.add(str[i]);
                                seq.add(')');
                                countRdO--;
                                countRdO2--;
                            }else{
                                if (countSqO2>0){
                                    seq.add(']');
                                    countSqO2--;
                                    seq.add(str[i]);
                                }else seq.add(str[i]);
                            }

                            break;
                        }
                        case (')'): {
                            if (countRdC > countRdO) {
                                seq.add('(');
                                seq.add(str[i]);
                                countRdC--;
                                countRdC2--;
                            }else if (countRdO2<=countRdC2) {
                                seq.add('(');
                                seq.add(')');
                                countRdC--;
                                countRdC2--;
                            }else seq.add(')');
                            countRdC2++;
                            break;
                        }
                        case ('['): {
                            countSqO2++;
                            if (countSqO > countSqC) {
                                seq.add(str[i]);
                                seq.add(']');
                                countSqO--;
                                countSqO2--;
                            }else{
                                if (countRdO2>0){
                                    seq.add(')');
                                    countRdO2--;
                                    seq.add(str[i]);
                                }else seq.add(str[i]);
                            }
                            break;
                        }
                        case (']'): {
                            if (countSqC > countSqO) {
                                seq.add('[');
                                seq.add(str[i]);
                                countSqC--;
                                countSqC2--;
                            }else if (countSqO2<=countSqC2) {
                                seq.add('[');
                                seq.add(']');
                                countSqC--;
                                countSqC2--;
                            }else seq.add(']');
                            countSqC2++;
                            break;
                        }
                    }
                }
                for (int i = 0; i < seq.size(); i++) {
                    System.out.print(seq.get(i));
                }
                break;
            }
        }
    }

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
}
