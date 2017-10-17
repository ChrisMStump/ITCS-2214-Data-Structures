
/**
 * Node object. Holds a Key and has children.
 * 
 * @author Christopher Stump
 * @version 11/8/2015
 */
class Node{
    String k;
    double v;

    Node leftChild;
    Node rightChild;
    
    public Node(){
    }
    public Node(String k){
        this.k = k;
        this.v = 1;
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
        return this.k;
    }
    public double getValue(){
        return this.v;
    }
    public void increaseValue(){
        this.v = v+1;
    }
    public String toString(){
        return "Key: " + this.k + " Value: " + this.v;
    }
}