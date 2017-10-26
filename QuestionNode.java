import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QuestionNode implements DecisionNode {
    public String query;
    public DecisionNode yesTree;
    public DecisionNode noTree;

    public QuestionNode(String query, String yesAnswer, String noAnswer) {
        this.query = query;
        this.yesTree = new GuessNode(yesAnswer);
        this.noTree = new GuessNode(noAnswer);
    }

    public int countObjects() {
        return yesTree.countObjects() + noTree.countObjects();
    }

    public DecisionNode guess(Scanner in) {
        System.out.println(query);
        String response = in.nextLine().toLowerCase();
        if (response.equals("yes")) {
            return yesTree.guess(in);
        } else if (response.equals("no")) {
            return noTree.guess(in);
        } else {
            return guess(in);
        }
    }

    public void write(FileWriter out) throws IOException {
        out.write("#" + query + "\n");
        yesTree.write(out);
        noTree.write(out);
    }
}
