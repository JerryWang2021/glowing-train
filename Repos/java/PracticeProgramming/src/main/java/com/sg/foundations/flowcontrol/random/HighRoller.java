/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.random;

import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author Jerry
 */
public class HighRoller {
    
    public static void main(String[] args) {
        Scanner myDie = new Scanner(System.in);
        System.out.println("how many sides do you want your die have?");
        
        int sidesofdie = myDie.nextInt();
        System.out.println("You choiced a " + sidesofdie + " sides of die.");
        Random diceRoller = new Random();

        int rollResult = diceRoller.nextInt(sidesofdie) + 1;

        System.out.println("TIME TO ROOOOOOLL THE DICE!");
        System.out.println("I rolled a " + rollResult);

        if (rollResult == 1) {
            System.out.println("You rolled a critical failure!");
        }
        if (rollResult == sidesofdie) {
            System.out.println("You rolled a critical!");
        }
    }
    
}
