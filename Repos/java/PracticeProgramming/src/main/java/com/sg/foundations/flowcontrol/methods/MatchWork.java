/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.methods;

/**
 *
 * @author Jerry
 */
public class MatchWork {
    
    public static void main(String[] args) {
        
        double pi = pi();
        int x = 1337;
        double y = 42;
        String word = "Llama";
        String a = "cart";
        String b = "Horse";
        
        System.out.println(" The word Cart should come before Horse aLphabetically : " + comesBefore(a , b));
        System.out.println(" Half of 42 = " + halfOf(y));
        System.out.println(" (short) Pi = " + pi());                                                                           
        System.out.println(" The first letter of the word Llama is: " + firstLetter(word));
        System.out.println(" 1337 x 1337 = " + times1337(x));
    }
    
    public static double pi() {
        return 3.14;
    }
    
    public static int times1337(int x) {
        return x * 1337;
    }
    
    public static double halfOf(double y){
        return y / 2;
    }
    
    public static String firstLetter(String word) {
        return word.substring(0,1);
    }
    
    public static boolean comesBefore(String a, String b) {
        return a.compareToIgnoreCase(b) < 0;
    }
    
   
    
}
