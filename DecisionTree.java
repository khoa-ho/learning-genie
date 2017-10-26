import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DecisionTree {
    private DecisionNode root;

    public DecisionTree() {
        root = new GuessNode("Dog");
    }

    public DecisionTree(File file) throws FileNotFoundException {
        Scanner in = new Scanner(file);
        root = decisionTreeHelper(in);
        in.close();
    }

    private DecisionNode decisionTreeHelper(Scanner in) {
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.charAt(0) != '#') {
                return new GuessNode(line);
            } else {
                return new QuestionNode(line.substring(1, line.length()), decisionTreeHelper(in),
                        decisionTreeHelper(in));
            }
        }
        return root;
    }

    public int countObjects() {
        return root.countObjects();
    }

    public void guess(Scanner in) {
        root = root.guess(in);
    }

    public void write(FileWriter out) throws IOException {
        root.write(out);
        out.close();
    }
}
