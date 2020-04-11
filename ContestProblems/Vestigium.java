import java.io.*;
import java.util.Scanner;

public class Vestigium {

    private static int trace(int[][] A, int N) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += A[i][i];
        }
        return sum;

    }

    private static int findRepeatedRows(int[][] A, int N) {
        int r = 0;
        boolean[] check;
        for (int i = 0; i < N; i++) {
            check = new boolean[N];
            for (int j = 0; j < N; j++) {
                if (check[A[i][j] - 1]) {
                    r++;
                    break;
                }
                check[A[i][j] - 1] = true;
            }
        }
        return r;
    }

    private static int findRepeatedCols(int[][] A, int N) {
        int c = 0;
        boolean[] check;
        for (int i = 0; i < N; i++) {
            check = new boolean[N];
            for (int j = 0; j < N; j++) {
                if (check[A[j][i] - 1]) {
                    c++;
                    break;
                }
                check[A[j][i] - 1] = true;
            }
        }
        return c;
    }


    public static void main(String[] args)throws IOException {
//        File file = new File("/home/manasa01/Desktop/testcase");
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        int num =  Integer.parseInt(bufferedReader.readLine().trim());
        int sum = 0;
        int r = 0;
        int c = 0;
        int counter = 0;
        while (num > 0) {


            counter++;

            int N = Integer.parseInt(bufferedReader.readLine().trim());

            int[][] A = new int[N][N];

            for (int j = 0; j < N; j++) {
                String[] arrStr = bufferedReader.readLine().split(" ");
                for (int i = 0; i < N; i++) {
                    A[j][i] = Integer.parseInt(arrStr[i]);
                }
            }

            sum = trace(A, N);
            r = findRepeatedRows(A, N);
            c = findRepeatedCols(A, N);
            //System.out.printf("\n");
//            System.out.printf("Case #%d: %d %d %d",counter, sum, r, c);
            System.out.println("\nCase #" + counter + " " + sum + " " + r + " " + c);
            num--;
        }

        bufferedReader.close();
    }
}
