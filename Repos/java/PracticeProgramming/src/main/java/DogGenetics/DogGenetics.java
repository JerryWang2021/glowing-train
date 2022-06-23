/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DogGenetics;

import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author Jerry
 */
public class DogGenetics {
    public static void main(String[] args) {
        // asks the user their dog name
        Scanner inputReader = new Scanner(System.in);
        String petDogName; //Initialize variables
        System.out.println("What is your dog's name? " );
        petDogName = inputReader.nextLine();
        System.out.println("Well then, I have this highly reliable report on " + petDogName + 
                           " 's prestigious background right here.");
        System.out.println("");
        
        Random numberCreator = new Random();
        
        //Initialize variables
        int count = 0;
        int sum;
        int randomPercentage1,randomPercentage2,randomPercentage3,
            randomPercentage4,randomPercentage5;
        
        //do-while loop to creat 4 reandom numbers(sum of them less than 100)
        do {
            randomPercentage1 = numberCreator.nextInt(100) + 1;
            randomPercentage2 = numberCreator.nextInt(100) + 1;
            randomPercentage3 = numberCreator.nextInt(100) + 1;
            randomPercentage4 = numberCreator.nextInt(100) + 1;
            
            sum = randomPercentage1 + randomPercentage2 + randomPercentage3 + randomPercentage4;
            count++;
        }
        while (sum > 100);
        
        randomPercentage5 = 100 - sum; // 5th number;
        
        //using 5 random percentage number to generates a fake DNA report
        System.out.println(""+ petDogName +  " is");
        System.out.println("");
        System.out.println(randomPercentage1 + "% St. Bernard");
        System.out.println(randomPercentage2 + "% Chihuahua");
        System.out.println(randomPercentage3 + "% Dramatic RedNosed Asian Pug");
        System.out.println(randomPercentage4 + "% Common Cur");
        System.out.println(randomPercentage5 + "% King Doberman");
        System.out.println("");
        System.out.println("Wow, that's QUITE the dog!");
    }
}       
       
       
          
    

