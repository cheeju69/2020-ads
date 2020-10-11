package ru.mail.polis.ads;


import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.io.BufferedReader;

public class part5 {
    public static void main(String[] arg) throws IOException {
        LinkedList arr = new LinkedList();
        QuCust a = new QuCust(arr);
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
                case "front": {
                    a.front();
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
    public static class QuCust {
        private LinkedList arr;
        public QuCust(LinkedList arr1){
            this.arr = arr1;
        }
        public void push(int n){
            arr.add(arr.size(),n);
            System.out.println("ok");
        }
        public void pop(){
            int a = (int) arr.get(0);
            arr.remove(0);
            System.out.println(a);
        }
        public void front(){
            int a = (int) arr.get(0);
            System.out.println(a);
        }
        public void size(){
            System.out.println(arr.size());;
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
