public class Solve_lu_b {

    public static double[] solver_LU(double[][] A, double[] b) {
        Lu_fact obj = new Lu_fact(A);
        double[] y = MatrixOperations.forwardSubstitution(obj.getL(), b);
        double[] x = MatrixOperations.backwardSubstitution(obj.getU(), y);
        return x;
    }

    public static void main(String[] args) {
        double[][] test = {{1.0, 1.0/2,1.0/3,1.0/4}, {1.0/2,1.0/3,1.0/4,1.0/5},
                {1.0/3,1.0/4,1.0/5, 1.0/6}, {1.0/4,1.0/5, 1.0/6, 1.0/7}};
        double[] b = {0.0464159, 0.0464159, 0.0464159, 0.0464159};
        MatrixOperations.display(solver_LU(test, b));
    }

}