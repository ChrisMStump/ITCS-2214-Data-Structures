/**
 * Creates the array of pairs. In this case, we are using sorted arrays which takes advantage of binary search.
 * 
 * @author Christopher Stump
 * @version 10/28/2015
 */
public class ArrayPair implements Dictionary
{
    private int capacity = 100; //Default capacity for pair array.
    private int cardinality = 0; //This is how full the array is.
    Pair[] pairs = new Pair[capacity]; //This creates an array of pairs.
    private Pair ref; //Used to store reference to an array slot.
    private int iterator = 0; //Used to iterate through the array.
    private boolean contains; //Does the arrayPair already contain the key?
    private boolean sentinel; //Used to escape the contains while loop.
    private String temp; //Temporarily holds the key.
    private int replace; //Holds the position in which to insert a key after binary search.
    public ArrayPair(){
    }
    /*
     * Searches the arrayPair array to determine if the word already exists.
     * If the word does not already exist, then it calls the binary search to find where
     * it should be inserted. Once that is done it shifts all the elements to the
     * right and creates the new pair at the position.
     * It also doubles the size of the array once it reaches capacity.
     * @param The string to store.
     */
    public void add(String s){
        if(cardinality == (capacity)-1){
            doubleArray();
        }    
        if(!this.contains(s)){
            cardinality++;
            this.binarySearch(s);
            for(int i = cardinality; i > replace; i--){
                pairs[i] = pairs[i-1];
            }
            pairs[replace] = new Pair(s, 1);
            reset();
        }
        else{
            ref.increaseValue();
            reset();
        }
    }
    /*
     * Method traverses the array to determine whether or not the string exists or not.
     * @param String s - The string to find.
     * @return boolean - Does the array list contain this string?
     */
    public boolean contains(String s){
        while((iterator < cardinality) && (sentinel == false)){
            ref = pairs[iterator];
            temp = ref.getKey();
            if(temp.equals(s)){             
                sentinel = true;
                contains = true;
            }
            else
                contains = false;        
            iterator++;
        }
        sentinel = false;
        return contains;
    }
    /*
     * Resets the iterator and replace to 0.
     */
    public void reset(){
        iterator = 0;
        replace = 0;
    }
    public boolean isValid(){
        return false;
    }
    /*
     * Increases iterator by one.
     */
    public void next(){
        iterator++;
    }
    /*
     * Returns the current string at the iterator.
     * @return String - Returns the Key.
     */
    public String getCurrentKey(){
        return pairs[iterator].getKey();
    }
    /*
     * Returns the current value at the iterator.
     * @return double - Returns the value.
     */
    public double getCurrentValue(){
        return pairs[iterator].getValue();
    }
    /*
     * Doubles the array to make room for more elements.
     */
    public void doubleArray(){
        Pair[] tempArray = new Pair[capacity * 2];
        for(int i = 0; i < capacity; i++){
            tempArray[i] = pairs[i];
        }
        pairs = tempArray;
        capacity = (capacity * 2);
    }
    /*
     * Returns the array of pairs to be used in another class.
     * @return Pair[] - Array of pairs to calculate distance.
     */
    public Pair[] getArray(){
        return pairs;
    }
    /*
     * Binary search to find where to insert the pair.
     * @param String s - Takes in the string to compare to other strings in the array.
     */
    public void binarySearch(String s){
        int low = 0;
        int high = cardinality - 1;
        while(high >= low){
            int middle = (low + high) / 2;
            if(this.pairs[middle] == null){
                break;
            }
            if(this.pairs[middle].getKey().compareTo(s) < 0){
                low = middle + 1;
            }
            if(this.pairs[middle].getKey().compareTo(s) > 0){
                high = middle - 1;
            }
            replace = low;
        }   
    }
    /*
     * Converts the array of Pairs into text format.
     */
    public String toString(){
        String s = null;
        for(int i = 0; i < cardinality; i++){
            ref = pairs[i];
            s = (s + "\n" + ref.toString());
        }
        return s;
    }
}
