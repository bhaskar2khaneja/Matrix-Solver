public class Qr_fact_givens {

    private double[][] Q;
    private double[][] R;
    private double error;

    public Qr_fact_givens(double[][] A) {
        QR_givens(A);
    }

    public double[][] getQ() {
        return Q;
    }

    public double[][] getR() {
        return R;
    }

    public double getQR_givens_Error() {
        return error;
    }

    public void QR_givens(double[][] A) {

        double[][] originalA = new double[A.length][A.length];
        MatrixOperations.copying(A, originalA);

        int n = A.length;
        int numOfIterations = ((n-1)*n)/2;
        double[][][] collectionOfG = new double[numOfIterations][n][n]; 
        int numOfG = 0;
        for (int i = 0; i < n - 1; i++) {
            double a = A[i][i];
            for (int j = i + 1; j < n; j++) {
                a = A[i][i];
                double b = A[j][i];
                if (b == 0) {
                    continue;
                }
                double cost = a/Math.sqrt(a*a + b*b);
                double sint = -b/Math.sqrt(a*a + b*b);
                double[][] G = new double[n][n];
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n;l++) {
                        if (k == i && l == i) {
                            G[k][l] = cost;
                        } else if(k==i && l==j) {
                            G[k][l] = -sint;
                        } else if(k==j && l==i) {
                            G[k][l] = sint;
                        } else if(k==j && l==j) {
                            G[k][l] = cost;
                        } else if(k==l) {
                            G[k][l] = 1;
                        } else {
                            G[k][l] = 0;
                        }
                    }
                }
                collectionOfG[numOfG] = G;
                numOfG++;

                A = MatrixOperations.multiply(G, A);
            }
        }

        R = A;

        Q = new double[n][n];
        Q = MatrixOperations.makeIdentity(Q);
        for(int i = 0; i<numOfG; i++) {
            Q = MatrixOperations.multiply(Q, MatrixOperations.transpose(collectionOfG[i]));
        }

        double[][] QRminusA = MatrixOperations.subtract(MatrixOperations.multiply(Q,R), originalA);
        error = MatrixOperations.infinityNorm(QRminusA);
    }

    public static void main(String[] args) {
        double[][] test = MatrixOperations.makeHilbertMatrix(4);
        MatrixOperations.display(test);
        Qr_fact_givens obj = new Qr_fact_givens(test);
        System.out.println("QR: ");
        MatrixOperations.display(MatrixOperations.multiply(obj.getQ(),obj.getR() ));
        System.out.println("error: ");
        System.out.println(obj.getQR_givens_Error());
        MatrixOperations.display(obj.getQ());
        System.out.println();
        MatrixOperations.display(obj.getR());
    }

} 