/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HealthyHearts;

import java.util.Scanner;

/**
 *
 * @author Jerry
 */
public class HealthyHearts {
    
    public static void main(String[] args) {
        
       //ask for the users age
        Scanner dataReader = new Scanner(System.in);
        System.out.println("What is your age? ");
        int age = dataReader.nextInt();
       //Calculate the healthy heart range
        int maximumHeartRate = 220 - age;
        int maxHealthyHeartRate = (int)(0.85 * maximumHeartRate);
        int minHealthyHeartRate = (int)(0.50 * maximumHeartRate);
        //display it
        System.out.println("Your maximum heart rate should be " 
                            + maximumHeartRate + " beats per minute");
        System.out.println("Your target HR Zone is " + minHealthyHeartRate +
                " - " + maxHealthyHeartRate + " beats per minute");
    }
}
