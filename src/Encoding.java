public class Encoding {

    private int[] y0;
    private int[] y1;

    public Encoding(int[] x) {
        findY0(x);
        findY1(x);
    }

    public int[] getY0() {
        return y0;
    }

    public int[] getY1() {
        return y1;
    }

    public void findY0(int[] x) {
        y0 = new int[x.length + 3];
        double[][] A0 = new double[x.length + 3][x.length + 3];
        double[][] newX = new double[x.length + 3][1];

        for (int i = 0; i < newX.length; i++) {
            if (i < x.length) {
                newX[i][0] = x[i];
            } else {
                newX[i][0] = 0;
            }
        }

        for (int i = 0; i < A0.length; i++) {
            for (int j = 0; j < A0[0].length; j++) {
                if(i == j) {
                    A0[i][j] = 1; 
                } else if (j == i-2) {
                    A0[i][j] = 1;
                } else if (j == i-3) {
                    A0[i][j] = 1;
                } else {
                    A0[i][j] = 0;
                }
            }
        }

        double[][] temp = MatrixOperations.multiply(A0, newX);

        for (int i = 0; i < temp.length; i++) {
            y0[i] = ((int) temp[i][0]) % 2;
        }

    }

    public void findY1(int[] x) {
        y1 = new int[x.length + 3];
        double[][] A1 = new double[x.length + 3][x.length + 3];
        double[][] newX = new double[x.length + 3][1];

        for (int i = 0; i < newX.length; i++) {
            if (i < x.length) {
                newX[i][0] = x[i];
            } else {
                newX[i][0] = 0;
            }
        }

        for (int i = 0; i < A1.length; i++) {
            for (int j = 0; j < A1[0].length; j++) {
                if(i == j) {
                    A1[i][j] = 1; 
                } else if(j == i-1) {
                    A1[i][j] = 1;
                } else if (j == i-3) {
                    A1[i][j] = 1;
                } else {
                    A1[i][j] = 0;
                }
            }
        }

        double[][] temp = MatrixOperations.multiply(A1, newX);

        for (int i = 0; i < temp.length; i++) {
            y1[i] = ((int) temp[i][0]) % 2;
        }

    }

    public String generateY(int[] y0, int[] y1) {
        String Y = "";
        for (int i = 0; i < y0.length; i++) {
            Y = Y + y0[i] + y1[i] + " ";
        }

        return (Y);
    }

}