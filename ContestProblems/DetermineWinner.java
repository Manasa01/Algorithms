public class DetermineWinner {
    public String getRoundResult(char winning_suit, char suit1, int number1, char suit2, int number2) {

        if (Character.compare(suit1, winning_suit) == 0 && Character.compare(suit2, winning_suit) == 0) {
            if (number1 > number2) {
                return "Player 1 wins";
            } else if (number2 > number1) {
                return "Player 2 wins";
            } else {
                return "Draw";
            }
        } else if (Character.compare(suit1, winning_suit) == 0) {
            return "Player 1 wins";

        } else if (Character.compare(suit2, winning_suit) == 0) {
            return "Player 2 wins";
        } else {
            if (number1 > number2) {
                return "Player 1 wins";
            } else if (number2 > number1) {
                return "Player 2 wins";
            } else {
                return "Draw";
            }
        }
    }

    public static void main(String[] args) {
        DetermineWinner oDW = new DetermineWinner();
        System.out.println(oDW.getRoundResult('C', 'B', 11, 'A', 11));
    }
}
