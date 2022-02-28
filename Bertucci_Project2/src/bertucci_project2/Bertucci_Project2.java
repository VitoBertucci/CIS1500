import java.util.Random;
import java.util.Scanner;
/*
-possible input validation with self destruct code guessing
*/
public class Bertucci_Project2 {
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void play() {
        //clear the terminal on launch
        clearScreen();   
        Scanner keyboard = new Scanner (System.in);
        
        //give user staring info and delay between each line
        System.out.println("Available inputs:");
            try {Thread.sleep(50);}
            catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("---------------------------");
            try {Thread.sleep(50);}
            catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("plusX  || minusX \nplusY  || minusY \nthrust || commands");
            try {Thread.sleep(50);}
            catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("---------------------------");
            try {Thread.sleep(50);}
            catch (InterruptedException e) {e.printStackTrace();}
        
        //declaration of variables
        int moves = 0;
        int distance = 10;
        int xTilt = -10 + new Random().nextInt(21);
        int yTilt = -10 + new Random().nextInt(21);

        //variable for time of self destruct, the code, and the user guess
        int selfDestructTime = new Random().nextInt(11);
        int selfCode;
        int userCode, tries;

            //loop that runs while the rover is not in the air
            while (distance > 0) {
                
                //ask user for input and give rover position detail
                System.out.println("Type 'commands' to view all available commands");
                    try {Thread.sleep(50);}
                    catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("(Case sensitive)");
                    try {Thread.sleep(50);}
                    catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("___________________________");
                    try {Thread.sleep(50);}
                    catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("Distance from ground = " + distance);
                    try {Thread.sleep(50);}
                    catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("X-axis tilt          = " + xTilt);
                    try {Thread.sleep(50);}
                    catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("Y-axis tilt          = " + yTilt);
                    try {Thread.sleep(50);}
                    catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("Please input command:");
                
                //ask user for commands and remove whitespace from it
                String userin = keyboard.nextLine();
                String userinput = userin.replace(" ","");
                System.out.println("__________________________");

                //switch case for testing which command was inputted
                //repeats if default case is reached
                //clears screen at valid command
                boolean again = true;
                while (again) {
                    again = false;
                    switch (userinput) {
                        case "thrust":
                            distance += 3;
                            clearScreen();
                            break;
                        case "plusX":
                            xTilt += 1;
                            clearScreen();
                            break;
                        case "minusX":
                            xTilt-= 1;
                            clearScreen();
                            break;
                        case "plusY":
                            yTilt += 1;
                            clearScreen();
                            break;
                        case "minusY":
                            yTilt -= 1;
                            clearScreen();
                            break;
                        case "commands":
                            //display commands again, get user input as passable command again
                            again = true;
                            System.out.println("plusX  || minusX \nplusY  || minusY \nthrust || descend");
                            System.out.println("Please enter a valid input:");
                            userin = keyboard.nextLine();
                            userinput = userin.replace(" ","");
                            break;
                        case "descend":
                            clearScreen();
                            break;
                        default:
                            //if default case is met, ask user for input, get input as command again
                            again = true; 
                            System.out.println("Please enter a valid input: \n ('commands' for valid commands)");
                            userin = keyboard.nextLine();
                            userinput = userin.replace(" ","");
                            clearScreen();
                            break;
                        }
                    }

                    //self-destruct counts down from a random number up to 10 every valid input
                    //self-destruct is activated when the count runs to 0
                    selfDestructTime -= 1;
                    if (selfDestructTime < 1) {

                        //starting values and creation of formatted variables 
                        selfCode = new Random().nextInt(1000);
                        tries = 3;
                        clearScreen();
                        String destructString = String.format("%03d", selfCode);
                        System.out.println("Oh no! The self-destruct sequence was activated!");
                        System.out.println("Enter the correct code to disable it:");
                        userCode = keyboard.nextInt();

                        /*if the user is not correct on the first guess, this while 
                        loop runs for 3 tries revealing a digit each time*/
                        while (selfCode != userCode) {

                            //each try the user is incorrect they are given one digit of the code
                            /*I am not too proud of this but it works as 
                            intended I'm just sure there's a better way to do it*/
                            switch (tries) {
                                case 3: 
                                    clearScreen();
                                    System.out.println("Incorrect code.");
                                        try {Thread.sleep(50);}
                                        catch (InterruptedException e) {e.printStackTrace();}
                                    System.out.println("Hint: **" + destructString.charAt(2));
                                        try {Thread.sleep(50);}
                                        catch (InterruptedException e) {e.printStackTrace();}
                                    System.out.println(tries + " tries remaining. \nEnter code:");
                                    userCode = keyboard.nextInt();
                                    clearScreen();
                                    break;
                                case 2:
                                    System.out.println("Incorrect code.");
                                        try {Thread.sleep(50);}
                                        catch (InterruptedException e) {e.printStackTrace();}
                                    System.out.println("Hint: *" + destructString.charAt(1) + destructString.charAt(2));
                                        try {Thread.sleep(50);}
                                        catch (InterruptedException e) {e.printStackTrace();}
                                    System.out.println(tries + " tries remaining. \nEnter code:");
                                    userCode = keyboard.nextInt();
                                    clearScreen();
                                    break;
                                case 1:
                                    System.out.println("Incorrect code.");
                                        try {Thread.sleep(50);}
                                        catch (InterruptedException e) {e.printStackTrace();}
                                    System.out.println("Hint: " + destructString);
                                        try {Thread.sleep(50);}
                                        catch (InterruptedException e) {e.printStackTrace();}
                                    System.out.println(tries + " tries remaining. \nEnter code:");
                                    userCode = keyboard.nextInt();
                                    clearScreen();
                                    break;
                                case 0:
                                    /*if the user does not correctly guess the code in 3 tries, 
                                    rover blows up and play() is stopped early*/
                                    System.out.println("Incorrect code.");
                                    System.out.println("The self-destruct sequence was activated and your rover blew up.");
                                    return;
                            }
                        tries -= 1;
                        }
                    //the self destruct countdown is set to an arbitrary number to stop it from happening again
                    selfDestructTime = 20000;
                    }
                //after every valid command, subtract one from distance
                //move counter for total user moves
                distance -= 1;
                moves += 1;
                }

            //if statement for end game condition
            if ((xTilt != 0) || (yTilt != 0)) {
                System.out.println("Oh no! You crashed the rover");
            } else {
                System.out.println("You successfully landed the rover in " + moves + " moves!");
            }
        }
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);        
        boolean playAgain;
        
        //a dowhile statement for play again featured
        //asks user for a y/n answer, playAgain is true if answer is y, false if n
        //game executes once on launch, plays again only if playAgain bool is true, stops if false
        do {
            play();
            System.out.println("Do you wish to play again? Y/N");
            playAgain = input.nextLine().toLowerCase().startsWith("y");
        } while (playAgain);
    }
}

