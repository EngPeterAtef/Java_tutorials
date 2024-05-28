package com.mycompany.req2;
import java.util.Scanner;

public class Multiply implements Runnable{
    private final Matrix A;
    private final Matrix B;
    private Matrix C;//result
    public Multiply(Matrix m1,Matrix m2 , Matrix res){
        A = m1;
        B=m2;
        C = res;
    }
    @Override
    public void run() {
        Matrix t;
        if (Thread.currentThread().getName().equals("1")){
             t = C.multiply(A,B,0,B.n/2);
//            while (!Thread.currentThread().isInterrupted()){}
//            System.out.println("Thread 1 output");
//            t.Print();
            C.Print2(0,B.n/2);
        }
        else {
             t = C.multiply(A,B,B.n/2,B.n);
//            while (!Thread.currentThread().isInterrupted()){}
//            System.out.println("Thread 2 output");
//            t.Print();
            C.Print2(B.n/2,B.n);
        }
    }
    public static void main(String[]args) throws InterruptedException {
        int x,y;
        int rX,rY;//size of the result matrix
        int []arr ;//array to take the input in
        Scanner in = new Scanner(System.in);
        //data for matrix A
        System.out.println("Enter The size of matrix A in this format: 'row col' ");
        x = in.nextInt();
        y = in.nextInt();
        rX = x;
        Matrix A = new Matrix(x,y);
        arr = new int[x*y];
        System.out.println("Enter The data of Matrix A");
        for (int i= 0;i<arr.length;i++ ){
            arr[i] = in.nextInt();
        }
        A.SetNumbers(arr);//setting the data in the matrix
        //Data of Matrix 2
        System.out.println("Enter The size of matrix B in this format: 'row col' ");
        x = in.nextInt();
        y = in.nextInt();
        rY = y;
        Matrix B = new Matrix(x,y);
        arr = new int[x*y];
        System.out.println("Enter The data of Matrix B");
        for (int i= 0;i<arr.length;i++ ){
            arr[i] = in.nextInt();
        }
        B.SetNumbers(arr);//setting the data in the matrix

        Matrix C = new Matrix(rX,rY);//result
//Multiply objects
        Runnable r1  =new Multiply(A,B,C);
        Runnable r2  =new Multiply(A,B,C);
//threads
        Thread t1 = new Thread(r1); t1.setName("1");
        Thread t2 = new Thread(r2); t2.setName("2");

//        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
//sending interrupt signal so that t1 start printing
//        t1.interrupt();
        t1.join();
//        t2.interrupt();//sending interrupt signal so that t2 start printing after t1 finished
        t2.join();

//        long time = System.currentTimeMillis() - start;
//        System.out.println(time);
        System.out.println("Matrix A :");
        A.Print();
        System.out.println("Matrix B :");
        B.Print();
        System.out.println("The result of Multiplication");
        C.Print();

    }
}
