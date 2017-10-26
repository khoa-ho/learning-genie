import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GuessNode implements DecisionNode {
    public String guess;

    public GuessNode(String guess) {
        this.guess = guess;
    }

    public int countObjects() {
        return 1;
    }

    public DecisionNode guess(Scanner in) {
        System.out.println("Are you thinking of " + guess + "?");
        String response = in.nextLine().toLowerCase();
        if (response.equals("yes")) {
            System.out.println("Excellent, thanks!");
            return new GuessNode(guess);
        } else if (response.equals("no")) {
            System.out.println("Oh no, I was wrong!\n"
                    + "What animal were you thinking of?");
            String answer = in.nextLine();
            System.out.println("What is a yes/no question that distinguishes a " + guess
                    + " from a " + answer + "?");
            System.out.println("(Yes corresponds to " + guess + "; No corresponds to "+ answer + ")");
            String query = in.nextLine();
            System.out.println("Thanks!  I'll learn from this experience!");
            return new QuestionNode(query, guess, answer);
        } else {
            System.out.println("Incorrect response! Please answer Yes or No");
            return guess(in);
        }
    }

    public void write(FileWriter out) throws IOException {
        out.write(guess + "\n");
    }
}
