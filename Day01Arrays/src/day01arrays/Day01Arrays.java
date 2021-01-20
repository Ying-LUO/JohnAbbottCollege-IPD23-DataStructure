/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day01arrays;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ewuzhou
 */
public class Day01Arrays {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner console = new Scanner(System.in);
        boolean flag = true;
        
        while(flag){
            System.out.println("Please enter the size of the array to create, must be 1 or greater: ");
            try{
                int size = console.nextInt();
                if(size >0){
                    int[] intArray = new int[size]; 
                    int[] primeArray = new int[size];
                    for(int i=0; i<size; i++){
                        intArray[i] = new Random().nextInt(100);
                    }
                    String[] strInt = Arrays.stream(intArray).mapToObj(String::valueOf).toArray(String[]::new);
                    
                    int j=0;
                    for(int i=0; i<size; i++){
                        if(isPrime(intArray[i])){
                            primeArray[j] = intArray[i];
                            j++;
                        }
                    }
                    System.out.println("All numbers in Int Array:\n" + String.join(";", strInt));
                    for (int i = 0; i < primeArray.length; i++) {
                        System.out.printf("%s%d", i == 0 ? "" : ",", primeArray[i]);
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
        for (int i = 2; i <= val / 2; ++i) {
            // condition for nonprime number
            if (val % i == 0) {
                return false;
            }
        }
        return true;
    }
}
