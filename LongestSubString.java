public class LongestSubString {

    private String str1;
    private String str2;
    private int len1;
    private int len2;

    LongestSubString(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
        len1 = str1.length();
        len2 = str2.length();
    }

    public int recursiveLSS(int i, int j, int count) {

//        if (i == len1 || j == len2) {
//            return 0;
//        }
//        if (str1.charAt(i) == str2.charAt(j)) {
//            return  1 + recursiveLSS(i + 1, j + 1);
//        }
//        else{
//          return recursiveLSS()
//        }

        if (i == 0 || j == 0)
            return count;

        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
            count = recursiveLSS(i - 1, j - 1, count + 1);
        }
        count = Math.max(count, Math.max(recursiveLSS(i, j - 1, 0), recursiveLSS(i - 1, j, 0)));
        return count;


    }

    public int DP_LSS() {
        int[][] str1str2 = new int[len1][len2];

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {

                    str1str2[i][j] = (i > 0 && j > 0 ? str1str2[i - 1][j - 1] : 0) + 1;

                } else {
                    str1str2[i][j] = 0;
                }
            }
        }

//        find max
        int max = 0;
        for (int i = 0; i < len1; i++) {

            for (int j = 0; j < len2; j++) {
                if (max < str1str2[i][j]) {
                    max = str1str2[i][j];
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestSubString oLS = new LongestSubString("eeaf", "eef");
        System.out.println(oLS.recursiveLSS(oLS.len1, oLS.len2,0));

        System.out.println(oLS.DP_LSS());
    }
}
