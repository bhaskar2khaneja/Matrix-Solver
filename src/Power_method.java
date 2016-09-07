public class Power_method {

    public void eigenvaluePowerMethod(double[][] A, double tol, double[] x) {
        double[][] newX = new double[x.length][1];
        double[][] newX2 = new double[x.length][1];
        double eigenvalue = x[0];
        for (int i = 0; i < newX.length; i++) {
            newX[i][0] = x[i];
        }
        double error;
        int k = 0;
        do {
            newX2 = MatrixOperations.multiply(A, newX);
            newX = MatrixOperations.divide(newX2, newX[0][0]);
            double newEigenvalue = newX[0][0];
            error = newEigenvalue - eigenvalue;
            eigenvalue = newEigenvalue;
            ++k;
        } while(Math.abs(error) >= tol && k <= 1000);

        if (k > 1000) {
            System.out.println("Method doesn’t converge after 1000 iterations");
        }

        newX = MatrixOperations.divide(newX, eigenvalue);
        MatrixOperations.display(newX);
        System.out.println(eigenvalue);
    }

    public static void main(String[] args) {
        double[][] A = {{2,-12},{1,-5}};
        double tol = 0.01;
        double[] x = {1,1};
        Power_method obj = new Power_method();
        obj.eigenvaluePowerMethod(A, tol, x);
    }

}