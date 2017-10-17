
/**
 * Dictionary interface.
 * 
 * @author Christopher Stump
 * @version 10/6/15
 */
public interface Dictionary
{
    public void add(String s);
    public boolean contains(String s);
    public void reset();
    public boolean isValid();
    public void next();
    public String getCurrentKey();
    public double getCurrentValue();
}
