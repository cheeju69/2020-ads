package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.BufferedReader;

public class part3 {
    public static void main(String[] arg) throws IOException {
        ArrayList arr = new ArrayList();
        StackCust a = new StackCust(arr);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String del = " ";
        String[] subStr;
        while(true){
        subStr = null;
        String com = reader.readLine();
        subStr = com.split(del);
        switch (subStr[0]){
            case "push": {
                a.push(Integer.parseInt((subStr[1])));
                break;
            }
            case "pop": {
                a.pop();
                break;
            }
            case "back": {
                a.back();
                break;
            }
            case "size": {
                a.size();
                break;
            }
            case "clear": {
                a.clear();
                break;
            }
            case "exit": {
                a.exit();
                break;
            }
        }
        }
    }
    public static class StackCust {
        private final ArrayList arr;
        public StackCust(ArrayList arr1){
            this.arr = arr1;
        }
        public void push(int n){
            arr.add(n);
            System.out.println("ok");
        }
        public void pop(){
            if(arr.size() == 0){
                System.out.println("error");
            }else{
                int a = (int) arr.get(arr.size()-1);
                arr.remove(arr.size()-1);
                System.out.println(a);
             }
        }
        public void back(){
            if(arr.size() == 0){
                System.out.println("error");
            }else{
                int a = (int) arr.get(arr.size()-1);
                System.out.println(a);
            }
        }
        public void size(){
            System.out.println(arr.size());
        }
        public void clear(){
            arr.clear();
            System.out.println("ok");
        }
        public void exit(){
            System.out.println("bye");
            System.exit(0);
        }
    }
}
