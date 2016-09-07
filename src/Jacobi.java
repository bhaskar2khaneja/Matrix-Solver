public class Jacobi {

    private double[] jacobiX;
    private void jacobiFinder(double[][] A, double[] y, double[] x, double tol) {
        double[][] R = new double[A.length][A.length];
        double[][] inverseD = new double[A.length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (i == j) {
                    R[i][j] = 0;
                    inverseD[i][j] = 1 / (A[i][j]);
                } else {
                    R[i][j] = -A[i][j];
                    inverseD[i][j] = 0;
                }
            }
        }
        double[] xN = null;
        double[] errorM = null;
        double error = 1000000000;
        int j = 0;
        do {
            xN = multiply(inverseD, add(multiply(R, x), y));
            errorM = subtract(xN, x);
            x = xN;
            error = MatrixOperations.euclideanNorm(errorM);
            j++;
        } while (tol < error && j <= 1000);
        if (j > 1000) {
            System.out.print("Error tolerance has not been achieved after 1000 iterations.");
        } else {
            for (double i : x) {
                System.out.println(Math.abs(i%2));
            }
            System.out.println("Kmax = " + j);
        }
    }

    private double euclideanNorm (double[] arr) {
        double sum = 0.0;
        for (double i : arr) {
            sum += i*i;
        }
        return Math.sqrt(sum);
    }

    private double[] multiply (double[][] A, double[] x) {
        double[] result = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            double sum = 0.0;
            for (int j = 0; j < A.length; j++) {
                sum += A[i][j] * x[j];
            }
            result[i] = sum;
        }
        return result;
    }

    private double[] add(double[] A, double[] B) {
        double[] result = new double[A.length];
        for (int i = 0; i < A.length; i++) {
            result[i] = A[i] + B[i];
        }
        return result;
    }

    private double[] subtract(double[] A, double[] B) {
        double[] result = new double[A.length];
        for (int i = 0; i < A.length; i++) {
            result[i] = A[i] - B[i];
        }
        return result;
    }

    public static void main(String[] args) {
        double[] y = {1, 0, 0, 0, 1, 0, 1, 0};
        double[] x = {0, 0, 0,0,0,0,0,0};
        double[][] A0 = {{1,0,0,0,0,0,0,0},
                         {0,1,0,0,0,0,0,0},
                         {1,0,1,0,0,0,0,0},
                         {1,1,0,1,0,0,0,0},
                         {0,1,1,0,1,0,0,0},
                         {0,0,1,1,0,1,0,0},
                         {0,0,0,1,1,0,1,0},
                         {0,0,0,0,1,1,0,1}};
        double tol = 0.00000001;
        Jacobi obj = new Jacobi();
        obj.jacobiFinder(A0, y, x, tol);
    }
}