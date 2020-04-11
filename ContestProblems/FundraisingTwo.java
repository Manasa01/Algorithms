import java.util.*;

public class FundraisingTwo {

    //structure of arraylist for list of tables
    static List<List<Integer>> listTables = new ArrayList<List<Integer>>();
    //list of total number of guests in each table
    static List<Integer> X = new ArrayList<Integer>();
    static int[][] count_request;
    static int[][] charm;

    static int[] sortTablesDescending(int t) {
        Map<Integer, Integer> tempMap = new HashMap<Integer, Integer>();

        for (int j = 0; j < t; j++) {
            //sort generosity_factor within a table
            Collections.sort(listTables.get(j), Collections.reverseOrder());
            //find the sum of generosity_factor in a table
            int sum = 0;
            for (int val : listTables.get(j)) {
                sum += val;
            }
            tempMap.put(sum, j);
        }

        Map<Integer, Integer> treeMap = new TreeMap<Integer, Integer>(tempMap);
        int[] sortedTables = new int[t];
        int j = t - 1;
        for (Map.Entry<Integer, Integer> e : treeMap.entrySet()) {
            sortedTables[j--] = e.getValue();
        }
        return sortedTables;
    }

    static void sortCharm(int m) {
        for (int j = 0; j < m; j++) {
            Arrays.sort(charm[j]); //ascending order
        }
    }

    static void solve(int m, int n, int t, int k) {
        int total_donation = 0;
        if (checkForRules(X, t, k, n)) {
            int[] tableSorted = sortTablesDescending(t);
            sortCharm(m);
            for (int tNo : tableSorted) {

                int donation = 0;
                //choose a group
                int[] G = findGroup(m, n, k, X.get(tNo));
                int g = G[0];
                int j = G[1];
                //find the donation

                for (int generosity_factor : listTables.get(tNo)) {

                    if (count_request[g][j] == k) {
                        j--;
                    }
                    donation += charm[g][j] * generosity_factor;
                    count_request[g][j]++;
                }

                total_donation += donation;
            }
        } else {
            total_donation = -1;
        }
        System.out.println(total_donation);

    }

    static int[] findGroup(int m, int n, int k, int x) {
        Map<Integer, Integer> tempMap = new HashMap<Integer, Integer>();
        for (int j = 0; j < m; j++) {

            //find the sum of charm factor
            int sum = 0;
            int X = 0;
            for (int i = (n-1); i >=0 ; i--) {
                if (count_request[j][i] != k && X < x) {
                    sum += charm[j][i] * (k - count_request[j][i]);
                    X += (k - count_request[j][i]);
                }
            }
            if(X < x){
                sum = -1;
            }
            tempMap.put(sum, j);
        }

        int j = m-1;
        int[] G = new int[2];

        Map<Integer, Integer> treeMap = new TreeMap<Integer, Integer>(tempMap);
        int[] sortedG = new int[m];
        for (Map.Entry<Integer, Integer> e : treeMap.entrySet()) {
            sortedG[j] = e.getValue();
            if(e.getKey() < 0){
                sortedG[j] = (sortedG[j])*(-1);
            }
            j--;
        }

        int i = (n-1);
        int sum_count = 0;

        for(int f : sortedG){
            if(f>0){G[0] = f; break;}
        }
        while(count_request[G[0]][i] == k){ i--;}
        G[1] = i;
        return G;

    }

    static boolean checkForRules(List<Integer> X, int t, int k, int n) {
        for (int x : X) {
            if (n * k < x) {
                return false;
            }
        }
        return true;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int tc = Integer.parseInt(scanner.nextLine().trim());
        for (int tcItr = 0; tcItr < tc; tcItr++) {
            String[] mnt = scanner.nextLine().split(" ");

            int m = Integer.parseInt(mnt[0].trim());

            int n = Integer.parseInt(mnt[1].trim());

            count_request = new int[m][n];
            charm = new int[m][n];

            for (int charmRowItr = 0; charmRowItr < m; charmRowItr++) {
                String[] charmRowItems = scanner.nextLine().split(" ");

                for (int charmColumnItr = 0; charmColumnItr < n; charmColumnItr++) {
                    int charmItem = Integer.parseInt(charmRowItems[charmColumnItr].trim());
                    charm[charmRowItr][charmColumnItr] = charmItem;
                }
            }

            int t = Integer.parseInt(mnt[2].trim());
            listTables = new ArrayList<List<Integer>>();
            X = new ArrayList<Integer>();

            for (int tItr = 0; tItr < t; tItr++) {
                String[] tableLine = scanner.nextLine().split(" ");
                int x = Integer.parseInt(tableLine[0].trim());
                X.add(x);
                List<Integer> generosities = new ArrayList<Integer>();

                for (int generositiesItr = 1; generositiesItr <x+1; generositiesItr++) {
                    int generositiesItem = Integer.parseInt(tableLine[generositiesItr].trim());
                    generosities.add(generositiesItem);
                }
                listTables.add(tItr, generosities);
            }
            int k = Integer.parseInt(scanner.nextLine().trim());

            solve(m, n, t, k);


        }


    }

}
