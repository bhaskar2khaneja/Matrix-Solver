public class MatrixOperations {

    public static double[][] convertToTwoD(double[] b) {
        double[][] bTwoD = new double[b.length][1];
        for (int i = 0; i < b.length; i++) {
            bTwoD[i][0] = b[i];
        }

        return bTwoD;
    }

    public static double[] convertToOneD(double[][] xTwoD) {
        double[] x = new double[xTwoD.length];
        for (int i = 0; i < xTwoD.length; i++) {
            x[i] = xTwoD[i][1];
        }

        return x;
    }

    public static double[][] makeHilbertMatrix(int n) {
        double[][] hilbertMatrix = new double[n][n];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <=n; j++) {
                hilbertMatrix[i-1][j-1] = 1.0/(i+j-1);
            }
        }

        return hilbertMatrix;
    }

    public static double euclideanNorm (double[] arr) {
        double sum = 0.0;
        for (double i : arr) {
            sum += i*i;
        }
        return Math.sqrt(sum);
    }

    public static double infinityNorm(double[][] A) {
        double max = -1;
        for (int i = 0; i < A.length; i++) {
            double sum = 0;
            for(int j = 0; j < A.length; j++) {
                sum += Math.abs(A[i][j]);
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    public static double[][] makeIdentity(double[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                A[i][i] = 1;
                if (i != j) {
                    A[i][j]=0;
                }
            }
        }
        return A;
    }

    public static double modulus(double[] A) {
        double sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i]*A[i];
        }
        return Math.sqrt(sum);
    }

    public static double[] divide(double[] A, double num) {
        double[] result = new double[A.length];
        for (int i = 0; i < A.length; i++) {
            result[i] = A[i]/num;
        }

        return result;
    }

    public static double[][] divide(double[][] A, double num) {
        double[][] result = new double[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j =0 ;j < A[0].length; j++) {
                result[i][j] = A[i][j]/num;
            }
        }

        return result;
    }

    public static double[][] subtract(double[][] A, double[][] B) {
        double[][] result = new double[A.length][A.length];
        for (int i = 0;i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                double temp = 0;
                temp = A[i][j] - B[i][j];
                result[i][j] = temp;
            }
        }
        return result;
    }

    public static double[][] multiply(double[][] A, double[][] B) {
        double[][] result = new double[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++){
                double sum = 0.0;
                for (int k = 0; k < A[0].length; k++) {
                    sum += A[i][k]*B[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }

    public static void display(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print((matrix[i][j]) + "  ");
            }
        System.out.println();
        }
    }

    public static void display(double[] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(matrix[i]);
        }
    }

    public static void display(int[] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(matrix[i]);
        }
    }

    public static double[][] transpose(double[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = i; j >=0; j--) {
                if (i != j) {
                    double temp = A[j][i];
                    A[j][i] = A[i][j];
                    A[i][j] = temp;
                }
            }
        }
        return A;
    }

    public static double[] forwardSubstitution(double[][] A, double[] b) {
        double[] solution = new double[b.length];
        double sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum = 0;
            for (int k = 0; k < i; k++) {
                sum += A[i][k]*solution[k];
            }
            solution[i] = (b[i] - sum)/A[i][i];
        }

        return solution;
    }

    public static double[] backwardSubstitution(double[][] A, double[] b) {
        double[] solution = new double[b.length];
        double sum = 0;
        for (int i = A.length - 1; i >= 0;i--) {
            sum = 0;
            for (int k = A.length - 1; k > i; k--) {
                sum += A[i][k]*solution[k];
            }
            solution[i] = (b[i] - sum)/A[i][i];
        }

        return solution;
    }

    public static void copying(double[][] A, double[][] originalA) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                originalA[i][j] = A[i][j];
            }
        }
    }

}