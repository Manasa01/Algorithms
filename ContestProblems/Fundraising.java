import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Fundraising {

    //structure of arraylist for list of tables
    static List<List<Integer>> listTables = new ArrayList<List<Integer>>();
    //list of total number of guests in each table
    static List<Integer> X = new ArrayList<Integer>();

    static void guestTable(int[] generosities, int i) {

        List<Integer> oL = new ArrayList<Integer>();
        boolean firstVal = true;
        for (int val : generosities) {
            if (firstVal) {
                X.add(val);
                firstVal = false;
            } else {
                oL.add(val);
            }
        }
        listTables.add(i, oL);
    }

    static int[] sortTablesOnSum(int t) {
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

    private static class returnStruct {
        int[][] arr;
        Map<Integer, Integer> treeMap;
    }

    static returnStruct sortGroupsOnSum(int[][] charm, int m, int n, int[][] count, int k, int x) {
        Map<Integer, Integer> tempMap = new HashMap<Integer, Integer>();
        int[][] final_charm = new int[m][n];
        for (int j = 0; j < m; j++) {
            Arrays.sort(charm[j]);
            //find the sum of generosity_factor in a table
            int sum = 0;
            int X = 0;
            int i = 0;
            for (int val : charm[j]) {
                if(x == 0){
                    if (count[j][i] != k) {
                        sum += val * (k - count[j][i]);
                    }
                }
                else{
                    if (count[j][i] != k && X < x) {
                        sum += val * (k - count[j][i]);
                        X = (k - count[j][i]);
                    }
                }
                i++;
            }
            tempMap.put(sum, j);
        }
        Map<Integer, Integer> treeMap = new TreeMap<Integer, Integer>(tempMap);
        int j = 0;
        for (Map.Entry<Integer, Integer> e : treeMap.entrySet()) {
            for (int i = (n - 1); i >= 0; i--) {
                final_charm[j][(n - 1) - i] = charm[e.getValue()][i];
                System.out.println(final_charm[j][(n-1)-i]);
            }
j++;
        }
        returnStruct oRet = new returnStruct();
        oRet.arr =  final_charm;
        oRet.treeMap = treeMap;
        return  oRet;

    }

    static void solve(int m, int n, int[][] charm, int t, int k) {
        int total_donation = 0;
        if (checkForRules(X, t, k, n)) {
            int[] tableSorted = sortTablesOnSum(t);

            int[][] count_request = new int[m][n];
           // returnStruct groupSortedRet = sortGroupsOnSum(charm, m, n, count_request, k , 0);
            //total donation
            for (int tNo : tableSorted) {

                int donation = 0;
                //choose a group
                int x = X.get(tNo);
                returnStruct groupSortedRet = sortGroupsOnSum(charm, m, n, count_request, k,x);
                count_request = sortCount(count_request, groupSortedRet.treeMap, m ,n);
                int[] G = findGroup(count_request, n, k, m, x);

                //find the donation
                int j = 0;
                for (int generosity_factor : listTables.get(tNo)) {

                    while (count_request[G[0]][j] == k) {
                        j++;
                    }
                    donation += (groupSortedRet.arr)[G[0]][j] * generosity_factor;
                    count_request[G[0]][j]++;
                }

                total_donation += donation;

            }
        } else {
            total_donation = -1;
        }
        System.out.println(total_donation);
    }

    static int[][] sortCount(int[][] count, Map<Integer, Integer> treeMap, int m, int n) {
        int[][] final_count = new int[m][n];
        int j=0;
        for (Map.Entry<Integer, Integer> e : treeMap.entrySet()) {
            for (int i = 0; i < n; i++) {
                final_count[j][i] = count[e.getValue()][i];
            }
            j++;
        }
        return final_count;
    }

    static int[] findGroup(int[][] count, int n, int k, int m, int x) {
        int[] G = new int[2];
        int i = 0;

        while (i < m) {
            int j = 0;
            boolean setJ = false;
            int sum = 0;
            for (int val : count[i]) {
                sum += val;
                if (!setJ && val != k) {
                    G[1] = j;
                    setJ = true;
                }
                j++;
            }
            if (n * k - sum >= x) {
                G[0] = i;
                break;
            } else {
                i++;
            }
        }
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

            int[][] charm = new int[m][n];

            for (int charmRowItr = 0; charmRowItr < m; charmRowItr++) {
                String[] charmRowItems = scanner.nextLine().split(" ");

                for (int charmColumnItr = 0; charmColumnItr < n; charmColumnItr++) {
                    int charmItem = Integer.parseInt(charmRowItems[charmColumnItr].trim());
                    charm[charmRowItr][charmColumnItr] = charmItem;
                }
            }

            int t = Integer.parseInt(mnt[2].trim());

            for (int tItr = 0; tItr < t; tItr++) {
                String[] tableLine = scanner.nextLine().split(" ");
                int x = Integer.parseInt(tableLine[0].trim());

                int[] generosities = new int[x + 1];
                generosities[0] = x;
                for (int generositiesItr = 1; generositiesItr < x + 1; generositiesItr++) {
                    int generositiesItem = Integer.parseInt(tableLine[generositiesItr].trim());
                    generosities[generositiesItr] = generositiesItem;
                }

                guestTable(generosities, tItr);

            }

            int k = Integer.parseInt(scanner.nextLine().trim());

            solve(m, n, charm, t, k);
            listTables = new ArrayList<List<Integer>>();
            X = new ArrayList<Integer>();
        }
    }
}
