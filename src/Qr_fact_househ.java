public class Qr_fact_househ {

    private double[][] Q;
    private double[][] R;
    private double error;

    public Qr_fact_househ(double[][] A) {
        QR_householder(A);
    }

    public double[][] getQ() {
        return Q;
    }

    public double[][] getR() {
        return R;
    }

    public double getQR_househ_Error() {
        return error;
    }

    public void QR_householder(double[][] A) {

        double[][] originalA = new double[A.length][A.length];
        MatrixOperations.copying(A, originalA);
        

        double[][] H;
        int n = A.length;
        double[][][] collectionOfH = new double[n-1][n][n];
        R = A;
        for (int i = 0; i < n - 1; i++) {
            double[] selection = new double[n-i];
            for (int j = i, k = 0; j < n; j++, k++) {
                selection[k] = R[j][i];
            }
            double[] u = selection;
            u[0] += MatrixOperations.modulus(selection);
            u = MatrixOperations.divide(u, MatrixOperations.modulus(u));
            H = calculateH(u, n);
            collectionOfH[i] = H;
            R = MatrixOperations.multiply(H, R);
        }

        Q = new double[n][n];
        Q = MatrixOperations.makeIdentity(Q);
        for (int i = 0; i < collectionOfH.length; i++) {
            Q = MatrixOperations.multiply(Q, collectionOfH[i]);
        }

        double[][] QRminusA = MatrixOperations.subtract(MatrixOperations.multiply(Q,R), originalA);
        error = MatrixOperations.infinityNorm(QRminusA);
    }

    public double[][] calculateH(double[] u, int n) {
        double[][] I = new double[u.length][u.length];
        I = MatrixOperations.makeIdentity(I);
        
        double[][] twoUUt = new double[u.length][u.length];
        for (int i = 0; i < u.length; i++) {
            for (int j = 0; j < u.length; j++) {
                twoUUt[i][j] = 2*u[i]*u[j];
            }
        }

        double[][] H = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < (n - u.length) || j < (n - u.length)) {
                    if (i == j) {
                        H[i][j] = 1;
                    } else {
                        H[i][j] = 0;
                    }
                } else {
                    double[][] temp = MatrixOperations.subtract(I, twoUUt);
                    H[i][j] = temp[i-(n-u.length)][j-(n-u.length)];
                }
            }
        }

    return H;
    }

    public static void main(String[] args) {
        double[][] test = MatrixOperations.makeHilbertMatrix(4);
        MatrixOperations.display(test);
        Qr_fact_househ obj = new Qr_fact_househ(test);
        System.out.println("QR: ");
        MatrixOperations.display(MatrixOperations.multiply(obj.getQ(),obj.getR() ));
        System.out.println("error: ");
        System.out.println(obj.getQR_househ_Error());
        MatrixOperations.display(obj.getQ());
        System.out.println();
        MatrixOperations.display(obj.getR());
    }

}