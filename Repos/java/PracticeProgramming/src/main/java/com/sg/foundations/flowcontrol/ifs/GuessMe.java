/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.ifs;

import java.util.Scanner;

/**
 *
 * @author Jerry
 */
public class GuessMe {
    
    public static void main(String[] args){
        int mynb = 28;
        int yournb =28;
        String yourNum;
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("I've chosen a number. it's between 18 to 38. You will not guess it!");
        System.out.println("Tell us your guess?");
        yourNum = inputReader.nextLine();
        
        yournb = Integer.parseInt(yourNum);
        
        if (yournb != mynb) { 
            System.out.println("Wrong! Try again!");
        } else {
            System.out.println("WoW! can't believe it!");
            
        }
    }
    
}
