/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.req2;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Peter
 */
public class Matrix {
    protected int m;
    protected int n;
//    private List<T[]> Numbers = new ArrayList<T[]>();
    protected int[][] Numbers;
    
    public Matrix(int i,int j){
        m = i;
        n = j;
        Numbers = new int[m][n];
    }

    public Matrix Add (Matrix a){
        if (m !=a.m || n != a.n) {
            System.out.println("Can not be added");
            return null;
        }
        Matrix temp = new Matrix(m,n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                temp.Numbers[i][j] = Numbers[i][j]+a.Numbers[i][j];
            }
        }
        return temp;
    }
    public Matrix multiply (Matrix m1 , Matrix m2,int start_col_OfM2,int end_col_OfM2) {
//        System.out.println("Output of Thread "+Thread.currentThread().getName());
        if (m1.n != m2.m) {
            System.out.println("Cannot multiply them");
            return null;
        }
        Matrix temp = new Matrix(m,end_col_OfM2-start_col_OfM2);
        for (int i = 0; i < m; i++) {
            for (int k = start_col_OfM2; k < end_col_OfM2; k++) {
                Numbers[i][k] = 0;
                for (int j = 0; j < m1.n; j++) {
                
                 Numbers[i][k] += m1.Numbers[i][j]*m2.Numbers[j][k];
                 temp.Numbers[i][k-start_col_OfM2] = Numbers[i][k];
                }
//                System.out.print(Numbers[i][k] + " ");
            }
//            System.out.println("");
        }
//        System.out.println("Result Matrix after Thread "+Thread.currentThread().getName());
//        temp.Print();
        return temp;
    }
    boolean SetNumbers(int []arr){
        if ((m*n) >arr.length) {
            return false;
        }
        int k = 0;
        for (int i = 0; i <m; i++) {
            for (int j = 0; j < n; j++) {
                    Numbers[i][j] = arr[k];
                    k++;
                }   
            
        }
        return true;
    }
    public void Print(){
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(Numbers[i][j] + " ");
            }
            System.out.println("");
        }
    }
    public synchronized void Print2(int start_col_OfM2,int end_col_OfM2){
        System.out.println("Out of Thread "+ Thread.currentThread().getName());
        for (int i = 0; i < m; i++) {
            for (int j = start_col_OfM2; j < end_col_OfM2; j++) {
                System.out.print(Numbers[i][j] + " ");
            }
            System.out.println("");
        }
    }
    public void Transpose(){
        int[][] temp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp[i][j]=Numbers[j][i];
            }
        }
        Numbers = temp;
        if (m!=n) {
            int k = m;
            m = n;
            n=k;
        }
    }
}
