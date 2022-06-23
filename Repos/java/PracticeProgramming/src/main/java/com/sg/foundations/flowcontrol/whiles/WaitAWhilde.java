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
public class WaitAWhilde {
    
    public static void main(String[] args) {
        
        int timeNow = 5; // timeNow=11, loop will be skipped and print out I will go to bed;
        int bedTime = 10;
        
        while (timeNow < bedTime) {
            System.out.println("It's only " + timeNow + " o'clock!");
            System.out.println("I think I 'll stay up just a liiiiiittle longer....");
            timeNow++; // if we comment out timeNow++, loop will execute for ever!!!
        }
        
        System.out.println("Oh. It's " + timeNow + "o'clock.");
        System.out.println("Guess I should go to bed ...");
    }
    
}
