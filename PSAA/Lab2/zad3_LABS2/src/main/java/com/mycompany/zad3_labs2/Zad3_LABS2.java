
package com.mycompany.zad3_labs2;

import java.util.*;



public class Zad3_LABS2 {


    public static void main(String[] args) {
        System.out.println("Hello World!");
        Queue<Integer> red= new LinkedList<>();
//        red.add(2);
//        red.add(3);
//        red.add(5);
//        red.add(1);
//        red.add(1);
//        red.add(2);
//        red.add(3);
        
            red.add(1);
            red.add(2);
            red.add(3);
            red.add(4);
            red.add(7);
            red.add(1);

        antiFinaciSociety(red);
        printQueue(red);
        
    }
    
    
     private static void antiFinaciSociety(Queue<Integer> red) {
      
        
        if(red.isEmpty() || red.size()<=2){
            System.out.println("bud nema dovolno elementi");
        }
        
        int qsize=red.size();
        int e1=red.remove(); int e2=red.remove(); int e3=red.remove();
        
        for (int i = 0; i < qsize-2; i++) {
       
            if ((e1 + e2) != e3) {
                red.add(e1); //na krajo od redo
                e1=e2;
            }
                e2=e3;
                if(i<qsize-3){
                e3=red.poll();}
        }
        red.add(e1);red.add(e2); //red.add(e1);
      
    } 
    
    public static void printQueue(Queue<Integer> q){
        for (int i = 0; i < q.size(); i++) {
            System.out.print(q.peek()+" ");
            q.add(q.poll());
        
        }
 
       
        
    } 

   
}
