
package com.mycompany.zad1_labs3;

import java.util.*;
        
public class Zad1_LABS3 {

    static HashMap<Integer,Integer> DP_memo=new HashMap<>();
    
    public static int TribonaciRec(int n){
      
        if(DP_memo.containsKey(n)){
           return DP_memo.get(n);
        }
        
        if(n==0){
           return 0;
       } else if(n==1 || n==2){
           return 1;
       }
        else{
       int val = TribonaciRec(n-1) + TribonaciRec(n-2) + TribonaciRec(n-3);
       DP_memo.put(n, val);
       return val;
       }
    }
    
    public static int TribBottomUp(int n){
        int [] array ={0,1,1};
        for (int i = 0; i < n-2; i++) {
            int temp= array[0] + array[1] + array[2];
            array[0]=array[1];
            array[1]=array[2];
            array[2]=temp;
        }
        return array [2];
    }
    
    public static void main(String[] args) {
        System.out.println(TribonaciRec(5));
        System.out.println(TribBottomUp(5));
        
    }
}
