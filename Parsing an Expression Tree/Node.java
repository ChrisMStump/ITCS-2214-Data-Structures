
/**
 * Node object. Holds a Key and has children.
 * 
 * @author Christopher Stump
 * @version 11/8/2015
 */
class Node{
	String key;

	Node leftChild;
	Node rightChild;

	Node(String key){
		this.key = key;
	}

	public String toString(){
		return this.key;
	}
}