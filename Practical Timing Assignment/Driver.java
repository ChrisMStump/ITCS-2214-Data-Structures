import java.io.*;
import java.util.*;
/**
 * This program will take in two parameters: the name of a file and an integer n. It will time how long it takes to find the min, max and sort the array of n numbers.
 * 
 * 
 * @author Christopher Stump
 * @version 9/07/2015
 */
public class Driver
{
    public static void main(String [ ] args) throws IOException
    {
        Scanner keyboard = new Scanner(System.in); //Get input from the user.
        Scanner inputFile;
        String fileName;
        int n; //How many elements?
        int min = 0; //Initialize the min and max.
        int max = 0;

        long startTime = 0; //These are used to find the time in nanoseconds.
        long totalTime = 0;
        
        int[] sample = new int[]{100, 1000, 5000, 10000, 50000, 100000, 500000, 1000000, 2500000, 5000000}; //I used this array for my convenience of automatically putting in the value for n.
        int[] num = new int[10];
        long[] mina = new long[30]; //These hold the average time for each the min, max and sorting.
        long[] maxa = new long[30];
        long[] sorta = new long[30];
        long amin = 0; //Mean min, max and sort values.
        long amax = 0;
        long asort = 0;
        
        long[]minNums = new long[30]; //Used to hold the timings for each iteration of n.
        long[]maxNums = new long[30];
        long[]sortNums = new long[30];
        
        System.out.println("Enter the location of the file: ");
        fileName = keyboard.nextLine();
        PrintWriter pw = new PrintWriter("outputFile.txt");
        for(int q=0; q < 10; q++)
        {
            System.out.println("Enter the amount of numbers to be read from the file:"); //Ask the user for n.
            //n = keyboard.nextInt();
            n = sample[q];
            num[q] = n;
            for(int h=0; h < 30; h++)
            {
                int[] numbers = new int[n];
                long[] format = new long[4];
                format[0] = n;
                
                inputFile = new Scanner(new File(fileName));
        
                for(int i = 0; i < n; i++) //Puts n n numbers into an array.
                {
                    numbers[i] = inputFile.nextInt();
                }
                /* 
                System.out.println("These are the first " + n + " numbers:\n");
                for(int i = 0; i < n; i++)
                {
                    System.out.println(numbers[i]);
                }
                */ //This was used to output the numbers in the array before it is sorted.
        
                //FINDING THE MINIMUM OF THE ARRAY
                startTime = System.nanoTime();
                min = numbers[0];
                for(int i = 0; i < n; i++)
                {
                    if(numbers[i] < min)
                    {
                        min = numbers[i];
                    }
                }
                totalTime = System.nanoTime() - startTime;
                System.out.println(totalTime);
                mina[h] = totalTime;
                minNums[h] = totalTime;
                /*
                System.out.println("The miniumum number is: ");
                System.out.println(min);
                */ //This was used to output the min number.
       
                //FINDING THE MAX OF THE ARRAY
                startTime = System.nanoTime();
                max = numbers[0];
                for(int i = 0; i < n; i++)
                {
                    if(numbers[i] > max)
                    {
                        max = numbers[i];
                    }
                }
                totalTime = System.nanoTime() - startTime;
                System.out.println(totalTime);
                maxa[h] = totalTime;
                maxNums[h] = totalTime;
                /*
                System.out.println("The maximum number is: ");
                System.out.println(max);
                */ //This was used to output the max number.
        
                //SORTING THE ARRAY
                startTime = System.nanoTime();
                Arrays.sort(numbers);
                totalTime = System.nanoTime() - startTime;
                System.out.println(totalTime);
                sorta[h] = totalTime;
                sortNums[h] = totalTime;
                /*
                System.out.println("The sorted array: ");
                for(int i = 0; i < n; i++)
                {
                    System.out.println(numbers[i]);
                }
                */
            }
            pw.println("These are the minimum numbers for " + n + ":");
            for(int i = 0; i < 30; i++) //Writes all the timings for finding the minimum.
            {
                pw.println(minNums[i]);
            }
            pw.println("These are the maximum numbers for " + n + ":");
            for(int i = 0; i < 30; i++) //Writes all the timiings for finding the maximum.
            {
                pw.println(maxNums[i]);
            }
            pw.println("These are the sort numbers for " + n + ":");
            for(int i = 0; i < 30; i++) //Writes all the timings for sorting the array of size n.
            {
                pw.println(sortNums[i]);
            }
            for(int i = 0; i < 30; i++) //Summations of min, max and sorting times.
            {
                amin += mina[i];
            }
            for(int i = 0; i < 30; i++)
            {
                amax += maxa[i];
            }
            for(int i = 0; i < 30; i++)
            {
                asort += sorta[i];
            }
            pw.println(num[q] + " " + (amin/30) + " " + (amax/30) + " " + (asort/30)); //Writes the average times for each min, max and sort to the output file.
        }
        pw.close(); //Close the PrintWriter to save.
    }
}
