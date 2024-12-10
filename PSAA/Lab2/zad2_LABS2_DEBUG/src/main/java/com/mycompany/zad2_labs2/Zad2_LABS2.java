

package com.mycompany.zad2_labs2;

import java.util.*;

public class Zad2_LABS2 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Stack<Integer> s=new Stack<>();
        s.push(6);
        s.push(4);
        s.push(2);
        s.push(3);
        s=reorderStack(s);
//        System.out.println(s.pop());
        printStack(s);
    }

    private static Stack<Integer> reorderStack(Stack<Integer> s){
        Stack<Integer> broj = new Stack<>(); //yes ik the names mean nofin
        Stack<Integer> temp = new Stack<>();
        
        broj.push(s.pop());
        while(!s.empty()){
            if(s.peek()>broj.peek()){
                temp.push(s.pop());
                
            }else{
                temp.push(broj.pop());
                broj.push(s.pop());
                while(!temp.isEmpty() && temp.peek()<broj.peek()){
                    s.push(temp.pop());
                }
            }
        }
        temp.push(broj.pop());
        return temp;
    }
    
    public static void printStack(Stack<Integer> s){
        for(Integer e : s){
            System.out.println(e);
        }
    }
}
