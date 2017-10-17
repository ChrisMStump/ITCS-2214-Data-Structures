import java.util.*;
/**
 * Write a description of class WordList here.
 * 
 * @author Christopher Stump
 * @version 11/27/2015
 */
class WordList{
    // Reference to first Link in list
    // The last Link added to the LinkedList
    public Pair firstWord = null;
    public Pair current;
    /*
     * If firstWord is null then the incoming word is the first word of the list.
     * If not the first word, then traverse the word to the end of the list.
     */
    public void insert(Pair newWord, int hashKey){
        Pair previous = null;
        Pair current = firstWord;
        newWord.hashValue = hashKey;
        while(current != null && newWord.hashValue > current.hashValue){
            previous = current;
            current = current.next;
        }
        if(previous == null){
           firstWord = newWord;
        }
        else{
            previous.next = newWord;
        }
        newWord.next = current;
    }
    /*
     * Used to display the List of Words in this section of the Hash Table.
     */
    public void displayWordList(){
        Pair current = firstWord;
        while (current != null){
            System.out.println(current);
            current = current.next;
        }
    }
    /*
     * Search for a word. If the word is found then return the Pair and increase the value.
     */
    public Pair find(int hashKey, String wordToFind){
        Pair current = firstWord;
        // Search for key, but stop searching if
        // the hashKey < what we are searching for
        // Because the list is sorted this allows
        // us to avoid searching the whole list.
        while (current != null && current.hashValue <= hashKey){
            // NEW
            if (current.k.equals(wordToFind)){
                current.increaseValue();
                return current;
            }
            current = current.next;
        }
        return null;
    }
    /*
     * Search for a word. If the word is found then return the Pair.
     */
    public Pair findWord(int hashKey, String wordToFind){
        Pair current = firstWord;
        // Search for key, but stop searching if
        // the hashKey < what we are searching for
        // Because the list is sorted this allows
        // us to avoid searching the whole list.
        while (current != null && current.hashValue <= hashKey){
            // NEW
            if (current.k.equals(wordToFind)){
                return current;
            }
            current = current.next;
        }
        return null;
    }
}