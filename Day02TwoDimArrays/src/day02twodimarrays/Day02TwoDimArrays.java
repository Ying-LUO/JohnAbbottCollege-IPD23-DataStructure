/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day02twodimarrays;

import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.max;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 *
 * @author ewuzhou
 */
public class Day02TwoDimArrays {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner console = new Scanner(System.in);
        boolean flag = true;
        int sum=0;
        int[] pair = new int[2];
        
        while(flag){
            System.out.println("Please enter the width and length of the array to create, must be 1 or greater: ");
            try{
                int width = console.nextInt();
                int length = console.nextInt();
                
                if(width >0 && length >0){
                    
                    int[][] intTwoDimArray = new int[width][length]; 
                    int[] sumRow= new int[length];
                    int[] sumColumn = new int[width];
                    List<Integer> allInt = new ArrayList<>();
                    
                    for(int i=0; i<width; i++){
                        String[] strLine = new String[length];
                            for(int j=0; j<length; j++){
                                intTwoDimArray[i][j] = randInt(-99, 99);
                                allInt.add(intTwoDimArray[i][j]);
                                sumRow[j] += intTwoDimArray[i][j];
                                sumColumn[i] += intTwoDimArray[i][j];
                                strLine[j] = String.format("%3s", intTwoDimArray[i][j]);
                            }
                        sum += sumColumn[i];
                        System.out.println(String.join(";", strLine));
                   }
                    
                    String[] strRow = Arrays.stream(sumRow).mapToObj(String::valueOf).toArray(String[]::new);
                    String[] strColumn = Arrays.stream(sumColumn).mapToObj(String::valueOf).toArray(String[]::new);
                    System.out.println("Sum of all numbers in the array: " + sum);
                    System.out.println("Sum of each Row of the array: " + String.join(";", strColumn));
                    System.out.println("Sum of each Column of the array: " + String.join(";", strRow));
                    System.out.println("Standard deviation of all numbers in the array: " + String.format("%.3f", calculateSD(allInt)));
                    
                    for(int i=0; i<width; i++){
                        pair[0] = intTwoDimArray[i][0];
                        for(int j=1; j<length; j++){
                            pair[1] = intTwoDimArray[i][j];
                            int sumpair = pair[0]+pair[1];
                            if(isPrime(sumpair)){
                                System.out.println("\npair num 0: " + pair[0] + ", pair num 1: " + pair[1] + ", sum of pair: " + sumpair);
                            }
                        }
                    }
                    
                    for(int i=0; i<length; i++){
                        pair[0] = intTwoDimArray[0][i];
                        for(int j=1; j<width; j++){
                            pair[1] = intTwoDimArray[j][i];
                            int sumpair = pair[0]+pair[1];
                            if(isPrime(sumpair)){
                                System.out.println("\npair num 0: " + pair[0] + ", pair num 1: " + pair[1] + ", sum of pair: " + sumpair);
                            }
                        }
                    }
                    
                    flag= false;
                } else{
                    System.out.println("Size must be 1 or greater");
                }
            }catch(InputMismatchException ex){
                System.out.println("Exception: Please input valid number");
                flag=false;
            }
        }
    }
    
    public static boolean isPrime(int val) {
        if(val <0){
            return false;
        }
        for (int i = 2; i <= val / 2; ++i) {
            // condition for nonprime number
            if (val % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public static int randInt(int min, int max) {
        int randomNum = min + (int)(Math.random() * ((max - min) + 1));
        return randomNum;
    }
    
    public static double calculateSD(List<Integer> list)
    {
        int sum = 0;
        double standardDeviation = 0.0;
        int length = list.size();

        for(int num : list) {
            sum += num;
        }

        double mean = sum/length;

        for(int num: list) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation/length);
    }
}
