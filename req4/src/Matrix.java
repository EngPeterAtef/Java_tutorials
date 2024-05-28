public class Matrix {
    protected int m;
    protected int n;
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
    //This function to calculate the cofactors of the each sub matrix in the original matrix
    private void getCofactor(int mat[][], int temp[][], int p, int q, int n)
    {
        int i = 0, j = 0;

        // Looping for each element of the matrix
        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {
                // Copying into temporary matrix
                // only those element which are
                // not in given row and column
                if (row != p && col != q)
                {
                    temp[i][j++] = mat[row][col];
                    // Row is filled, so increase row index and reset col index
                    if (j == n - 1)
                    {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }
//we can only calculate the determinant only if the matrix is square
    private int determinantRec(int mat[][], int r)
    {
        int D = 0; // Initialize result

        // Base case : if matrix contains single element
        if (r == 1)
            return mat[0][0];

        // To store cofactors
        int temp[][] = new int[n][m];

        // To store sign multiplier
        int sign = 1;

        // Iterate for each element of first row
        for (int f = 0; f < r; f++)
        {
            // Getting Cofactor of mat[0][f]
            getCofactor(mat, temp, 0, f, r);
            D += sign * mat[0][f ]* determinantRec(temp, r - 1);

            // terms are to be added with
            // alternate sign
            sign = -sign;
        }

        return D;
    }
    public int Determinant(){
        if (n!=m){
            System.out.println("Can't calculate it");
            return -1;
        }
        return determinantRec(Numbers,n);
    }
}
