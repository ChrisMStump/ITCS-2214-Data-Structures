import java.util.*;
import java.io.*;
import java.lang.*;
/**
 * Reads in a file and tokenizes each word. Sends them to arrayPair to create pairs to find distance.
 * 
 * @author Christopher Stump 
 * @version 10/6/15
 */
public class Driver
{
    public static void main(String [] args) throws IOException
    {
        Scanner keyboard = new Scanner(System.in); //Get input from the keyboard
        StringTokenizer stok; //stok will hold all the strings after they are tokenized
        String fileName; //fileName 1 and 2 will hold the directory for each text
        String fileName2;
        String temp; //temp will hold all the strings after they are tokenized
        
        Pair[] pairs1; //These array pairs will hold the array of key and values for each text
        Pair[] pairs2;        
        
        int tokenCount1 = 0;
        int tokenCount2 = 0;
        
        char delimit = (char)160;
        String characterToString = Character.toString(delimit); //G
        
        System.out.print("Enter the location of the first file: "); //Ask the user for the directories.
        fileName = keyboard.nextLine();
        System.out.print("Enter the location of the second file: ");
        fileName2 = keyboard.nextLine();
        
        System.out.println("This process will take some time depending on the size.");
        
        /*
         * ********************************************************************************************************************************************************************************************************
         */
        
        Scanner inputFile = new Scanner(new File(fileName));        
        //Count the ammount of tokens to know the arraySize to be created.       
        while(inputFile.hasNext())
        {
            stok = new StringTokenizer(inputFile.nextLine(), ".,\"/?!_-(){}[]<> 0123456789#*@$%^&';:+-*/=" + delimit);
            while(stok.hasMoreTokens())
            {
                temp = stok.nextToken();
                tokenCount1++;
            }
        }
        //System.out.println(arrayPair1.toString());
        inputFile.close();
        System.out.println(tokenCount1);
        ArrayPair arrayPair1 = new ArrayPair(tokenCount1);
        
        inputFile = new Scanner(new File(fileName));        
        //Tokenize the first text, send to arrayPair1, which creates the pair and stores them in an array.        
        while(inputFile.hasNext())
        {
            stok = new StringTokenizer(inputFile.nextLine(), ".,\"/?!_-(){}[]<> 0123456789#*@$%^&';:+-*/=" + delimit);
            while(stok.hasMoreTokens())
            {
                temp = stok.nextToken();
                temp = temp.toLowerCase();
                arrayPair1.add(temp);
                //System.out.println(temp);
            }
        }
        //System.out.println(arrayPair1.toString());
        inputFile.close();
        //arrayPair1.displayTheArray();
        
        /*
         * ********************************************************************************************************************************************************************************************************
         */
        
        Scanner inputFile2 = new Scanner(new File(fileName2));
        //Count the ammount of tokens to know the arraySize to be created.       
        while(inputFile2.hasNext())
        {
            stok = new StringTokenizer(inputFile2.nextLine(), ".,\"/?!_-(){}[]<> 0123456789#*@$%^&';:+-*/=" + delimit);
            while(stok.hasMoreTokens())
            {
                temp = stok.nextToken();
                tokenCount2++;
            }
        }
        //System.out.println(arrayPair1.toString());
        inputFile.close();
        System.out.println(tokenCount2);
        ArrayPair arrayPair2 = new ArrayPair(tokenCount2);
        
        inputFile2 = new Scanner(new File(fileName2));
        //Tokenize the second text, send to arrayPair2, which also creates the pairs and stores them in an array.
        while(inputFile2.hasNext())
        {
            stok = new StringTokenizer(inputFile2.nextLine(), ".,\"/?!_-(){}[]<> 0123456789#*@$%^&';:+-*/=");
            while(stok.hasMoreTokens())
            {
                temp = stok.nextToken();
                temp = temp.toLowerCase();
                arrayPair2.add(temp);
                //System.out.println(temp);
            }
        }
        //System.out.println(arrayPair2.toString());
        inputFile.close();
        //arrayPair2.displayTheArray();
        /*
         * ********************************************************************************************************************************************************************************************************
         */
        System.out.println("Done");
        //pairs1 = arrayPair1.getArray(); //Pairs 1 has arrayPairs1's array.
        //pairs2 = arrayPair2.getArray(); //Pairs 2 has arrayPairs2's array.
        
        double timeBegin = 0; //Hold the timings in nano seconds
        double timeEnd = 0;
        double finalTime = 0; //timeEnd - timeBegin
        Double[] times = new Double[30]; //Will hold the total time for each run
        double powerNums = 0; //Sum of the times when they are squared.
        double standDev = 0; //Will hold the standard deviation
        final double tCoeff = 2.042; //95% confidence interval with 30 tests
        double mean = 0; //Will hold the mean time
        double upperConfidence = 0; //Holds the upper confidence interval
        double lowerConfidence = 0; //Holds the lower confidence interval
        
        PrintWriter pr = new PrintWriter("outputFile.txt");
        
        /* Creates a new instance of Distance(). Both arrays are sent. This class will calculate the distance
         * between both texts. 
         * This also times the time it takes to find the distance.
        */
        for(int i = 0; i < 30; i++){
            timeBegin = System.nanoTime();
            Distance distance = new Distance(arrayPair1, arrayPair2);
            timeEnd = System.nanoTime();
            finalTime = timeEnd - timeBegin;
            times[i] = finalTime;
        }
        //Sums up all the numbers in order to get the mean.
        for(int i = 0; i < 30; i++){
            mean = mean + times[i];
        }
        //Used to calculate standard deviation.
        for(int i = 0; i < 30; i++){
            powerNums = powerNums + Math.pow(times[i], 2);
        }
        standDev = Math.sqrt(powerNums); //Gets the final standard deviation.
        mean = (mean/30); //Gives us the mean of the times.
        
        upperConfidence = mean + (tCoeff*(standDev/(Math.sqrt(30))));
        lowerConfidence = mean - (tCoeff*(standDev/(Math.sqrt(30))));
        
        for(int i = 0; i < 30; i++){
            pr.println(times[i]);
        }
        pr.println("Mean(Average): " + mean);
        pr.println("Upper Confidence: " + upperConfidence);
        pr.println("Lower Confidence: " + lowerConfidence);
        pr.close();
        //System.exit(0);
    }
}
