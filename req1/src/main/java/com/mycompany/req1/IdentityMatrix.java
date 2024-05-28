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
public class IdentityMatrix extends Matrix {
    public IdentityMatrix(int i ,int j){
        super(i,j);
        if (i!=j) {
            System.out.println("This not a squeare matrix");
        }
    }
    
    @Override
    public boolean SetNumbers(int []arr){
        boolean flag =super.SetNumbers(arr);
        boolean isIdentity = true;
        if (m!=n) {//al identity lazm tkon square matrix
            System.out.println("The identity matrix is a square matrix");
            return false;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i==j && Numbers[i][j]!=1) {
                    isIdentity= false;
                }
                else if(i!=j && Numbers[i][j]!=0)
                {
                    isIdentity=false;
                }
            }
        }
        return (flag && isIdentity);
    }
    @Override
    public void Transpose(){
    }
}
