/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.methods;

import java.util.Random;

/**
 *
 * @author Jerry
 */
public class BarelyControlledChaos {
    
    public static void main(String[] args) {
        
   
        String color = color();
        String animal = animal();
        String colorAgain = color();
        int weight = randomNumber(5,200);
        int distance = randomNumber(10,20);
        int number = randomNumber(10000,20000);
        int time = randomNumber(2,6);

        System.out.println("Once, when I was very small...");

        System.out.println("I was chased by a " + color + ", "
            + weight + "lb " + " miniature " + animal
            + " for over " + distance + " miles!!");

        System.out.println("I had to hide in a field of over "
            + number + " " + colorAgain + " poppies for nearly "
            + time + " hours until it left me alone!");

        System.out.println("\nIt was QUITE the experience, "
            + "let me tell you!");
    }
    public static String color(){
        
        Random rng = new Random();
        
        String[] colors = new String[] { "Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet"};
        int randomIndex = rng.nextInt(colors.length);
        String color = colors[randomIndex];
        
        return color;
    }
    public static String animal() {
        
        Random rng = new Random();
        
        String[] animals = new String[] { "Cat", "Dog", "Leopard", "Cheetah", "Gorilla", "Polar bear", "wolf"};
        int randomIndex = rng.nextInt(animals.length);
        String animal = animals[randomIndex];
        
        return animal;
    }
    
    public static int randomNumber(int min, int max){
        
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt((max-min) + 1) + min;
        
        return randomNumber;
    }
    
   
}
    // ??? Method 1 ???
    // ??? Method 2 ???
    // ??? Method 3 ???

  
