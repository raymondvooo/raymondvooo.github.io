/**
 * Project Title: Tree Lab
 * Project Description: Program gets input file of Gettysburg Address and creates a cross reference table for every word, displaying
 * the number of appearances and line and position. Also creates a hash table using linear probing for collision resolution for words to be
 * omitted. Also prompts user to query for words and displays the number of appearances and line and position.
 * @version May 18, 2018
 * How to Start the Project: Run it.
 * @author Raymond Vo
 * Palomar ID: 010478907
 * User Instructions: When asks for user input, input words, zero to exit.
 */
import java.io.*;
/**
 * Driver Class
 * Creates an output file, hash table, and cross reference table for all words in Gettysburg address. Then prompts user query for word search.
 */
public class Driver {

    public static void main(String[] args) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("csis.txt"));
        Hash hashing = new Hash(pw);
        Xref crossRef = new Xref(pw, hashing);
        Query userQ = new Query(pw, crossRef);


        crossRef.outputGetty();
        crossRef.createTree();
        crossRef.outputXRef();

        userQ.search();


        hashing.getOmitted();
        hashing.outputHash();
        System.out.println("Hash function adds together the ascii values of each character of the word and then with\n" +
                "that sum, squares it and multiplies the value by 144. It then modulos it by the table size which is 37.\n" +
                "I found this mathematical function by squaring the sum and plugging in random even numbers for the multiplier.");
        pw.println("Hash function adds together the ascii values of each character of the word and then with\r\n" +
                "that sum, squares it and multiplies the value by 144. It then modulos it by the table size which is 37.\r\n" +
                "I found this mathematical function by squaring the sum and plugging in random even numbers for the multiplier.");

        pw.close();
    }
}