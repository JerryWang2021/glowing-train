/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RockPaperScissors;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Jerry
 */
public class RockPaperScissors {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
    do {
        //initialize the Scanner and print a welcome message and ask user to key in rounds
        Scanner roundChoice = new Scanner(System.in);
        System.out.println("Welcome to Rock, Paper, Scissors! Let's play! ");
        System.out.println("How many rounds do you want to play? Please choice your number from 1 to 10.");
        int nRound = roundChoice.nextInt();
        
        //if user enter numbers out of range, error message and exit;
        if ( nRound<1 || nRound > 10){ 
            System.out.println("Your number not a valid number!!!");
            System.exit(0);
        }
        //three variables to keep tracking computers win or lose,
            int ties = 0;
            int computerWins = 0;
            int computerLoses = 0;
        // while loops for the rounds user wants to play;
        int i = 1;
        while (i<=nRound){
            // ask user to choice move
            System.out.println("What is your move? To make a move, enter rock, paper,or scissors.");
            Scanner playerChoice = new Scanner(System.in);
            String playerMove = playerChoice.nextLine();
            // use the random class to make computer's moves
            Random randNumbers = new Random();
            int rand = randNumbers.nextInt(3)+1;
            
            String computerMove = "";
            if(rand == 1) {
               computerMove = "rock"; 
            } else if(rand == 2) {
               computerMove = "paper"; 
            } else {
               computerMove = "scissors"; 
            }
            System.out.println("computerMove: " + computerMove);
            
            if(playerMove.equals(computerMove)){
                        ties++;
                        System.out.println("It's a tie!" );
            } else if ( ( playerMove.equals("scissors") && computerMove.equals("paper") )||
                        ( playerMove.equals("rock") && computerMove.equals("scissors")  )||
                        ( playerMove.equals("paper") && computerMove.equals("rock")     )  )
                        {
                        computerLoses++;  
                        System.out.println("Computer lost!");
            } else      {
                        computerWins++;
                        System.out.println("Computer won!");
                        }
            System.out.println("Till now ,computer won " + computerWins + "; lose " + 
                               computerLoses + "; ties " + ties);
            i++;             
            } 
        
        if (computerWins++ > computerLoses++) {
            System.out.println("Comupter is the final winner!");
        } else if (computerWins++ < computerLoses++) {
            System.out.println("You are the final winner!");
        } else {
            System.out.println("It's a tie.");
        }
        System.out.println("Do you want play again?" + " Type YES to continue;type NO to end!");
        if (input.next().equalsIgnoreCase("NO")){ 
            System.out.println("Thanks for playing!!!");
            System.exit(0);}
    }
        while (input.next().equalsIgnoreCase("YES"));
        input.close();
    }
        
}
