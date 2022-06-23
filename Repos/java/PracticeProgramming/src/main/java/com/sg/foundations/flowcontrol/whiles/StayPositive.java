/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.whiles;

/**
 *
 * @author Jerry
 */
public class StayPositive {
    
    public static void main(String[] args) {
        
            int counter = 58;
            // counter >=0 not work
            while ( counter >= 0){
                for (int i = 0; i < 10 ; i++) {
                
                    for (int j = 0; j < 10 ; j++) {
                        System.out.print(counter);
                        System.out.print(" ");
                        counter--;
                    }
            
                    System.out.println();
                }   
            
            }
        
    }        
}   
