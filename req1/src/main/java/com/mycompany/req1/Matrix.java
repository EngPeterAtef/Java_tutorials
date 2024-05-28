/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.req1;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
/**
 *
 * @author Peter
 */
public class Matrix implements Addable {
    protected int m;
    protected int n;
//    private List<T[]> Numbers = new ArrayList<T[]>();
    protected int[][] Numbers;
    public Matrix(int i,int j){
        m = i;
        n = j;
        Numbers = new int[m][n];
    }
    @Override
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
