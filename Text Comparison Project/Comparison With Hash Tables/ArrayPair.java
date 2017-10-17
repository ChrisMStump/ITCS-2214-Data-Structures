import java.util.*;
import java.lang.*;
/**
 * Creates the hash table.
 * 
 * @author Christopher Stump
 * @version 11/27/2015
 */
public class ArrayPair implements Dictionary
{
    WordList[] theArray; //The actual Hash Table. An array of Linked List. :)
    WordList temp; //Used to hold a WordList at a point in the Hash Table.
    int arraySize; //The size of the hash table.
    int iterator = 0;
    /*
     * At first call, set the size of the Hash Table.
     * This is determined by the String Tokenizer countTokens().
     */
    public ArrayPair(int size){
        arraySize = size;
        theArray = new WordList[size];
        for (int i = 0; i < arraySize; i++){
            theArray[i] = new WordList();
        }
    }
    /*
     * Call to add a String. A new Pair is created and the inset method is called.
     */
    public void add(String s){
        if(contains(s) == null){
            Pair newWord = new Pair(s, 1);
            insert(newWord);
        }
    }
    /*
     * Creates the hash code out of the string and sends it along with the word to
     * be inserted into a Word List.
     */
    public void insert(Pair newWord){
        String wordToHash = newWord.k;
        // Calculate the hashkey for the Word
        int hashKey = stringHashFunction(wordToHash);
        // Add the new word to the array and set
        // the key for the word
        theArray[hashKey].insert(newWord, hashKey);
    }
    /*
     * Turns the string into hash code.
     */
    public int stringHashFunction(String wordToHash){
        int hashKeyValue = 0;
        for (int i = 0; i < wordToHash.length(); i++){
            // 'a' has the character code of 97 so subtract
            // to make our letters start at 1
            int charCode = wordToHash.charAt(i) - 96;
            int hKVTemp = hashKeyValue;
            // Calculate the hash key using the 26 letters
            // plus blank.
            hashKeyValue = (hashKeyValue * 27 + charCode) % arraySize;
        }
        //System.out.println();
        return hashKeyValue;
    }
    /*
     * Used to see if the table contains a string. It also increses the value if it does.
     */
    public Pair contains(String s){
        // Calculate the hash key for the word
        int hashKey = stringHashFunction(s);
        // NEW
        System.out.println(s);
        Pair theWord = theArray[hashKey].find(hashKey, s);
        return theWord;
    }
    /*
     * Used to see if the tbale contains a string. Does not increase value.
     */
    public Pair containsWord(String s){
        // Calculate the hash key for the word
        int hashKey = stringHashFunction(s);
        // NEW
        Pair theWord = theArray[hashKey].findWord(hashKey, s);
        return theWord;
    }
    /*
     * Used to display the hash table.
     * Calls the Word List for each spot in the array.
     * This also displays the Word List.
     */
    public void displayTheArray(){
        for (int i = 0; i < arraySize; i++) {
            System.out.println("theArray Index " + i);
            theArray[i].displayWordList();
        }
    }
    /*
     * Set the iterator to zero.
     */
    public void reset(){
        this.iterator = 0;
    }
    /*
     * Increment the iterator.
     */
    public void increaseIterator(){
        this.iterator++;
    }
    /*
     * Used to traverse the Hash Table. Returns the list at theArray[iterator].
     */
    public WordList traverseNext(){
        temp = theArray[iterator];
        iterator++; 
        return temp;
    }
}
