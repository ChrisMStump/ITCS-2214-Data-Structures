import java.util.*;
import java.lang.*;

/**
 * Takes in prefix expression, evaluates it. It also sends it to be turned into a tree and the prefix, infix and postfix expressions will be printed.
 * 
 * @author Christopher Stump
 * @version 11/8/2015
 */
public class Driver
{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        StringTokenizer stok; 

        String prefixOne; //Prefix expressions the user enters.
        String prefixTwo;
        String temp; //Used to hold tokens.
        String s; //Holds the prefix, infix and postfix expressions.

        String[] copy; //Used to shrink arrays if the expression was evaluated.

        System.out.println("Enter the first expression in prefix form: ");
        prefixOne = keyboard.nextLine();

        System.out.println("Enter the second expression in prefix form: ");
        prefixTwo = keyboard.nextLine();

        int max;
        int counter = 0;     

        BinaryTree treeOne = new BinaryTree();
        BinaryTree treeTwo = new BinaryTree();

        stok = new StringTokenizer(prefixOne);
        max = stok.countTokens();
        String[] tempArrayOne = new String[max];
        
        /*
         * Here we will break up the expression into tokens
         * and then store them into an array.
         */
        
        while(stok.hasMoreTokens()){
            temp = stok.nextToken();
            tempArrayOne[counter] = temp;
            counter++;
        }
        counter = 0;
        
        
        /*
         * If the current token is an operator and the next
         * two tokens are integers, then they will be evaluated.
         * Everything will also be shifted to the left of the array.
         */
        for(int i = 0; i < max; i++){
            if(tempArrayOne[i].equals("+") || tempArrayOne[i].equals("-") || tempArrayOne[i].equals("*") || tempArrayOne[i].equals("/")){
                if(tempArrayOne[i+1].matches("[-+]?\\d+(\\.\\d+)?") && tempArrayOne[i+2].matches("[-+]?\\d+(\\.\\d+)?")){
                    if(tempArrayOne[i].equals("+")){
                        tempArrayOne[i] = Integer.toString(Integer.parseInt(tempArrayOne[i+1]) + Integer.parseInt(tempArrayOne[i+2]));
                        tempArrayOne[i+1] = null;
                        tempArrayOne[i+2] = null;
                        copy = new String[max-2];
                        for(int k = 0; k < max; k++){
                            if(tempArrayOne[k] != null){
                                copy[counter] = tempArrayOne[k];
                                counter++;
                            }
                        }
                        counter = 0;
                        max = max-2;
                        tempArrayOne = copy.clone();
                    }
                    if(tempArrayOne[i].equals("-")){
                        tempArrayOne[i] = Integer.toString(Integer.parseInt(tempArrayOne[i+1]) - Integer.parseInt(tempArrayOne[i+2]));
                        tempArrayOne[i+1] = null;
                        tempArrayOne[i+2] = null;
                        copy = new String[max-2];
                        for(int k = 0; k < max; k++){
                            if(tempArrayOne[k] != null){
                                copy[counter] = tempArrayOne[k];
                                counter++;
                            }
                        }
                        counter = 0;
                        max = max-2;
                        tempArrayOne = copy.clone();
                    }
                    if(tempArrayOne[i].equals("*")){
                        tempArrayOne[i] = Integer.toString(Integer.parseInt(tempArrayOne[i+1]) * Integer.parseInt(tempArrayOne[i+2]));
                        tempArrayOne[i+1] = null;
                        tempArrayOne[i+2] = null;
                        copy = new String[max-2];
                        for(int k = 0; k < max; k++){
                            if(tempArrayOne[k] != null){
                                copy[counter] = tempArrayOne[k];
                                counter++;
                            }
                        }
                        counter = 0;
                        max = max-2;
                        tempArrayOne = copy.clone();
                    }
                    if(tempArrayOne[i].equals("/")){
                        tempArrayOne[i] = Integer.toString(Integer.parseInt(tempArrayOne[i+1]) / Integer.parseInt(tempArrayOne[i+2]));
                        tempArrayOne[i+1] = null;
                        tempArrayOne[i+2] = null;
                        copy = new String[max-2];
                        for(int k = 0; k < max; k++){
                            if(tempArrayOne[k] != null){
                                copy[counter] = tempArrayOne[k];
                                counter++;
                            }
                        }
                        counter = 0;
                        max = max-2;
                        tempArrayOne = copy.clone();
                    }
                }
            }
        }

