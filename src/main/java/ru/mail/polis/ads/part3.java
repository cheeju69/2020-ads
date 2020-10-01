package ru.mail.polis.ads;

import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class part3 {
    public static void main(String[] arg) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        boolean f = false;
        int m = 0;
        for(int i=0; i<line.length();i++){
            if(line.charAt(i) == '('){
                m++;
            }else if(line.charAt(i) == ')'){
                m--;
            }
            if(m<0){
                f = true;
            }
        }
        if(f || (m>0)){
            System.out.println("NO");
        }else{
            System.out.println("YES");
        }
    }
}
