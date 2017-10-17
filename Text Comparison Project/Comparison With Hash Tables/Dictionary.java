
/**
 * Dictionary interface.
 * 
 * @author Christopher Stump
 * @version 11/27/2015
 */
public interface Dictionary
{
    public void add(String s);
    public Pair contains(String s);
    public void insert(Pair p);
    public int stringHashFunction(String s);
    public Pair containsWord(String s);
}
