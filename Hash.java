import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Hash class for creating hash table and performing operations on omitted words.
 */
public class Hash {
    private PrintWriter pw;
    private int TABLESIZE;
    private int index;
    private int hashCollisons;
    private int resCollisons;
    private int omitcount;
    private String[] hashTable;
    private int keys;

    /**
     * Hash constructor
     * @param pw output file
     */
    public Hash(PrintWriter pw) {
        this.pw = pw;
        this.TABLESIZE = 37;
        this.hashTable = new String[TABLESIZE];
        this.hashCollisons = 0;
        this.omitcount = 0;
    }

    /**
     * hash function
     *
     * @param key hash table key
     * @return index for storage
     */
    private int getHash(String key) {
        index = 0;
        int sum = 0;

        for (int i = 0; i < key.length(); i++) {
            sum += (int) key.charAt(i);
        }
        index = (sum ^ 2 * 144) % TABLESIZE;

        return index;
    }

    /**
     * gets and inputs the omitted words
     * @throws IOException
     */
    public void getOmitted() throws IOException {
        Scanner omit = new Scanner(new File("omit.txt"));

        while (omit.hasNext()) {
            String omitted = omit.next();
            insert(getHash(omitted), omitted);
        }
    }

    /**
     * inserts the item into hash table
     *
     * @param index index to be inserted
     * @param key word to be inserted
     */
    public void insert(int index, String key) {
        if (hashTable[index] == null) {
            hashTable[index] = key;
        } else {
            linearProbeInsert(index, key);
            keys++;
        }
    }

    /**
     * linear probing insert algorithm
      * @param index index
     * @param key word
     */
    private void linearProbeInsert(int index, String key) {
        if (hashTable[index] == null) {
            hashTable[index] = key;
        } else {
            hashCollisons++;
            while (hashTable[index] != null) {
                resCollisons++;
                index++;

                if (index >= TABLESIZE) {
                    index = 0;
                }
            }
        }
    }


    /**
     * searches for a word in hash table
     * @param key word
     * @return boolean if search hit or miss
     */
    public boolean search(String key) {
        index = getHash(key);
        boolean reset = false;

        if (hashTable[index] == null) {
            return false;
        } else if (hashTable[index].equals(key)) {
            omitcount++;
            return true;
        } else {
            linearProbeSearch(index, key, reset);

        }
        return false;
    }

    /**
     * algorithm for linear probe searching
     * @param index index of hash table
     * @param key word
     * @param reset if probe wraps around back to beginnning
     * @return search hit or miss
     */
    private boolean linearProbeSearch(int index, String key, boolean reset) {
        while (hashTable[index] != null && !(hashTable[index].equals(key))) {
            index++;

            if (hashTable[index] != null && hashTable[index].equals(key)) {
                omitcount++;
                return true;
            }
            if (index > TABLESIZE - 1) {
                index = 0;
                reset = true;
            }
            if (reset && index == index) {
                return false;
            }
        }
        return false;
    }


    /**
     * outputs hash table and collision statistics.
     */
    public void outputHash() {
        System.out.println("-------------------------");
        pw.println("-------------------------");
        System.out.println(" Hash Table");
        pw.println(" Hash Table");
        System.out.println("-------------------------");
        pw.println("-------------------------");
        System.out.println(" Code |  Key");
        pw.println(" Code |  Key");
        System.out.println("-------------------------");
        pw.println("-------------------------");

        for (int index = 0; index < TABLESIZE; index++) {
            if (hashTable[index] != null) {
                System.out.println(index + "\t|" + hashTable[index]);
                pw.println(index + "\t|" + hashTable[index]);

            } else if (hashTable[index] == null) {
                System.out.println(index + "\t|" + "-");
                pw.println(index + "\t|" + "-");
            }
        }
        System.out.println("------------------------------");
        pw.println("------------------------------");
        System.out.println("Hash Collisions: " + "\t" + hashCollisons);
        pw.println("Hash Collisions: " + "\r\t" + hashCollisons);

        System.out.println("Resolution Collisions: " + "\t" + resCollisons);
        pw.println("Resolution Collisions: " + "\r\t" + resCollisons);

        System.out.println("Total Collisions: " + "\t" + (hashCollisons + resCollisons));
        pw.println("Total Collisions: " + "\r\t" + (hashCollisons + resCollisons));

        System.out.printf("Load factor: %.2f percent\n", (double) keys / TABLESIZE * 100);
        pw.printf("Load factor: %.2f percent\r\n", (double) keys / TABLESIZE * 100);

        System.out.println("------------------------------\n");
        pw.println("------------------------------\r\n");
    }
}
