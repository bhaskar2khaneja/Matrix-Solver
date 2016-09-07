public class Lu_fact {

    private double[][] L;
    private double[][] U;
    private double error;

    public Lu_fact(double[][] A) {
        LUFactorization(A);
    }

    public double[][] getL() {
        return L;
    }

    public double[][] getU() {
        return U;
    }

    public double getLU_Error() {
        return error;
    }

    public void LUFactorization(double[][] A) {
        double[][] originalA = new double[A.length][A.length];
        for (int i = 0; i < A.length; i++) {
            for(int j = 0; j < A.length; j++) {
                originalA[i][j] = A[i][j];
            }
        }
        MatrixOperations.copying(A, originalA);
        int n = A.length;
        U = A;
        L = new double[n][n];
        for(int j = 0; j < n; j++) {
            double pivot = U[j][j];
            if (pivot == 0 && pivot != U[n-1][n-1]) {
                System.out.println("LU Factorization does not exist.");
                System.exit(0);
            }
            L[j][j] = 1;
            for(int i = j + 1; i < n; i++) {
                double factor = U[i][j]/pivot;
                L[i][j] = factor;
                L[j][i] = 0;
                for (int k = 0; k < n; k++) {
                    U[i][k] -= U[j][k] * factor;
                } 
            }
        }

        double[][] LUminusA = MatrixOperations.subtract(MatrixOperations.multiply(L,U), originalA);
        error = MatrixOperations.infinityNorm(LUminusA);

        System.out.println("L:");
        MatrixOperations.display(L);
        System.out.println("U:");
        MatrixOperations.display(U);
        System.out.println("InfinityNorm(LU-A)" + error);
    }        


    public static void main(String[] args) {
        double[][] test = MatrixOperations.makeHilbertMatrix(4);
        MatrixOperations.display(test);
        Lu_fact obj = new Lu_fact(test);
        System.out.println("LU: ");
        MatrixOperations.display(MatrixOperations.multiply(obj.getL(),obj.getU() ));
        System.out.println("error: ");
        System.out.println(obj.getLU_Error());
    }

}
        