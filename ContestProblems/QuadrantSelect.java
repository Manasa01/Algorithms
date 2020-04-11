import java.util.Scanner;

public class QuadrantSelect {


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int result = 0;
        if(x > 0){
            result = y > 0 ? 1: 4;
        }else{
            result = y > 0 ? 2: 3;
        }
        System.out.printf("%d", result);

    }
}
