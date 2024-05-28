/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.req1;
/**
 *
 * @author Peter
 */

public class req1 {
    public static void main(String[]args){
        //Initial Matrices
        Matrix m1 = new Matrix(3,3);
        Matrix m2 = new Matrix(3,2);
        Matrix i1 = new IdentityMatrix(2,2);
        Matrix i2 = new IdentityMatrix(3,3);
        //setting values
        int [] arr1 = {1,2,3,4,5,6,7,8,9};
        int [] arr2 = {1,2,3,4,5,6};
        int [] arr3 = {1,0,0,1};
        int [] arr4 = {1,0,0,0,1,0,0,0,1};
        if(m1.SetNumbers(arr1)){
            System.out.println("Matrix m1 3*3");
            m1.Print();
        }
        if(m2.SetNumbers(arr2)){
            System.out.println("Matrix m2 3*2");
            m2.Print();
        }
        if(i1.SetNumbers(arr3)){
            System.out.println("Identity Matrix I1 2*2");
            i1.Print();
        }
        if(i2.SetNumbers(arr4)){
            System.out.println("Identity Matrix I2 3*3");
            i2.Print();
        }
        if (!m2.SetNumbers(arr4)) {//in case of sendding an array that can't fill the matrix
            System.out.println("The size of the array is not enough");
        }
        //Transpose and Print functions
        m1.Transpose();
        System.out.println("This m1 after Transpose");
        m1.Print();
        
        m2.Transpose();
        System.out.println("This m2 after Transpose");
        m2.Print();
        
        System.out.println("This I1 after Transpose");
        i1.Transpose();
        i1.Print();
        
        System.out.println("This I2 after Transpose");
        i2.Transpose();
        i2.Print();
        
        //Add function
        Matrix m3 = m1.Add(i2);
        if(m3 != null){
            System.out.println("New Matrix After Adding The 2 matrices");
            m3.Print();
        }
        m3 = m1.Add(i1);
        if(m3 != null){
            System.out.println("New Matrix After Adding The 2 matrices");
            m3.Print();
        }
    }
}
