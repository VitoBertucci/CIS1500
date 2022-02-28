package Bertucci_Project1.src;

//Vito Bertucci CIS1500 Java Programming

import java.util.Scanner;
import java.lang.Math;
public class Bertucci_Project1 {

/*
-ask for name 
-ask for post distance
-ask for length and width of fences
-ask for length of board
-calculate how many boards are needed to make fence 
-calculate how much board was wasted
*/

public static void main(String[] args) {

//just declaration of variables
    String name; 
    double postDistance;
    double fenceLength,fenceWidth;
    double boardLength,boardWasted ;
    double perimeter; 
        
    Scanner keyboard = new Scanner (System.in);

//ask for name and store in var "name"
    System.out.println("Hello there! What is your name?");
        name = keyboard.nextLine();

//ask for post distance store in var "postDistance"
    System.out.println("Hi there " + name + "! How far apart are your fence posts?");
        postDistance = Integer.parseInt(keyboard.nextLine());
    
//get fence length and width from user and store in respective vars
    System.out.println("Okay " + name + " ,what is the length and width of your fenced area?");
    System.out.println("Length:");
        fenceLength = Integer.parseInt(keyboard.nextLine());
    System.out.println("Width");
        fenceWidth = Integer.parseInt(keyboard.nextLine());
//calculate perimeter and store in varible for total distance of board length needed
    perimeter = (fenceLength * 2) + (fenceWidth * 2); 

//display the perimeter and get length of board from user
    System.out.println("So you are trying to make a fence that is " + perimeter + " feet around.");
    System.out.println("How long are your fence boards going to be, " + name + "?");
        boardLength = Integer.parseInt(keyboard.nextLine());

//calculating how many boards to surround area        
    double boardsForPerimeterExact; 
        boardsForPerimeterExact = perimeter / boardLength;
    double boardsForPerimeterCeil;
        boardsForPerimeterCeil = Math.ceil(perimeter / boardLength);

//calculate how many post each board will cover
    double postPerBoardExact;
        postPerBoardExact = boardLength / postDistance;
    double postPerBoardFloor; 
        postPerBoardFloor = Math.floor(boardLength / postDistance);


//an icky ifelse conditional because I couldn't get the if conditional to work correctly
    if (postPerBoardExact < 1 ) {
        //if the board length does not cover at least one post distance, display this
        System.out.println("Oh no! It looks like you cant even cover one post with that board length!");
        System.out.println("Go ahead and come back when you have a board length at least as long as the post distance, " + name + ".");
    } else if (postPerBoardExact == postPerBoardFloor) {
        //if the board is as long as the post distance, display this
        System.out.println("One board can cover " + postPerBoardExact);
        System.out.println("It would take " + boardsForPerimeterExact + " boards to surround the area");
    } else if (postPerBoardExact > postPerBoardFloor) {
        //if the board covers more than one post distance, calculate the amounted wasted and display it
        boardWasted = (postPerBoardExact - postPerBoardFloor) * boardsForPerimeterCeil;
        System.out.println("One board can cover " + postPerBoardExact + " posts");
        System.out.println("It would take " + boardsForPerimeterCeil + " boards to surround the area");
        System.out.println("Also, you would waste " + boardWasted + " feet of board");
    }

    
    }
}
