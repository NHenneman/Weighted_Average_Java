/*
Author: Nathan Henneman
Date: 15 November 2016
*/
package weighted.interval;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

//creates a custom object, Interval, which stores
//the infomation about a certain interval:
//Start time, Finish time, and Profit from job
class Interval{
    int Start, Finish, Profit;
    public Interval(int start, int finish, int profit){
        this.Start = start;
        this.Finish = finish;
        this.Profit = profit;
    }
}

public class WeightedInterval {
   
    //File_Input reads in a file from user input to read info from
    static String File_Input(){
        Scanner scanner = new Scanner(System.in);      
        String file;
        
        System.out.print("What file do you want to read in?\n");
        System.out.print("Enter file in form \"FileName.txt\": ");
        file = scanner.next();
        return(file);
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Interval> Intervals = new ArrayList<>();
        FileReader file = new FileReader(File_Input());
        Scanner scanner = new Scanner(file); 
        int num_Intervals, start, stop, profit;
        int[] p;
        
        //Gets the number of intervals to be read from file
        num_Intervals = scanner.nextInt();
        
        //reads each interval set from file 
        //and saves the info to an array of
        //Interval Objects
        for(int i = 0; i < num_Intervals; i++){
           start = scanner.nextInt();
           stop = scanner.nextInt();
           profit = scanner.nextInt();
           Intervals.add(new Interval(start,stop,profit));
        }
        
        //create a array same size as interval array and fill with -1
        p = new int[num_Intervals];
        Arrays.fill(p, -1);
        
        //finds largest index i < j which is compatable with j
        for(int i = 0; i < num_Intervals; i++){
            for(int j = 0; j < i; j++){
                if(Intervals.get(i).Start >= Intervals.get(j).Finish){
                    p[i] = j;
                }
            }
        }
        
        int optimum = compute_Optimum(num_Intervals-1, Intervals, p);
       
        //Print to screen the optimum solution to the input data
        System.out.println("Optimum Profit: " + optimum);
    } 
    
    //Computes the optimum job path using recusion
    public static int compute_Optimum(int j, ArrayList<Interval> Intervals, int[] p){
        if(j == -1){
            return 0;
        }
        else{
            return max((Intervals.get(j).Profit + compute_Optimum(p[j], Intervals, p))
                        , compute_Optimum(j-1, Intervals, p));
        }
        
    }
    //determines if value a or b is larger and returns larger value
    public static int max(int a, int b){
        if(a >= b)
            return a;
        else
            return b;
    }
}
