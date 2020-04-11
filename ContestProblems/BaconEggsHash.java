import java.util.*;

public class BaconEggsHash {


    public static void main(String[] args) {
        Scanner oScan = new Scanner(System.in);

        int n = oScan.nextInt();
        oScan.nextLine();
        //System.out.println(n);
        while (n > 0) {
            TreeMap<String, PriorityQueue<String>> customerOrders = new TreeMap<>();

            for (int j = 0; j < n ; j++) {
                String orderLine = oScan.nextLine();
               // System.out.println(orderLine);
                String[] orders = orderLine.split(" ");


                for (int i = 1; i < orders.length; i++) {
                    String key = orders[i];

                    PriorityQueue<String> pq = customerOrders.get(key);
                    if (pq == null) {
                        PriorityQueue<String> customer = new PriorityQueue<>();
                        customer.add(orders[0]);
                        customerOrders.put(key, customer);
                    } else {
                        pq.add(orders[0]);
                        customerOrders.put(key, pq);
                    }

                }

            }
            n = oScan.nextInt();
            oScan.nextLine();
            //print result
             for(String key: customerOrders.keySet()){
                 PriorityQueue<String> pq = customerOrders.get(key);
                 System.out.print("\n" + key + " ");
                 while(!pq.isEmpty()){
                     System.out.print(pq.remove() + " ");
                 }
             }
            System.out.println("");
        }
    }
}
