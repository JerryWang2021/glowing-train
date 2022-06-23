/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.arrays;

/**
 *
 * @author Jerry
 */
public class StillPositive {
    public static void main(String[] args) {
        
        System.out.println("Gotta stay positive ...!");
        
        int [] myNums = { 389, -447, 26, -485, 712, -884, 94, -64, 868, -776, 227,
            -744, 422, -109, 259, -500, 278, -219, 799, -311};
        for (int i = 0; i < myNums.length; i++) {
            if (myNums[i] > 0) {
                
                System.out.print(myNums[i] + " ");
            }
            
        }
    }
    
}
