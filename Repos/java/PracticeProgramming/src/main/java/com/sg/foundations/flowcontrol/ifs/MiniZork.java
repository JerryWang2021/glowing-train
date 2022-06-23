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
public class MiniZork {
   
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        System.out.println("You are standing in an open field west of a white house,");
        System.out.println("With a boarded front door.");
        System.out.println("There is a small mailbox here.");
        System.out.print("Go to the house, or open the mailbox? ");

        String action = userInput.nextLine();

        if (action.equals("open the mailbox")) {
            System.out.println("You open the mailbox.");
            System.out.println("It's really dark in there.");
            System.out.print("Look inside or stick your hand in? ");
            action = userInput.nextLine();

            if (action.equals("look inside")) {
                System.out.println("You peer inside the mailbox.");
                System.out.println("It's really very dark. So ... so very dark.");
                System.out.print("Run away or keep looking? ");
                action = userInput.nextLine();

                if (action.equals("keep looking")) {
                    System.out.println("Turns out, hanging out around dark places isn't a good idea.");
                    System.out.println("You've been eaten by a grue.");
                } else if (action.equals("run away")) {
                    System.out.println("You run away screaming across the fields - looking very foolish.");
                    System.out.println("But you alive. Possibly a wise choice.");
                } else {
                    System.out.println("You stop playing! Too scary!");
                }
            } else if (action.equals("stick your hand in")) {
                System.out.println("You stick your hand inside the mialbox.");
                System.out.println("There are something soft and warm!");
                System.out.println("It's scary!!!");
            } else{
                System.out.println("You fell uncomfortable and stop play!");
            }
        } else if (action.equals("go to the house")) {
            System.out.println("You go to the house.");
            System.out.println("It is dark inside");
            System.out.println("You open the door, go inside without a flash in your hand.");
        }else{
            System.out.println("You didn't make chioce. You stop playing!");
        }
    }
}
