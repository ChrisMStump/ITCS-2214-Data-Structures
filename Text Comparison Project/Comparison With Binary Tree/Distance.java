
/**
 * Takes in two Binary Trees and calculates the distance of the two texts.
 * 
 * @author Christopher Stump 
 * @version 11/18/2015
 */
public class Distance
{
    private Node ref;
    private Node ref2;
    Node[] nodes1;
    Node[] nodes2;
    private String temp1 = null;
    private String temp2 = null;
    private double wordCount = 0;
    private double wordCount1 = 0;
    private double wordCount2 = 0;
    private int count = 0;
    private int uniqueWords1 = 0;
    private int uniqueWords2 = 0;
    private double value = 0;
    private int capacity = 0;
    private int arrayCount = 0;
    private double v1 = 0;
    private double v2 = 0;
    private double v3 = 0;
    private double sum = 0;
    public Distance()
    {
    }

    public Distance(Node p, Node q){
        countLeaves(p);
        uniqueWords1 = count;
        wordCount1 = wordCount;
        count = 0;        
        wordCount = 0;
        
        countLeaves(q);
        uniqueWords2 = count;
        wordCount2 = wordCount;
        count = 0;        
        wordCount = 0;
        
        nodes1 = new Node[uniqueWords1];
        nodes2 = new Node[uniqueWords2];
        inOrderTraverseTreeOne(p);
        arrayCount = 0;
        inOrderTraverseTreeTwo(q);
        arrayCount = 0;

        //Get frequencies for text 1.
        for(int i = 0; i < uniqueWords1; i++){
            ref = nodes1[i];
            value = ref.getValue();
            value = (value/wordCount1);
            ref.setValue(value);
            nodes1[i] = ref;
            //System.out.println(ref.getValue());
        }
        //Get frequencies for text 2.
        for(int i = 0; i < uniqueWords2; i++){
            ref = nodes2[i];
            value = ref.getValue();
            value = (value/wordCount2);
            ref.setValue(value);
            nodes2[i] = ref;
            //System.out.println(ref.getValue());
        }

        capacity = uniqueWords1 + uniqueWords2;
        Node[][] pairHisto = new Node[capacity][capacity];
        //Create a 2D array to compare the two texts.
        for(int i = 0; i < uniqueWords1; i++){
            ref = nodes1[i];
            pairHisto[0][i] = ref;
            arrayCount++;     
        }
        for(int i = 0; i < uniqueWords2; i++){
            ref2 = nodes2[i];
            temp2 = ref2.getKey();            
            ref = pairHisto[0][i];               
            if(pairHisto[0][i] != null){
                temp1 = ref.getKey(); 
                if(temp1.equals(temp2)){                    
                    pairHisto[1][i] = ref2;
                }
                else{
                    pairHisto[1][arrayCount] = ref2;
                    arrayCount++;
                }
            }          
        }

        //for(int i = 0; i < capacity; i++){
        // System.out.println(pairHisto[0][i] + " " + pairHisto[1][i]);
        //}
        Double[] distance = new Double[capacity];
        //Subtract the frequencies and sum them up to get distance.
        for(int i = 0; i < capacity; i++){
            ref = pairHisto[0][i];
            if(pairHisto[0][i] == null){
                v1 = 0;
            }
            else{
                v1 = ref.getValue();
            }
            ref2 = pairHisto[1][i];
            if(pairHisto[1][i] == null){
                v2 = 0;
            }
            else{
                v2 = ref2.getValue();
            }            
            v3 = Math.abs(v1-v2);
            sum = sum + v3;
            v3 = 0;
        }              
        System.out.println("The distance is: " + sum);

    }

    public void inOrderTraverseTreeOne(Node focusNode){
        if(focusNode != null){

            // Traverse the left node

            inOrderTraverseTreeOne(focusNode.leftChild);

            // Visit the currently focused on node

            nodes1[arrayCount] = focusNode;
            arrayCount++;

            // Traverse the right node

            inOrderTraverseTreeOne(focusNode.rightChild);

        }        
    }

    public void inOrderTraverseTreeTwo(Node focusNode){
        if(focusNode != null){

            // Traverse the left node

            inOrderTraverseTreeTwo(focusNode.leftChild);

            // Visit the currently focused on node

            nodes2[arrayCount] = focusNode;
            arrayCount++;

            // Traverse the right node

            inOrderTraverseTreeTwo(focusNode.rightChild);

        }
    }

    public void countLeaves(Node focusNode){
        if(focusNode != null){

            // Traverse the left node

            countLeaves(focusNode.leftChild);

            // Visit the currently focused on node

            count += 1;
            wordCount += focusNode.getValue();

            // Traverse the right node

            countLeaves(focusNode.rightChild);

        }   
    }
}
