import java.net.HttpRetryException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PlantingTrees {

    public static void main(String[] args) {
        Scanner oScan = new Scanner(System.in);
        PriorityQueue<Integer> seeds = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        int n = oScan.nextInt();
        for (int i = 0; i < n; i++) {
            seeds.add(oScan.nextInt());
        }
        int max_days = 0;
        int no_of_days = 1;
        int temp;
        while (!seeds.isEmpty()) {
            temp = seeds.remove() + no_of_days;
            if (temp >= max_days) {
                max_days = temp;
            }
            no_of_days++;
        }
        System.out.println(max_days + 1);
    }
}
