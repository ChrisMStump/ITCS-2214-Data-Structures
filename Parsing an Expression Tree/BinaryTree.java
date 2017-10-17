import java.lang.*;
/**
 * This class constructs the expression tree and traverses in prefix, infix and postfix.
 * 
 * @author Christopher Stump
 * @version 11/8/2015
 */
public class BinaryTree {

    Node root; //Root of the tree.
    String s = ""; //Used hold the prefix, infix and postfix expressions.
    int arrayCount = 0; //How many elements are in the nodeArray.

    /*
     * This method completely creates the tree. It takes in the evaluated
     * prefix expression and creates the tree from the bottom-up. If the 
     * read element is an operator then it will take the elements to the
     * right and make them it's children. The subtree will then be stored
     * back into the array and the operands will be deleted. If it is the
     * last element to be read, then that element will be set as the tree's
     * root and the two remaining subtrees will be the roots children.
     * Voila! The tree has been created.
     */
    public void createTree(String[] keys){
        Node[] nodeArray = new Node[keys.length];
        for(int i = keys.length; i > 0; i--){            
            if((keys[i-1].equals("+") || keys[i-1].equals("-") || keys[i-1].equals("*") || keys[i-1].equals("/")) && i != 1){
                Node newNode = new Node(keys[i-1]);
                newNode.leftChild = nodeArray[arrayCount-1];
                newNode.rightChild = nodeArray[arrayCount-2];
                nodeArray[arrayCount-2] = null;
                nodeArray[arrayCount-1] = null;
                nodeArray[arrayCount-2] = newNode;
                arrayCount--;
            }
            else if((keys[i-1].equals("+") || keys[i-1].equals("-") || keys[i-1].equals("*") || keys[i-1].equals("/")) && i == 1){
                Node newNode = new Node(keys[i-1]);
                root = newNode;
                newNode.leftChild = nodeArray[arrayCount-1];
                newNode.rightChild = nodeArray[arrayCount-2];
                nodeArray[arrayCount-2] = null;
                nodeArray[arrayCount-1] = null;
                nodeArray[arrayCount] = null;
                nodeArray[arrayCount-2] = newNode;
                arrayCount--;
            }
            else{
                Node newNode = new Node(keys[i-1]);
                nodeArray[arrayCount] = newNode;
                arrayCount++;
            }
        }
    }
    
    /*
     * Called in the driver to reset String s.
     * It could not be done since it would be
     * unreachable.
     */
    public void resetS(){
        s = "";
    }

    // All nodes are visited in ascending order
    // Recursion is used to go to one node and
    // then go to its child nodes and so forth

    public String inOrderTraverseTree(Node focusNode){        
        if (focusNode != null){

            // Traverse the left node

            inOrderTraverseTree(focusNode.leftChild);

            // Visit the currently focused on node

            s = s.concat(focusNode.toString() + " ");

            // Traverse the right node

            inOrderTraverseTree(focusNode.rightChild);

        }        
        return s;
    }    
   

    public String preorderTraverseTree(Node focusNode){
        if (focusNode != null){

            s = s.concat(focusNode.toString() + " ");

            preorderTraverseTree(focusNode.leftChild);
            
            preorderTraverseTree(focusNode.rightChild);

        }
        return s;
    }

    public String postOrderTraverseTree(Node focusNode){
        if (focusNode != null){

            postOrderTraverseTree(focusNode.leftChild);
            
            postOrderTraverseTree(focusNode.rightChild);

            s = s.concat(focusNode.toString() + " ");

        }
        return s;
    }
}