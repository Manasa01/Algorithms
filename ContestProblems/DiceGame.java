import java.util.Scanner;

public class DiceGame {

    /*

    //assumption:observation: since numbers are consecutive natural numbers, all values between min_sum and max_sum can be obtained

    */
    public static void findWinner(int a1, int b1, int a2, int b2, int c1, int d1, int c2, int d2) {

        //player1 - Gunnar
        int max_sum_1 = b1 + b2;
        int min_sum_1 = a1 + a2;
        int count1 = 0;


        //player2 - Emma
        int max_sum_2 = d1 + d2;
        int min_sum_2 = c1 + c2;
        int count2 = 0;

        count1 = findCount(max_sum_1, max_sum_2, min_sum_1, min_sum_2);
        count2 = findCount(max_sum_2, max_sum_1, min_sum_2, min_sum_1);

        if(count1 < count2) System.out.println("Emma");
        else if(count1 > count2) System.out.println("Gunnar");
        else System.out.println("Tie");

    }

    private static int findCount(int max1, int max2, int min1, int min2) {
        int max_term = 0;
        int min_term = 0;
        while (max1 <= max2) max2--;
        while (min1 <= min2) min1++;
        max_term = max2 - min2 + 1;
        min_term = min1 - min2;

        int sum = (max_term * ( max_term  +1) )/ 2;
        while(min_term > 1){
            sum -= min_term - 1;
            min_term--;
        }
        return sum;
    }
    public static void main(String[] args){
        Scanner oScan = new Scanner(System.in);

        int a1 = oScan.nextInt();
        int b1 = oScan.nextInt();
        int a2 = oScan.nextInt();
        int b2 = oScan.nextInt();
        int c1 = oScan.nextInt();
        int d1 = oScan.nextInt();
        int c2 = oScan.nextInt();
        int d2 = oScan.nextInt();
        findWinner(a1, b1, a2, b2, c1, d1, c2, d2);
    }
}
