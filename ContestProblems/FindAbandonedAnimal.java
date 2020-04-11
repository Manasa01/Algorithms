import java.util.*;

public class FindAbandonedAnimal {

    public static class DirectedGraph {
        private int total_vertices;
        private ArrayList<LinkedList<Integer>> adjList = new ArrayList<>();
        public static ArrayList<String> inventoryList = new ArrayList<>();

        public DirectedGraph(int V) {
            total_vertices = V;
            for (int i = 0; i < V; i++) {
                adjList.add(i, new LinkedList<>());
                inventoryList.add(i, "");
            }
        }

        public void addEdge(int v, int w) {
            LinkedList ll = adjList.get(v);

            ll.add(w);

        }

        public void addItem(int v, String item) {
            inventoryList.set(v, inventoryList.get(v) + " " + item);
        }

        public int V() {
            return total_vertices;
        }

        public Iterable<Integer> getAdjacent(int v) {
            return adjList.get(v);
        }

        public String getInv(int v) {
            return inventoryList.get(v);
        }

    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int noOfStores = s.nextInt();
        DirectedGraph dg = new DirectedGraph(noOfStores);
        HashMap<String, ArrayList<Integer>> mapVisit = new HashMap<>();
        int paths = s.nextInt();
        s.nextLine();
        int previous = -1;
        ArrayList<Integer> visitedIndex;
        for (int i = 0; i < paths; i++) {
            String[] str = s.nextLine().split(" ");
            int current = Integer.parseInt(str[0]);

            if (i > 0 && previous != current) {
                dg.addEdge(previous, current);
            }
            dg.addItem(current, str[1]);
            previous = current;

            visitedIndex = mapVisit.get(str[1]);
            if (visitedIndex == null) {
                ArrayList<Integer> tmp = new ArrayList<>();
                tmp.add(current);
                mapVisit.put(str[1], tmp);

            } else {
                visitedIndex.add(current);
            }
        }

        int itemsBought = s.nextInt();
        s.nextLine();

        int countPath = 0;

        String[] items = new String[itemsBought];
        boolean[] marked = new boolean[itemsBought];
        for (int i = 0; i < itemsBought; i++) {
            items[i] = s.nextLine();
        }

        //for all vertexs with first item
        for (int v : mapVisit.get(items[0])) {

            marked[0] = true;
            //find a path from each start vertex to rest of the items, count the paths
            String temp = dg.getInv(v);
            for (int i = 1; i < itemsBought; i++) {
                if (!marked[i]) {
                    marked[i] = temp.contains(items[i]);
                }


            }

            for (int adj : dg.getAdjacent(v)) {
                temp = dg.getInv(adj);
                for (int i = 1; i < itemsBought; i++) {
                    if (!marked[i]) {
                        marked[i] = temp.contains(items[i]);
                    }

                }
            }
            int j;
            for (j = 0; j < itemsBought; j++) {
                if (marked[j] == false) break;
            }
            if (j == itemsBought) {
                countPath++;
            }

        }
        if (countPath > 1) {
            System.out.println("ambiguous");
        } else if (countPath == 1) {
            System.out.println("unique");
        } else {
            System.out.println("impossible");
        }

    }
}
