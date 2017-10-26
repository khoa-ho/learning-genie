import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QuestionNode implements DecisionNode {
    public String query;
    public DecisionNode yesTree;
    public DecisionNode noTree;

    public QuestionNode(String query, DecisionNode yesTree, DecisionNode noTree) {
        this.query = query;
        this.yesTree = yesTree;
        this.noTree = noTree;
    }

    public int countObjects() {
        return yesTree.countObjects() + noTree.countObjects();
    }

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

    public void write(FileWriter out) throws IOException {
        out.write("#" + query + System.lineSeparator());
        yesTree.write(out);
        noTree.write(out);
    }
}
