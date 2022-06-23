/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.arrays;

import java.util.Random;

/**
 *
 * @author Jerry
 */
public class HiddenNuts {
    
    public static void main(String[] args) {
        int luckySpot = 0;
        String[] hidingSpots = new String[100];
        Random squirrel = new Random(100);// look Random not works
        luckySpot = squirrel.nextInt(hidingSpots.length);
        hidingSpots[luckySpot] = "Nut";
        System.out.println("The nut has been hidden ...");
        
        for(String Nut: hidingSpots) {
           System.out.println("Found it! It's in spot# " + luckySpot);
           break;
        }
        
    }
    
    
    
}
