import java.util.*;
import java.io.*;
import java.lang.*;
/**
 * The Distance class calculates the Distance of two text files.
 * 
 * @author Christopher Stump 
 * @version 11/27/2015
 */
public class Distance
{
    private WordList tempList; //Holds the list of words at a certain hashValue in the array.
    Pair temp; //Temporary holding for Pair objects.
    Pair temp2;
    double distance; //The text distance of the books.
    public Distance()
    {
    }
    public Distance(ArrayPair p, ArrayPair q){
        
        //Traverse through the first text. If a word appears in the second text, then subtract the absolute values of the frequencies.
        //If a word does not appear in the other text then just add the frequency to Distance.
        p.reset();
        while(p.iterator < p.arraySize){
            tempList = p.traverseNext();
            temp = tempList.firstWord;
            while(temp != null){
                if(q.containsWord(temp.getKey()) == null){
                    distance += (temp.getValue() / p.arraySize);
                }
                else if(q.containsWord(temp.getKey()) != null){
                    temp2 = q.containsWord(temp.getKey());
                    distance += Math.abs((temp.getValue() / p.arraySize) - (temp2.getValue() / q.arraySize));
                }
                temp = temp.next;                
            }
        } 
        
        //Traverse through the second text. All the words that don't appear in the first, add their frequencies to Distance.
        q.reset();
        while(q.iterator < q.arraySize){
            tempList = q.traverseNext();
            temp = tempList.firstWord;
            while(temp != null){
                if(p.containsWord(temp.getKey()) == null){
                    distance += (temp.getValue() / q.arraySize);
                }
                temp = temp.next;                
            }
        }

        System.out.println("The distance is: " + distance);

    }
}
