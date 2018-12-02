package chapter18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class IO {
    public static void main(String[] args) {
        test3();
    }

    public static void test3(){
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s;
        try {
            while ((s = stdin.readLine()) != null && s.length()!=0 ) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void test2(){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String i = sc.next();
            System.out.println(i);
        }
    }
    public static void test1(){
        char c;
        try {
            while ((c = (char)System.in.read()) != -1 ) {//read the single character
                if(c !='\n')    System.out.println(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
