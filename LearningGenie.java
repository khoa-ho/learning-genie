import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LearningGenie {
    public static void main(String[] args) throws IOException {
        String fileName = "genieData.txt";
        File genieData = new File(fileName);
        DecisionTree root;
        if (genieData.exists()) {
            root = new DecisionTree(genieData);
        } else {
            root = new DecisionTree();
        }
        FileWriter out = new FileWriter(fileName);

        System.out.println("I am the learning genie!\n"
                + "I can figure out whatever you are thinking of by asking questions.\n" + "I know "
                + root.countObjects() + " thing!");

        Scanner in = new Scanner(System.in);
        boolean isContinued = true;
        while (isContinued) {
            System.out.println("\nThink of an object!");
            root.guess(in);

            System.out.print("Do you want to continue? ");
            String response = in.nextLine().toLowerCase();
            while (!response.equals("yes") && !response.equals("no")) {
                System.out.println("Incorrect response! Please answer Yes or No");
                System.out.print("Do you want to continue? ");
                response = in.nextLine().toLowerCase();
            }
            
            if (response.equals("no")) {
                isContinued = false;
            }
        }
        root.write(out);
        in.close();
    }
}
