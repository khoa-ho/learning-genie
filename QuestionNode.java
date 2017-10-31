import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QuestionNode implements DecisionNode {
    public String query;
    public DecisionNode yesTree;
    public DecisionNode noTree;
    /*
     * A constructor for QuestionNode containing a question as a string and then the trees for the yes and no choices
     */
    public QuestionNode(String query, DecisionNode yesTree, DecisionNode noTree) {
        this.query = query;
        this.yesTree = yesTree;
        this.noTree = noTree;
    }
    /*
     * @return the total number of GuessNodes in the tree
     */
    public int countObjects() {
        return yesTree.countObjects() + noTree.countObjects();
    }
    /*
     * Performs the guessing game starting at this node using the given Scanner object to query the user for input; returns an updated node that is the result of any knowledge learned during the game
     * @return DecisionNode: returns a DecisionNode that can be interpreted as either a QuestionNode or a GuessNode
     * @param in: a Scanner object
     */
    public DecisionNode guess(Scanner in) {
        System.out.print(query + " ");
        String response = in.nextLine().toLowerCase();
        while (!response.equals("yes") && !response.equals("no")) {
            System.out.println("Incorrect response! Please answer Yes or No");
            System.out.print(query + " ");
            response = in.nextLine().toLowerCase();
        }

        if (response.equals("yes")) {
            yesTree = yesTree.guess(in);
        } else {
            noTree = noTree.guess(in);
        }
        return new QuestionNode(query, yesTree, noTree);
    }
    /*
     * writes this node in the serialized format
     * @param out: a FileWriter
     */
    public void write(FileWriter out) throws IOException {
        out.write("#" + query + System.lineSeparator());
        yesTree.write(out);
        noTree.write(out);
    }
}
