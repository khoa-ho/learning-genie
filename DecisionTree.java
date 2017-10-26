import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DecisionTree {
    private DecisionNode root;

    public DecisionTree() {}
    
    public DecisionTree(File file) throws FileNotFoundException {
        Scanner in = new Scanner(file);
        
    }
    
    public int countObjects() {
        return root.countObjects();
    }

    public void guess(Scanner in) {
        System.out.println(
                "I am the learning genie!\n"
                + "I can figure out whatever you are thinking of by asking questions.\n"
                + "I know 1 thing!\n");
        guessHelper(in);        
    }
    
    private void guessHelper(Scanner in) {
        System.out.println("Think of an object!");
        root.guess(in);
        System.out.println("Do you want to continue?");
        String response = in.nextLine();
        if (response.equals("Yes")) {
            guessHelper(in);
        }
    }

    public void write(FileWriter out) throws IOException {
        root.write(out);
    }
}
