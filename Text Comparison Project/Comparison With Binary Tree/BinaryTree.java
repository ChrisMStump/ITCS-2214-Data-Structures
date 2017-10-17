/**
 * Creates a Binary Tree of the text.
 * 
 * @author Christopher Stump
 * @version 10/6/15
 */
public class BinaryTree implements Dictionary
{
    Node root;
    Node focusNode;
    public BinaryTree(){
    }
    public void add(String s){
        
		// Create a new Node and initialize it

		Node newNode = new Node(s);

		// If there is no root this becomes root

		if (root == null){

			root = newNode;

		} 
		else{
		    if(contains(s) != null){
		        focusNode.increaseValue();
		        return;
		    }

			// Set root as the Node we will start
			// with as we traverse the tree

			Node focusNode = root;

			// Future parent for our new Node

			Node parent;

			while(true){

				// root is the top parent so we start
				// there

				parent = focusNode;

				// Check if the new node should go on
				// the left side of the parent node

				if(s.compareTo(focusNode.getKey()) < 0){

					// Switch focus to the left child

					focusNode = focusNode.leftChild;

					// If the left child has no children

					if(focusNode == null){

						// then place the new node on the left of it

						parent.leftChild = newNode;
						return; // All Done

					}
				} 
				else{ // If we get here put the node on the right

					focusNode = focusNode.rightChild;

					// If the right child has no children

					if(focusNode == null){

						// then place the new node on the right of it

						parent.rightChild = newNode;
						return; // All Done

					}
				}
			}
		}
	}	
    public Node contains(String s){

		// Start at the top of the tree

		focusNode = root;

		// While we haven't found the Node
		// keep looking

		while (!s.equals(focusNode.getKey())){

			// If we should search to the left

			if (s.compareTo(focusNode.getKey()) < 0){

				// Shift the focus Node to the left child

				focusNode = focusNode.leftChild;

			} 
			else{

				// Shift the focus Node to the right child

				focusNode = focusNode.rightChild;

			}

			// The node wasn't found

			if (focusNode == null)
				return null;

		}

		return focusNode;

	}	
    public Node getTree(){
        return root;
    }
}
