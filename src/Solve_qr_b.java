public class Solve_qr_b {

    public static double[] solver_QR_househ(double[][] A, double[] b) {
        Qr_fact_househ obj = new Qr_fact_househ(A);
        double[][] bTwoD = MatrixOperations.convertToTwoD(b);
        double[][] yTwoD = MatrixOperations.multiply(MatrixOperations.transpose(obj.getQ()), bTwoD);
        double[] x = MatrixOperations.backwardSubstitution(obj.getR(), MatrixOperations.convertToOneD(yTwoD));
        return x;
    }

    public static double[] solver_QR_givens(double[][] A, double[] b) {
        Qr_fact_givens obj = new Qr_fact_givens(A);
        double[][] bTwoD = MatrixOperations.convertToTwoD(b);
        double[][] yTwoD = MatrixOperations.multiply(MatrixOperations.transpose(obj.getQ()), bTwoD);
        double[] x = MatrixOperations.backwardSubstitution(obj.getR(), MatrixOperations.convertToOneD(yTwoD));
        return x;
    }

}