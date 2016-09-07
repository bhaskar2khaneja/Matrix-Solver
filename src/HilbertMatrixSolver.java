public class HilbertMatrixSolver {

    private double[][] H;
    private double[] b;

    public HilbertMatrixSolver(int n) {
        H = MatrixOperations.makeHilbertMatrix(n);
        b = new double[n];
        for (int i = 0; i < n; i++) {
            b[i] = Math.pow(0.1, n/3.0);
        }
    }

    public void solveUsingLU() {
        Solve_lu_b obj1 = new Solve_lu_b();
        double[] x = obj1.solver_LU(H, b);
        double[] HXminusB = MatrixOperations.convertToOneD(MatrixOperations.subtract(
                MatrixOperations.multiply(H, MatrixOperations.convertToTwoD(x)), MatrixOperations.convertToTwoD(b)));
        System.out.println("The solution x is: ");
        MatrixOperations.display(x);

        Lu_fact obj2 = new Lu_fact(H);
        double[][] LUminusH = MatrixOperations.subtract(MatrixOperations.multiply(obj2.getL(), obj2.getU()), H);

        System.out.println("The error ||LU - H|| (or its infinity norm): " + MatrixOperations.infinityNorm(LUminusH));
        System.out.println("The error ||Hx - b|| (or its euclidean norm): " + MatrixOperations.euclideanNorm(HXminusB));

    }

}