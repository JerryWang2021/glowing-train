/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.fors;

/**
 *
 * @author Jerry
 */
public class ForAndTwentyBlackbirds {
    
    public static void main(String[] args) {
        int birdsInPie = 0;
        for (int i = 1; i <= 24; i++) {
            System.out.println("Blackbirds #" + i + " goes into the pie!");
            birdsInPie++;
        } // if we want 1-24, use int i = 1; i <= 24; i++;
        
        System.out.println("There are " + birdsInPie + " birds in there!");
        System.out.println("Quite the pie full!");
    }
    
}
