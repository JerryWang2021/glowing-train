/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.practiceprogramming;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Jerry
 */
public class LuckySeven {
    
    public static void main(String[] args) {
        
        Scanner myScanner = new Scanner(System.in);
        Random diceRoller = new Random();
        int count,
            betMoney,
            die1,die2,
            maxBetMoney,
            countAtMax;
        
        //Request the input
        System.out.println("How many dollars do you have?");
        betMoney = myScanner.nextInt();
        
        //Initialize variables
        count = 0;
        countAtMax = 0;
        maxBetMoney = betMoney;
        
        // Loop until the money is gone
        while (betMoney > 0) {
            count++;
            
            // Roll the dice
            die1 = diceRoller.nextInt(6) + 1;
            die2 = diceRoller.nextInt(6) + 1;
            
            if (die1 + die2 == 7)
                betMoney += 4;
            else 
                betMoney -= 1;
            
            // find new maximum and remember it
            if (betMoney > maxBetMoney) {
                maxBetMoney = betMoney;
                countAtMax = count;
            }
        } 
        System.out.println
            ("You are broke after " + count + " rolls.\n" + "You should have "
                    + "quite after " + countAtMax + "rolls when you have $" +
                    maxBetMoney + ".");
    }
    
}
