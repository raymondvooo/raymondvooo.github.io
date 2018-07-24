import java.io.*;
import java.util.Scanner;

/**
 * Query class is for user searching for words and outputting it's statistics
 */
public class Query {
    private PrintWriter pw;
    private Xref crossRef;
    private String query;

    /**
     * Query constructor
     * @param pw output file
     * @param x cross reference table and statistics
     */
    public Query (PrintWriter pw, Xref x){
        this.pw = pw;
        this.crossRef = x;
        this.query = null;
    }

    /**
     * Asks for user input and searches for the word and outputs it's statistics.
     */
    public void search() {
        boolean x = true;
        ObjectBinaryTree BST = crossRef.getTree();
        Scanner input = new Scanner(System.in);


        while (x) {
            System.out.println();
            pw.println();

            System.out.println("Enter word to query (0 to stop): ");
            pw.println("Enter word to query (0 to stop): ");

            query = input.nextLine();
            pw.print("User input: " + query + "\r\n");
            Word searchFor = new Word(query.toLowerCase());
            ObjectTreeNode q = BST.searchBST(searchFor);

            if (query.equals("0")) {
                System.out.println("User query stopped.");
                pw.println("User query stopped.");
                x = false;
                input.close();
            }
            else if (q == null) {
                System.out.println("Error: '" + query +"' not found.");
                pw.println("Error: '" + query +"' not found.");
            }
            else {
                System.out.printf("%-9s %-12s %-10s\n", "Word", "Appearances", "Line-Position");
                pw.printf("%-9s %-12s %-10s\r\n", "Word", "Appearances", "Line-Position");

                System.out.println("----------------------------------------------------------------------------------------------------");
                pw.println("----------------------------------------------------------------------------------------------------");

                Word w = (Word) q.getInfo();
                w.visit();
            }

        }

    }

}