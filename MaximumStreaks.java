public class MaximumStreaks {

    public void getMaxStreaks(String[] tosses) {
        int max_head = 0;
        int max_tail = 0;
        int heads_count = 0;
        int tails_count = 0;
        boolean prev_heads = false;
        String heads = "H";
        for (String s : tosses) {
            if (s.contains(heads)) {
                if (prev_heads) {
                    heads_count += 1;
                } else {
                    if (max_head < heads_count) {
                        max_head = heads_count;
                    }
                    heads_count = 1;
                    prev_heads = true;
                }
            } else {
                if (prev_heads) {
                    if (max_tail < tails_count) {
                        max_tail = tails_count;
                    }
                    tails_count = 1;
                } else {
                    tails_count += 1;
                }
                prev_heads = false;
            }
        }
        if (max_head < heads_count) {
            max_head = heads_count;
        }
        if (max_tail < tails_count) {
            max_tail = tails_count;
        }
        System.out.println(max_head + " " + max_tail);
    }

    public static void main(String[] args) {

        MaximumStreaks oMS = new MaximumStreaks();
        String[] tosses = {"Tails", "Tails", "Heads", "Heads","Heads", "Tails"};
        oMS.getMaxStreaks(tosses);
    }
}
