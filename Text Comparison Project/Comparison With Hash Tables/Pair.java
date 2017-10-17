/**
 * Used to create pair objects
 * 
 * @author Christopher Stump 
 * @version 9/28/15
 */
public class Pair
{
    public String k; //Key
    public double v; //Value
    public int hashValue; //The hash code of the word.
    public Pair next; //The next word in the list.
    public Pair(){
    }
    public Pair(String k, double v)
    {
        this.k = k;
        this.v = v;
    }
    public void setKey(String k)
    {
        this.k = k;
    }
    public void setValue(double v)
    {
        this.v = v;
    }
    public String getKey(){
        return k;
    }
    public double getValue(){
        return v;
    }
    public void increaseValue(){
        this.v = v+1;
    }
    public String toString(){
        return "Key: " + this.k + " Value: " + this.v;
    }
}