        /*
         * If there are three tokens left in the array
         * check to make sure they can be evaluated.
         * If so, evaluate them.
         */
        if(max == 3 && (tempArrayOne[1].matches("[-+]?\\d+(\\.\\d+)?") && tempArrayOne[2].matches("[-+]?\\d+(\\.\\d+)?"))){
            copy = new String[1];
            if(tempArrayOne[0].equals("+")){
                copy[0] = Integer.toString(Integer.parseInt(tempArrayOne[1]) + Integer.parseInt(tempArrayOne[2]));
            }
            if(tempArrayOne[0].equals("-")){
                copy[0] = Integer.toString(Integer.parseInt(tempArrayOne[1]) - Integer.parseInt(tempArrayOne[2]));
            }
            if(tempArrayOne[0].equals("*")){
                copy[0] = Integer.toString(Integer.parseInt(tempArrayOne[1]) * Integer.parseInt(tempArrayOne[2]));
            }
            if(tempArrayOne[0].equals("/")){
                copy[0] = Integer.toString(Integer.parseInt(tempArrayOne[1]) / Integer.parseInt(tempArrayOne[2]));
            }
            tempArrayOne = copy.clone();
        }
        
        
        /*
         * If the expression has not been evaluated all the way,
         * then we will create the expression tree and print
         * out the prefix, infix and postfix expressions.
         * We will do this by traversing the array.
         * 
         * If the expression has been evaluated completely
         * to a numerical value, then we will just print out
         * it's value.
         */
        if(tempArrayOne.length > 1){
            treeOne.createTree(tempArrayOne);
            System.out.println("********************");

            System.out.println("Infix: ");
            s = treeOne.inOrderTraverseTree(treeOne.root);
            System.out.println(s);
            treeOne.resetS();

            System.out.println("Prefix: ");
            s = treeOne.preorderTraverseTree(treeOne.root);
            System.out.println(s);
            treeOne.resetS();

            System.out.println("Postfix: ");
            s = treeOne.postOrderTraverseTree(treeOne.root);
            System.out.println(s);
            treeOne.resetS();

            System.out.println("********************");
        }
        else{
            System.out.println("Infix: " + tempArrayOne[0]);            

            System.out.println("Prefix: " + tempArrayOne[0]);
          
            System.out.println("Postfix: " + tempArrayOne[0]);
           
        }

        
        /*
         * ********************************************************************************************************************************************************************************************************************************************************
         */
        /*
         * The code below is a copy of the code from above.
         * This just works with the second expression.
         */

