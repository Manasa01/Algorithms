import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class HashTable {
    String[] hashTable;
    int tableSize;
    int textCount;
    int totalCollisions = 0;

    HashTable() {
        //Initial table size is 31
        initializeHashTable(31);
    }

    void initializeHashTable(int tableSize) {

        hashTable = new String[tableSize];
        this.tableSize = tableSize;
        textCount = 0;
        totalCollisions = 0;
    }

    /*
     Insert text into hashTable
       */
    void insert(String key) {
        int hash = hash(key);
        int collisionCount = 0;
        //The collision resolution scheme is open addressing - quadratic probing.
        while (hashTable[hash] != null) {
            collisionCount++;
            totalCollisions++;
            //Collision resolution: Using Quadratic Probing
            hash = (hash + collisionCount * collisionCount) % tableSize;
        }
        hashTable[hash] = key;
        textCount++;
        //Increase the table size and rehash at load factor of .5
        double loadFactor = getLoadFactor();

        System.out.printf("|%20s|%17d|%13f|%19s|\n", key, hash, loadFactor, collisionCount);
        if (loadFactor >= 0.5) {
            rehash();
        }
    }

    double getLoadFactor() {
        return (double) textCount / tableSize;
    }

    /*
     Hash function for text strings
     */
    int hash(String key) {
        int index = 0;
        int P = 37; //A small prime number is preferrable
        for (int i = 0; i < key.length(); i++) {
            index = (P * index + key.charAt(i)) % tableSize;
        }
        if (index < 0) {
            index += tableSize;
        }
        return index;
    }

    /* ReHash
       re-size and re-insert
    */
    void rehash() {
        printStatus();
        System.out.println("Rehashing in progress...");
        //re-size
        String[] tempHash = hashTable;
        initializeHashTable(tableSize * 2);
        //re-insert
        printInsertStatus();
        for (int i = 0; i < tempHash.length; i++) {
            if (tempHash[i] != null) {
                insert(tempHash[i]);
            }
        }
        System.out.println("Rehashing completed...");
    }

    void printStatus() {

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Hash Table size: " + tableSize);
        System.out.println("Number of text inputs: " + textCount);
        System.out.println("Load factor: " + getLoadFactor());
        System.out.println("Total number of collisions occurred: " + totalCollisions);
        System.out.println("--------------------------------------------------------------------------");

    }

    void printInsertStatus() {

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Inserting text into hash table...");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("|        Text        | Stored at index | Load Factor | No. of collisions |");
        System.out.println("--------------------------------------------------------------------------");
    }

    public static void main(String[] args) throws Exception {
        /*
        Requirement: Pick 20 random words.  Each word must be of different lengths, maximum length 8 and minimum length 3.
        The words will be of letters a-zA-Z and the space character.
        */
        String pathname = args[0];
        File inputTexts = new File(pathname); // pathname must contain the COMPLETE path of the file
        BufferedReader br = new BufferedReader(new FileReader(inputTexts));
        HashTable ht = new HashTable();
        //insert the 20 words into the hash table
        ht.printInsertStatus();
        String text;
        while ((text = br.readLine()) != null) {
            ht.insert(text);
        }
        ht.printStatus();
    }
}

