import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GuessNode implements DecisionNode {
    public String guess;
    /*
     * A constructor for GuessNodes containing a guess
     */
    public GuessNode(String guess) {
        this.guess = guess;
    }
    /*
     * gives the GuessNode a count of 1
     * @return 1: the count of the Object
     */
    public int countObjects() {
        return 1;
    }
    /*
     * Performs the guessing game starting at this node using the given Scanner object to query the user for input; returns an updated node that is the result of any knowledge learned during the game
     * @return DecisionNode: returns a DecisionNode that can be interpreted as either a QuestionNode or a GuessNode
     * @param in: a Scanner object
     */
    public DecisionNode guess(Scanner in) {
        System.out.print("Are you thinking of " + guess + "? ");
        String response = in.nextLine().toLowerCase();
        while (!response.equals("yes") && !response.equals("no")) {
            System.out.println("Incorrect response! Please answer Yes or No");
            System.out.print("Are you thinking of " + guess + "? ");
            response = in.nextLine().toLowerCase();
        }

        if (response.equals("yes")) {
            System.out.println("Excellent, thanks!");
            return new GuessNode(guess);
        } else {
            System.out.print("Oh no, I was wrong!\n" + "What object were you thinking of? ");
            String answer = in.nextLine();
            System.out.println("What is a yes/no question that distinguishes a " + guess
                    + " from a " + answer + "?");
            System.out
                    .print("(Yes corresponds to " + guess + "; No corresponds to " + answer + ") ");
            String query = in.nextLine();
            System.out.println("Thanks!  I'll learn from this experience!");
            return new QuestionNode(query, new GuessNode(guess), new GuessNode(answer));
        }
    }
    /*
     * writes this node in the serialized format
     * @param out: a FileWriter
     */
    public void write(FileWriter out) throws IOException {
        out.write(guess + System.lineSeparator());
    }
}
