import java.io.PrintWriter;

/**
 * Word class is the object for words to put into binary search tree.
 */
public class Word implements TreeComparable {
    private PrintWriter pw;
    private String word;
    private int count = 0;
    private ObjectList list;

    /**
     * Word constructor takes in output file, word string, number of occurences, and list for line-position
     * @param pw output file
     * @param word string
     * @param count number of occurences
     * @param list line-position
     */
    public Word(PrintWriter pw, String word, int count, ObjectList list) {
        this.pw = pw;
        this.word = word;
        this.count = count;
        this.list = list;
    }

    /**
     * Word constructor takes in String for searching.
     * @param word
     */
    public Word(String word) {
        this.word = word;
    }

    /**
     * getWord gets the String word
     * @return word
     */
    public String getWord() {
        return word;
    }

    /**
     * getList gets the line-position list
     * @return list
     */
    public ObjectList getList() {
        return list;
    }

    /**
     * compareTo is for comparing words for search tree
     * @param o word
     * @return the greater word
     */
    public int compareTo(Object o) {
        Word w = (Word) o;
        return word.compareTo(w.getWord());
    }

    /**
     * operate adds word to tree and adds to the counter
     * @param o word
     */
    public void operate(Object o) {
        Word w = (Word) o;
        ObjectListNode p = w.getList().getFirstNode();

        list.addLast(p);
        count += 1;
    }

    /**
     * visit outputs the line-position of each word that is visited
     */
    public void visit() {
        System.out.printf("%-13s %-13s", word, count);
        pw.printf("%-13s %-13s", word, count);

        ObjectListNode p = list.getFirstNode();
        while (p != null) {
            LinePosition line = (LinePosition) p.getInfo();
            String pos = line.getLineNumber() + "-" + line.getPosition();

            System.out.printf("%-7s", pos);
            pw.printf("\r%-7s", pos);

            p = p.getNext();
        }
        System.out.println();
        pw.println();
    }


}
