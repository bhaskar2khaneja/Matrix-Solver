import java.util.Scanner;

public class Matrix {

    int size;
    double[][] mat;

    public Matrix(int size) {
        this.size = size;
        mat = new double[size][size];
    }

    public void input() {
        Scanner keyboard = new Scanner(System.in);
        for (int i = 0;i<size;i++) {
            for (int j = 0; j < size; j++) {
                System.out.println("Enter the [" + i + "][" + j + "] th element: ");
                mat[i][j] = keyboard.nextDouble();
            }
        }
    }

    public void display() {
        for (int i = 0;i < size; i++) {
            for (int j = 0; j < size; j++) {
                String format = "%12.6f";
                System.out.printf(format, mat[i][j]);
            }
            System.out.println();
        }
    } 

    public int getSize() {
        return size;
    }
}