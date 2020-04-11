import com.sun.source.tree.Tree;

import java.util.*;

public class AbandonedAnimal {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int market_num = s.nextInt();
        int visited_count = s.nextInt();
        s.nextLine();
        HashMap<String, TreeSet<Integer>> mapVisit = new HashMap<>();
        for (int i = 0; i < visited_count; i++) {
            String[] temp = s.nextLine().split(" ");
            TreeSet<Integer> visitedIndex = mapVisit.get(temp[1]);
            if (visitedIndex == null) {
                TreeSet<Integer> tmp = new TreeSet<>();
                tmp.add(i);
                mapVisit.put(temp[1], tmp);

            } else {
                visitedIndex.add(i);
            }
        }
        int items_bought = s.nextInt();
        s.nextLine();
        //previous set of visited index
        TreeSet<Integer> previous = null;
        boolean Impossible = false;
        long max_paths = 0;
        for (int i = 0; i < items_bought; i++) {
            //set of visit indexes for a given item
            TreeSet<Integer> current = mapVisit.get(s.nextLine());
            //if not the first item
            if (previous != null) {
                //find the possible paths between previous and current
                long count = 0;
                ArrayList<Integer> removeVals = new ArrayList<>();
                for (int val : current) {
                    long tmpCount = previous.parallelStream().filter(e -> e < val).count();
                    if (tmpCount == 0) {
                        removeVals.add(val);
                    }
                    count += tmpCount;
                }
                //if no paths between previous and current - Impossible
                if (count == 0) {
                    System.out.println("impossible");
                    Impossible = true;
                    break;
                }
                //remove nodes with no paths
                for (int val : removeVals) {
                    current.remove(val);
                }
                if (count > max_paths) {
                    max_paths = count;
                }

            }
            previous = current;
        }
        if(!Impossible) {
            //if max_paths between any two pairs is greater than 1 then ambiguous
            if (max_paths > 1) {
                System.out.println("ambiguous");
            } else {
                System.out.println("unique");
            }
            //else its unique
        }
    }
}
