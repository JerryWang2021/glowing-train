/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SummativeSums;

/**
 *
 * @author Jerry
 */
public class SummativeSums {
    
        public static void main(String[] args) {
            
            int [] array1 = { 1, 90, -33, -55, 67, -16, 28, -55, 15 };
            int [] array2 = {  999, -60, -77, 14, 160, 301  };
            int [] array3 = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 
                            140, 150, 160, 170, 180, 190, 200, -99 };
            
            //call sumOfArray method inside main and print out for above three arrays
            System.out.println("#1  Array Sum: " + sumOfArray(array1));
            System.out.println("#2  Array Sum: " + sumOfArray(array2));
            System.out.println("#3  Array Sum: " + sumOfArray(array3));
        }
        //static method about int array, sum all elements, and return the result
        static int sumOfArray(int [] array) {
            int sum = 0;
            for( int value : array) {
                sum += value;
            }return sum;
        }
}