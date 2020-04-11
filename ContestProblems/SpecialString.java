
import java.io.IOException;
import java.util.Scanner;

public class SpecialString {


    static int[][] resultArray;

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                resultArray[i][j] = s.charAt(i) == s.charAt(j) ? 1 : 0;
            }
        }

        long count = 0;
        int countZero = 0;
        boolean firstOneAfterZero = false;
        int countOnes = 0;
        for (int j = 0; j < n; j++) {
            countZero = 0;
            firstOneAfterZero = false;
            countOnes = 0;
            for (int i = j; i >= 0; i--) {
                if (resultArray[i][j] == 1 && !firstOneAfterZero && countZero <= 1) {
                    count++;
                    countOnes++;
                    if (j != i && !firstOneAfterZero && countZero == 1) {
                        firstOneAfterZero = true;
                    }

                } else {
                    countZero++;
                }
            }
            if (firstOneAfterZero && countOnes > 2) {
                count--;
            }
        }
        return count;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        String s = "wasnnypopizuvczxkkblaxumezspbjiavazgowojmvmxsbaxgpnsfjihiksbxyluxeoiuylzpxwgnxnvvzegjuptsrwcpfkmrxolbfiydkddjugkekndjtxlsfefuadkrrlmzjifmcpztyllbtsoeedgfeovbgga";
        int n = 32;
//        int n = scanner.nextInt();
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        String s = scanner.nextLine();
        resultArray = new int[n][n];
        long result = substrCount(n, s);
System.out.println(result);
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}