        stok = new StringTokenizer(prefixTwo);
        max = stok.countTokens();
        String[] tempArrayTwo = new String[max];
        while(stok.hasMoreTokens()){
            temp = stok.nextToken();
            tempArrayTwo[counter] = temp;
            counter++;
        }
        counter = 0;
        for(int i = 0; i < max; i++){
            if(tempArrayTwo[i].equals("+") || tempArrayTwo[i].equals("-") || tempArrayTwo[i].equals("*") || tempArrayTwo[i].equals("/")){
                if(tempArrayTwo[i+1].matches("[-+]?\\d+(\\.\\d+)?") && tempArrayTwo[i+2].matches("[-+]?\\d+(\\.\\d+)?")){
                    if(tempArrayTwo[i].equals("+")){
                        tempArrayTwo[i] = Integer.toString(Integer.parseInt(tempArrayTwo[i+1]) + Integer.parseInt(tempArrayTwo[i+2]));
                        tempArrayTwo[i+1] = null;
                        tempArrayTwo[i+2] = null;
                        copy = new String[max-2];
                        for(int k = 0; k < max; k++){
                            if(tempArrayTwo[k] != null){
                                copy[counter] = tempArrayTwo[k];
                                counter++;
                            }
                        }
                        counter = 0;
                        max = max-2;
                        tempArrayTwo = copy.clone();
                    }
                    if(tempArrayTwo[i].equals("-")){
                        tempArrayTwo[i] = Integer.toString(Integer.parseInt(tempArrayTwo[i+1]) - Integer.parseInt(tempArrayTwo[i+2]));
                        tempArrayTwo[i+1] = null;
                        tempArrayTwo[i+2] = null;
                        copy = new String[max-2];
                        for(int k = 0; k < max; k++){
                            if(tempArrayTwo[k] != null){
                                copy[counter] = tempArrayTwo[k];
                                counter++;
                            }
                        }
                        counter = 0;
                        max = max-2;
                        tempArrayTwo = copy.clone();
                    }
                    if(tempArrayTwo[i].equals("*")){
                        tempArrayTwo[i] = Integer.toString(Integer.parseInt(tempArrayTwo[i+1]) * Integer.parseInt(tempArrayTwo[i+2]));
                        tempArrayTwo[i+1] = null;
                        tempArrayTwo[i+2] = null;
                        copy = new String[max-2];
                        for(int k = 0; k < max; k++){
                            if(tempArrayTwo[k] != null){
                                copy[counter] = tempArrayTwo[k];
                                counter++;
                            }
                        }
                        counter = 0;
                        max = max-2;
                        tempArrayTwo = copy.clone();
                    }
                    if(tempArrayTwo[i].equals("/")){
                        tempArrayTwo[i] = Integer.toString(Integer.parseInt(tempArrayTwo[i+1]) / Integer.parseInt(tempArrayTwo[i+2]));
                        tempArrayTwo[i+1] = null;
                        tempArrayTwo[i+2] = null;
                        copy = new String[max-2];
                        for(int k = 0; k < max; k++){
                            if(tempArrayTwo[k] != null){
                                copy[counter] = tempArrayTwo[k];
                                counter++;
                            }
                        }
                        counter = 0;
                        max = max-2;
                        tempArrayTwo = copy.clone();
                    }
                }
            }
        }

        //Check to make sure everything has been evaluated.
        if(max == 3 && (tempArrayTwo[1].matches("[-+]?\\d+(\\.\\d+)?") && tempArrayTwo[2].matches("[-+]?\\d+(\\.\\d+)?"))){
            copy = new String[1];
            if(tempArrayTwo[0].equals("+")){
                copy[0] = Integer.toString(Integer.parseInt(tempArrayTwo[1]) + Integer.parseInt(tempArrayTwo[2]));
            }
            if(tempArrayTwo[0].equals("-")){
                copy[0] = Integer.toString(Integer.parseInt(tempArrayTwo[1]) - Integer.parseInt(tempArrayTwo[2]));
            }
            if(tempArrayTwo[0].equals("*")){
                copy[0] = Integer.toString(Integer.parseInt(tempArrayTwo[1]) * Integer.parseInt(tempArrayTwo[2]));
            }
            if(tempArrayTwo[0].equals("/")){
                copy[0] = Integer.toString(Integer.parseInt(tempArrayTwo[1]) / Integer.parseInt(tempArrayTwo[2]));
            }
            tempArrayTwo = copy.clone();
        }

        if(tempArrayTwo.length > 1){
            treeTwo.createTree(tempArrayTwo);
            
            System.out.println("********************");

            System.out.println("Infix: ");
            s = treeTwo.inOrderTraverseTree(treeTwo.root);
            System.out.println(s);
            treeTwo.resetS();

            System.out.println("Prefix: ");
            s = treeTwo.preorderTraverseTree(treeTwo.root);
            System.out.println(s);
            treeTwo.resetS();

            System.out.println("Postfix: ");
            s = treeTwo.postOrderTraverseTree(treeTwo.root);
            System.out.println(s);
            treeTwo.resetS();

            System.out.println("********************");
        }
        else{
            System.out.println("Infix: " + tempArrayTwo[0]);            

            System.out.println("Prefix: " + tempArrayTwo[0]);
          
            System.out.println("Postfix: " + tempArrayTwo[0]);
           
        }

        /*
         * ********************************************************************************************************************************************************************************************************************************************************
         */
        /*
         * The below code prints whether the two expressions are the same.
         * I did not take commutativity into account.
         * That being said if the data entered for both are equal,
         * then we can say they are the same.
         * If not then they are not the same.
         */
        if(prefixOne.equals(prefixTwo)){
            System.out.println("These two extressions are the same.");
            System.out.println("Commutativity is not taken into account.");
        }
        else{
            System.out.println("These two expressions are not the same.");
            System.out.println("Commutativity is not taken into account.");
        }
        
    }
}
