import java.io.*;
import java.util.Scanner;

/**
 * Xred class is used for cross reference table for  words in the Getty and contains methods for creating search tree and outputting info.
 */
public class Xref {
    private PrintWriter pw;
    private ObjectBinaryTree BST;
    private Hash h;

    /**
     * Xref constructor
     * @param pw output file
     * @param h hash class
     */
    public Xref(PrintWriter pw, Hash h) {
        this.pw = pw;
        this.BST = new ObjectBinaryTree();
        this.h = h;
    }

    /**
     * outputGetty outputs Gettysburg address with line numbers.
     * @throws IOException
     */
    public void outputGetty() throws IOException {
        String line;
        int lineNumber = 1;
        Scanner fileScan = new Scanner(new File("getty.txt"));

        while (fileScan.hasNextLine()) {
            line = fileScan.nextLine();
            System.out.println(lineNumber + ") " + line);
            pw.println(lineNumber + ") " + line);

            lineNumber++;
        }
        fileScan.close();
    }

    /**
     * Creates binary search tree of for storing statistics of words
     * @throws IOException
     */
    public void createTree() throws IOException {
        Scanner gettyScan = new Scanner(new File("getty.txt"));
        int lineNumber = 1;
        String delims = "[ ,. -;]+";

        while (gettyScan.hasNextLine()) {
            String[] tokens = gettyScan.nextLine().toLowerCase().split(delims);

            for (int i = 0; i < tokens.length; i++) {
                ObjectList list = new ObjectList();

                if (!h.search(tokens[i])) {
                    LinePosition linePos = new LinePosition(lineNumber, i + 1);
                    Word word = new Word(pw, tokens[i], 1, list);

                    list.addLast(linePos);
                    BST.insertBSTDup(word);
                }
            }
            lineNumber++;
        }
    }

    /**
     * outputs the cross reference table with statistics.
     */
    public void outputXRef() {
        System.out.println("\nCross Referenced Listing");
        pw.println("\r\nCross Referenced Listing");

        System.out.println("----------------------------------------------------------------------------------------------------");
        pw.println("----------------------------------------------------------------------------------------------------");

        System.out.printf("%-9s %-12s %-10s\n", "Word", "Appearances", "Line-Position");
        pw.printf("%-9s %-12s %-10s\r\n", "Word", "Appearances", "Line-Position");

        System.out.println("----------------------------------------------------------------------------------------------------");
        pw.println("----------------------------------------------------------------------------------------------------");


        ObjectTreeNode p = BST.getRoot();
        BST.inTrav(p);

        System.out.println("----------------------------------------------------------------------------------------------------");
        pw.println("----------------------------------------------------------------------------------------------------");

    }



    /**
     * method for getting binary search tree
     * @return search tree
     */
    public ObjectBinaryTree getTree() {
        return BST;
    }


}