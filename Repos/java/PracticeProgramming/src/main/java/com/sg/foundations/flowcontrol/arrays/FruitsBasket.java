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
public class FruitsBasket {
    
    public static void main(String[] args) {
        String[] fruitBasket = {"Orange", "Apple", "Orange", "Apple", "Orange", "Apple",
            "Orange", "Apple", "Orange", "Orange", "Orange", "Apple", "Orange", "Orange",
            "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Apple", "Apple",
            "Orange", "Orange", "Apple", "Apple", "Apple", "Banana", "Apple", "Orange",
            "Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Orange", "Apple",
            "Apple", "Apple", "Apple", "Orange", "Orange", "PawPaw", "Apple", "Orange",
            "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange",
            "Apple", "Orange", "Apple", "Kiwi", "Orange", "Apple", "Orange",
            "Dragonfruit", "Apple", "Orange", "Orange"};

        int numOranges = 0;
        int numApples = 0;
        int numOtherFruit = 0;
        String [] oranges = null;
        String [] apples = null;
        int fruitInBasket = numOranges + numApples + numOtherFruit;
        for ( String fruitBasket1 : fruitBasket) {
            if (fruitBasket1.equalsIgnoreCase("Apple") && apples[numApples]== null){
                apples[numApples] = "Apple";
                numApples++;
            }
            if (fruitBasket1.equalsIgnoreCase("Orange") && oranges[numOranges]==null) {
                oranges[numOranges] = "Orange";
                numOranges++;
            }else {
                numOtherFruit++;
            }
            
            
        }System.out.println("Total# of Fruit in Basket: " + fruitInBasket);
        System.out.println("Num of Apples: " + numApples);
        System.out.println("Num of Oranges: " + numOranges);
        System.out.println("Number of other Fruit: " + numOtherFruit);
    }
    
}
