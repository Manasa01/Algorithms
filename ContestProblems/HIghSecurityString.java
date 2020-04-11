/*
english lower letters
0 to 25
starts from a then cyclic

string  1 to 100, lowercase
int 0 to 25
*/
public class HIghSecurityString {


    public int getStrength(String password, int weight_a) {
        int len = password.length();
        if(weight_a < 0 || weight_a > 25){
            throw new IllegalArgumentException("Must be within 1 to 25, including");
        }
        if  (len < 1 || len > 100){
            throw new IllegalArgumentException("String length must be within 1 to 100, including");
        }
        int strength = 0;
        int char_strength = 0;

        for (int i = 0; i < len; i++) {
            char_strength = ((((int) (password.charAt(i))) - ((int) 'a')) + weight_a) % 26;
            strength = strength + char_strength;
        }
        //for each character in the string
        //find the weight
        //add to the total

        return strength;

    }

    public static void main(String[] args) {
        HIghSecurityString oHS = new HIghSecurityString();
        System.out.println(oHS.getStrength("aaaaaab", 55));
    }


}


