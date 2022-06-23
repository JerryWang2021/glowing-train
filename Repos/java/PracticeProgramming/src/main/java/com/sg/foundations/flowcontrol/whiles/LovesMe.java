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
public class LovesMe {
    // did not use while loop, for loop is better when dealing with opposite results
    public static void main(String[] args) {
        int i = 0;
        for (i = 2; i <= 35; i++) {
            if ( i%2 == 0) {
                System.out.println("It loves me NOT!");
            } else {
                System.out.println("It loves me!");
            }
        }System.out.println("I know it! It LOVES ME!");
    }
    
}
