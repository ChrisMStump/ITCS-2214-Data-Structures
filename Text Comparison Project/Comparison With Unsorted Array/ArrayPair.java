/**
 * Creates the array of pairs.
 * 
 * @author Christopher Stump
 * @version 10/6/15
 */
public class ArrayPair implements Dictionary
{
    private int capacity = 100; //Default capacity for pair array.
    private int cardinality = 0; //This is how full the array is.
    Pair[] pairs = new Pair[capacity]; //This creates an array of pairs.
    private Pair ref; //Used to store reference to an array slot.
    private int iterator = 0; //Used to iterate through the array.
    private String currentKey;
    private int currentValue;
    private boolean contains;
    private boolean sentinel;
    private String temp;
    public ArrayPair(){
    }
    public void add(String s){
        if(cardinality == (capacity)-1){
            doubleArray();
        }    
        if(!this.contains(s)){
            pairs[cardinality] = new Pair(s, 1);
            cardinality++;
            reset();
        }
        else{
            ref.increaseValue();
            reset();
        }
    }
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
    public void reset(){
        iterator = 0;
    }
    public boolean isValid(){
        return false;
    }
    public void next(){
        iterator++;
    }
    public String getCurrentKey(){
        return pairs[iterator].getKey();
    }
    public double getCurrentValue(){
        return pairs[iterator].getValue();
    }
    public void doubleArray(){
        Pair[] tempArray = new Pair[capacity * 2];
        for(int i = 0; i < capacity; i++){
            tempArray[i] = pairs[i];
        }
        pairs = tempArray;
        capacity = (capacity * 2);
    }
    public Pair[] getArray(){
        return pairs;
    }
    public String toString(){
        String s = null;
        for(int i = 0; i < cardinality; i++){
            ref = pairs[i];
            s = (s + "\n" + ref.toString());
        }
        return s;
    }
}
