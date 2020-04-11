import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DVD {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    //find the smallest number out of order - s
    //no of operations will be max_num or in other words n - s + 1

    //find one , once found, find two and so on...increment disorder when the current searchTerm(i.e. one , two and so on) is not found
   /* private static int findDisorder(int[] arr){
        int disorder = 0;
        int num = 1;
        for(int val : arr){
            if(val != num) disorder++;
            else num++;
        }
        return disorder;
    }*/
    public static void main(String[] args) {
        FastReader oScn = new FastReader();
        int testcases = oScn.nextInt();
        for (int i = 0; i < testcases; i++) {
            int disorder = 0;
            int num = 1;

            int N = oScn.nextInt();

            for (int j = 0; j < N; j++) {
                if (oScn.nextInt() != num) disorder++;
                else num++;
            }
            System.out.println(disorder);
        }
    }
}
