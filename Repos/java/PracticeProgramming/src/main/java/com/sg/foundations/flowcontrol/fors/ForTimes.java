/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.fors;

import java.util.Scanner;

/**
 *
 * @author Jerry
 */
public class ForTimes {
    
    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        System.out.print("Which times table shall I recite?");
        int times = inputReader.nextInt();
        int i = 1;
        String s=String.valueOf(i);
        String r=String.valueOf(times);
        for (i = 1; i < 16; i++) {
            
            System.out.println(s * r + " is:" );
            int a = inputReader.nextInt();
            if (i * times == a) {
                System.out.println("Sorry no, this answer is:" + i * times);
            } else {
                System.out.println("Correct!");
            }
        }

    }
    
}
