import mpi.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;  // Import the File class
import java.util.Scanner;
//import java.lang.Math;

public class req3 {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, InterruptedException {

        MPI.Init(args);
        int[] matrix = new int[9];
        int rank = MPI.COMM_WORLD.Rank();
        int root=0;
        int [] subDet = new int[1];//sent
        int [] finalDet = new int [1];//received

        if(rank==root) {
            try {
                File myObj = new File("data.txt");
                Scanner myReader = new Scanner(myObj);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i * 3 + j] = myReader.nextInt();
                        /*we did that because Bcast function take array of contiguous
                         *and the 2D array doesn't guarantee to us that the elements are contiguous, so we can
                         * emulate the 2D array using 1D array as mat[i][j]= mat[i*N+j]
                         *  */
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            subDet[0] = 0; //34an al root m4 hy7sb sub determinant
            MPI.COMM_WORLD.Bcast(matrix, 0, 9, MPI.INT, 0) ;
        }
        else if(rank==1){
            MPI.COMM_WORLD.Bcast(matrix, 0, 9, MPI.INT, 0) ;
            subDet[0] = matrix[4]*matrix[8]-matrix[7]*matrix[5];
            subDet[0]*=matrix[0];
            System.out.println("The determinant is cal by process "+ rank + " = " + subDet[0]);
        }
        else if(rank==2){
            MPI.COMM_WORLD.Bcast(matrix, 0, 9, MPI.INT, 0) ;
            subDet[0] = matrix[3]*matrix[8]-matrix[6]*matrix[5];
            subDet[0]*=(-1 * matrix[1]);
            System.out.println("The determinant is cal by process "+ rank + " = " + subDet[0]);
        }
        else if(rank ==3){
            MPI.COMM_WORLD.Bcast(matrix, 0, 9, MPI.INT, 0) ;
            subDet[0] = matrix[3]*matrix[7]-matrix[4]*matrix[6];
            subDet[0]*=matrix[2];
            System.out.println("The determinant is cal by process "+ rank + " = " + subDet[0]);
        }
//        MPI.COMM_WORLD.Barrier();
        MPI.COMM_WORLD.Reduce(subDet, 0, finalDet,0,1,  MPI.INT, MPI.SUM, 0);
        if(rank ==0) {
            System.out.println("The determinant = "+finalDet[0]);
            if (finalDet[0] == 0) {
                System.out.println("A singular matrix");
            } else {
                System.out.println("An invertible matrix");
            }
        }
        MPI.Finalize();
    }
}
