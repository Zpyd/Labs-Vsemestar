

package com.mycompany.zad2_labs3;

public class Zad2_LABS3 {
    
    private static void PascalTriangle(int n) { //O(n^2)
        int[][] a = new int[n][500]; //yes iam aware of the unused space/too little space and idc
        for (int i = 0; i < n; i++) {
            a[i][0] = a[i][i] = 1;
            if(i!=0){
            System.out.print(a[i][0]+" ");}
            for (int j = 1; j < i; j++) {
                a[i][j] = a[i - 1][j] + a[i - 1][j - 1];
                System.out.print(a[i][j] + " ");
            }
            System.out.print(a[i][i]+"\n");
        }

    }


    public static void main(String[] args) {
        PascalTriangle(5);
    }

    
}
