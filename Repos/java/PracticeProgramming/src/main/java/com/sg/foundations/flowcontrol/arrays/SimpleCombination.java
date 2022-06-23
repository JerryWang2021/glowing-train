/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.arrays;

import java.util.Arrays;

/**
 *
 * @author Jerry
 */
public class SimpleCombination {
    
    public static void main(String[] args) {
        int[] firstHalf = {3, 7, 9, 10, 16, 19, 20, 34, 35, 45, 47, 49}; // 12 numbers
        int[] secondHalf = {51, 54, 68, 71, 75, 78, 82, 84, 85, 89, 91, 100}; // also 12!
        int length = firstHalf.length + secondHalf.length;

        int[] wholeNumbers = new int[length];
        
        int i = 0;
        for (int element : firstHalf) {
            wholeNumbers[i] = element;
            i++;
        }
        for (int element : secondHalf) {
            wholeNumbers[i] = element;
            i++;
        }
        System.out.println(Arrays.toString(wholeNumbers));
        //System.out.print(wholeNumbers[i] + " "); 
        
    }
    
}
