import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DecisionTree {
    private DecisionNode root;
    /*
     * A constructor for DecisionTree creating the root as a Guess Node of string "Dog"
     */
    public DecisionTree() {
        root = new GuessNode("Dog");
    }
    /*
     * Constructs a decision tree stored in a serialized form in the given file
     */
    public DecisionTree(File file) throws FileNotFoundException {
        Scanner in = new Scanner(file);
        root = decisionTreeHelper(in);
        in.close();
    }
    /*
     * A helper for decision tree that assigns lines that start with a # as QuestionNodes and lines that do not as GuessNodes
     */
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
    /*
     * @return the count of all objects in the decision tree
     */
    public int countObjects() {
        return root.countObjects();
    }

    /*
     *Assigns the root of the decision tree to be the dependent on the given file taken in by the scanner
     *@param in: a Scanner of the given file
     */
    public void guess(Scanner in) {
        root = root.guess(in);
    }
    /*
     * writes this tree in the serialized format
     * @param out: a FileWriter
     */
    public void write(FileWriter out) throws IOException {
        root.write(out);
        out.close();
    }
}
