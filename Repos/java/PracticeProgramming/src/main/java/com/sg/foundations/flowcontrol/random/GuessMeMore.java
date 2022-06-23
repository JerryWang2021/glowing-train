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
public class GuessMeMore {
    
    public static void main(String[] args){
        int mynb =88;
        int yournb =28;
        Random mynumber = new Random();
        mynb = mynumber.nextInt(200)+1-(100);
        //random number between -100 and 100;        
        System.out.println("I've chosen a number between -100 and 100. Bet you can't guess it!");
        
        Scanner inputReader = new Scanner(System.in);
       
        System.out.println("Tell us your guess?");
        yournb = inputReader.nextInt();
        System.out.println("Your guess: " + yournb);
        System.out.println("My number is " + mynb);
              
        if (yournb != mynb) { 
            System.out.println("Wrong! Try again!");
        } else {
            System.out.println("WoW! Nice guess! That was it!!");
            
        }
    }
}