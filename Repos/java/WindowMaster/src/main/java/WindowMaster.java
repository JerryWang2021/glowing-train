
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jerry
 */

public class WindowMaster {
    
public static void main(String [] args) {
    // declare variables for height,width and cost for glass and trim
    float height;
    float width;
    float price1GlassPerSquareFoot;
    float price2TrimPerLinearFoot;
        
    // delcare String variable to hold the user's height,width and cost for glass and trim input
    String stringHeight;
    String stringWidth;
    String stringPrice1GlassPerSqureFoot = null;
    String stringPrice2TrimPerLinearFoot = null;
        
    // declare other variables
    float areaOfWindow;
    float cost;
    float perimeterOfWindow;
    
    
    // declare and initialize the scanner
    Scanner myScanner = new Scanner(System.in);
    
    // det input from the user
    System.out.println("please eter window height:");
    stringHeight = myScanner.nextLine();
    System.out.println("Please enter window width:");
    stringWidth = myScanner.nextLine();
    System.out.println("please eter price1GlassPerSquareFoot:");
    stringHeight = myScanner.nextLine();
    System.out.println("Please enter price2TrimPerLinearFoot:");
    stringWidth = myScanner.nextLine();
    
    // convert String values of height and width to float values
    height = Float.parseFloat(stringHeight);
    width = Float.parseFloat(stringWidth);
    price1GlassPerSquareFoot = Float.parseFloat(stringPrice1GlassPerSqureFoot);
    price2TrimPerLinearFoot = Float.parseFloat(stringPrice2TrimPerLinearFoot);
    
    
    // calculate the area of the window
    areaOfWindow = height * width;
    
    // calculate the perimeter of the window
    perimeterOfWindow = 2 * (height + width);
    
    // calculate the total cost - use a hard-coded value for material cost
    cost = ((price1GlassPerSquareFoot * areaOfWindow) + (price2TrimPerLinearFoot * perimeterOfWindow));
    
    // display the results to the user
    System.out.println("Window height = " + stringHeight);
    System.out.println("Window width = " + stringWidth);
    System.out.println("Window area = " + areaOfWindow);
    System.out.println("Window perimeter = " + perimeterOfWindow);
    System.out.println("Total Cost = " + cost);
            
}
}