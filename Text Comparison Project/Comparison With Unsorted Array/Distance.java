import java.lang.*;
/**
 * Calculates the distance.
 * 
 * @author Christopher Stump
 * @version 10/6/2015
 */
public class Distance
{   
    private Pair[] pairs1; //Holds the array of pairs. Holds the Key Value pairs.
    private Pair[] pairs2;
    private double count1 = 0; //Holds how many words total are in each text.
    private double count2 = 0;
    private int nullCount1 = 0; //Holds the cardinality of each text.
    private int nullCount2 = 0;    
    private double value = 0; //Used to calculate the frequency of each word.
    private int capacity = 0;
    private Pair ref; //Used to reference pair objects in order to obtain the key and values.
    private Pair ref2;
    private String temp1 = null; //Will temporarily hold the keys for each text.
    private String temp2 = null;
    private double v1 = 0;
    private double v2 = 0;
    private double v3 = 0;
    private double sum = 0;
    private int arrayCount = 0;
    private boolean found = true;
    public Distance(){        
    }
    public Distance(Pair[] p, Pair[] q){
        pairs1 = p;
        pairs2 = q;
        //Calculate cardinality for pair 1.
        for(int i = 0; i < pairs1.length; i++){
            ref = pairs1[i];
            if(ref != null){
                count1 += ref.getValue();
                nullCount1++;
            }            
        }   
        //Calculate cardinality for pair 2.
        for(int i = 0; i < pairs2.length; i++){
            ref = pairs2[i];
            if(ref != null){
                count2 += ref.getValue();
                nullCount2++;
            }  
        }
        //Get frequencies for text 1.
        for(int i = 0; i < nullCount1; i++){
            ref = pairs1[i];
            value = ref.getValue();
            value = (value/count1);
            ref.setValue(value);
            pairs1[i] = ref;
            //System.out.println(ref.getValue());
        }
        //Get frequencies for text 2.
        for(int i = 0; i < nullCount2; i++){
            ref = pairs2[i];
            value = ref.getValue();
            value = (value/count2);
            ref.setValue(value);
            pairs2[i] = ref;
            //System.out.println(ref.getValue());
        }
        
        
        capacity = nullCount1 + nullCount2;
        Pair[][] pairHisto = new Pair[capacity][capacity];
        //Create a 2D array to compare the two texts.
        for(int i = 0; i < nullCount1; i++){
            ref = pairs1[i];
            pairHisto[0][i] = ref;
            arrayCount++;     
        }
        for(int i = 0; i < nullCount2; i++){
            ref2 = pairs2[i];
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
}
