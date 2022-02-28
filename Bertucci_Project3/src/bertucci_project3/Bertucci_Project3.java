import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
public class Bertucci_Project3 {
public static void createMode() throws IOException {

//set up scanner and ask for game introduction
    Scanner keyboard = new Scanner (System.in);
    String fileName = "GameIntroduction.txt";
    System.out.println("Enter the game introduction:");
    String gameIntro = keyboard.nextLine();

//write gameIntro into .txt file
    PrintWriter writer = null;
    writer = new PrintWriter(fileName);
    writer.println(gameIntro);
    writer.close();

//loop asking for commands and writing those commands as files
    String command = "blank";
    String endCommand = "EndCommand";
    while (true) {

    //ask for command, get command
        System.out.println("Enter valid game command:");
        System.out.println("Enter 'EndCommand' to finish making commands");
        command = keyboard.nextLine();

    //if user enters 'EndCommand' loop stops 
        if (command.equalsIgnoreCase(endCommand)) {
            break;
        }

    //write file with that commands name
            writer = new PrintWriter(command + ".txt");

    //see if command is WOUNDED status or not
        System.out.println("Is this command going to wound the player?");
        String wounded = keyboard.nextLine();
            switch (wounded) {
                case "yes":
                    writer.println("WOUNDED");
                    break; 
                default:
                    break;
                }

    //ask for setting, print on new line of command file
        System.out.println("What is the setting after this command?");
        String setting = keyboard.nextLine();
        writer.println(setting);

    //ask for the question directed towards the player for their next move 
    //i.e. "what will you do now?"
        System.out.println("What will you ask the player?");
        String question = keyboard.nextLine();
        writer.println(question);
        writer.close();
        }

//ask for escape word
    System.out.println("What is the user's escape word to end the adventure?"); 
    String escapeWord = keyboard.nextLine();

//Create new file escapeWord.txt and write the escape word on the first line
    PrintWriter escapeWriter = new PrintWriter("escapeWord.txt");
    escapeWriter.println(escapeWord);
    escapeWriter.close();
}

public static void playMode() throws FileNotFoundException  {

//find gameIntroduction.txt and use Scanner to read from file
    String introString = "GameIntroduction.txt"; 
    File introFile = new File(introString); 
    Scanner readIntro = new Scanner(introFile);

//display gameIntroduction.txt to player
    String introLine = readIntro.nextLine();
    System.out.println(introLine);

//get escape word from escapeWord.txt
    String escapeString = "escapeWord.txt";
    File escapeFile = new File(escapeString); 
    Scanner readEscape = new Scanner(escapeFile);
    String escapeWord = readEscape.nextLine();

//set up Scanner and set playerInput to some value, ask user for command
    Scanner keyboard = new Scanner(System.in);
    String playerInput = "blank"; 
    System.out.println("What will you do?");

//while loop for player adventure
    while (!playerInput.equalsIgnoreCase(escapeWord)) {

    //ask user for command, set to playerInput, if its escapeWord, break loop
        playerInput = keyboard.nextLine();
        if (playerInput.equalsIgnoreCase(escapeWord)) {
            break;
        }

    //create boolean value for if the command is not valid, set up command file and Scanner
        Boolean commandAgain = true;
        File commandFile = null;
        Scanner readCommand = null;

    //loop for command file validation
        while (commandAgain){
            commandAgain = false;

        //try to find file with that command's name, print the setting if there is
            try {
                commandFile = new File(playerInput + ".txt");
                readCommand = new Scanner(commandFile);

            //set variables to the first, second, and third line of the commandFile
                String firstLine = readCommand.nextLine();
                String secondLine = readCommand.nextLine();
                String thirdLine = "";

            //if the player is not wounded there will be no third line, catch that
                try {
                    thirdLine = readCommand.nextLine();
                } catch (NoSuchElementException exc) {}

            //if statement for WOUNDED logic
                if (firstLine.equalsIgnoreCase("WOUNDED")) {

                //let player know they are wounded and can only sleep
                    System.out.println(firstLine); 
                    System.out.println("Oh no! This action has left you in a WOUNDED state!"); 
                    System.out.println("You must sleep in order to recover!");

                //boolean while loop until player enters 'sleep'
                    Boolean playerSleep = true;
                    while (playerSleep) {
                        String sleepCommand = keyboard.nextLine();
                        playerSleep = false;

                    //test for 'sleep' as command
                        if (sleepCommand.equalsIgnoreCase("sleep")) {

                        //if yes, stop loop, display commandFile
                            System.out.println("You rested and recovered from your WOUNDED state.");
                            System.out.println(secondLine);
                            System.out.println(thirdLine);
                            } else {

                        //if no, keep loop going until player enters 'sleep' as command
                            System.out.println("You cannot do that. You may only sleep to recover.");
                            playerSleep = true;
                            }
                    }
                } else {

                //if command does not wound player, display setting and question of commandFile
                    System.out.println(firstLine);
                    System.out.println(secondLine);
                }
            }

        //if there is no file with valid command, ask for another command
        //loop back to top and look for a new file with that name
            catch (FileNotFoundException exc) {
                commandAgain = true;
                System.out.println("You cannot do that.");
                playerInput = keyboard.nextLine();
                if (playerInput.equalsIgnoreCase(escapeWord)) {
                    break;
                }
            }
        }
    }
}
    public static void main(String[] args) throws IOException {
        createMode();
        playMode();
    }
}
