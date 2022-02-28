package bertucci_finalproject;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
//import bertucci_finalproject.Player;
import bertucci_finalproject.Monster;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class FinalProjectController implements Initializable {

    @FXML
    private Label pDexLabel;
    @FXML
    private Label pIntLabel;
    @FXML
    private Label pStrLabel;
    @FXML
    private Label pNameLabel;
    @FXML
    private Label pHealthLabel;
    @FXML
    private Button fightButton;
    @FXML
    private Button runButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button sleepButton;
    @FXML
    private Label monsterName;
    @FXML
    private Label mDexLabel;
    @FXML
    private Label mIntLabel;
    @FXML
    private Label mStrLabel;
    @FXML
    private Label mHealthLabel;
    @FXML
    private VBox mBox;
    @FXML
    private Label totalGold;
    @FXML
    private Label roomsCleared;
    private int numRoomClear;
    @FXML
    private Label totalSteps;
    private int totalStepsTaken;
    @FXML
    private Label totalMonstersKilled;
    private int monstersKilled;
    @FXML
    private Button upButton;
    @FXML
    private Button leftButton;
    @FXML
    private Button downButton;
    @FXML
    private Button rightButton;
    @FXML
    private Button rollButton;
    @FXML
    private Label rollValLabel;
    @FXML
    private Label damageValLabel;
    @FXML
    private Label monRollValLabel;
    @FXML
    private Label monDamageValLabel;
    @FXML
    private HBox pDamageValBox;
    @FXML
    private GridPane monRollValGrid;
    @FXML
    private TextArea promptArea;
    @FXML
    private Rectangle topWall;
    @FXML
    private Rectangle leftWall;
    @FXML
    private Rectangle bottomWall;
    @FXML
    private Rectangle rightWall;
    @FXML
    private Polygon playerSprite;
    @FXML
    private Rectangle combatBackground;
    @FXML
    private Rectangle travelBackground;
    private Boolean inFight;
    private Boolean room;
    private Boolean monsterInRoom;
    private Boolean search;
    private Boolean alive;
    @FXML
    private Circle enemySprite;
    @FXML
    private ToggleButton adminToggle;
    @FXML
    private VBox adminBox;
    @FXML
    private Button adminKillPlayer;
    @FXML
    private CheckBox adminSleepMon;
    private Boolean sleepMon;
    @FXML
    private CheckBox adminRunMon;
    private Boolean runMon;
    
    private int monsterHealth;
    private int monsterStrength;
    private int monsterDexterity;
    private int monsterIntelligence;
    
    private String playerName;
    private int playerHealth;
    private int playerTotalGold; 
    private int playerStrength;
    private int playerDexterity; 
    private int playerIntelligence; 
    @FXML
    private Button resetButton;
    
    Dice dice = new Dice();
//______________________________________________________________________________
    //make new player and monster methods
    public void makePlayer() {
        //make a new player and assign variables to player stats
        Player user = new Player("Johnathan");
        playerName = user.getPlayerName();
        playerHealth = user.getPlayerHealth();
        playerTotalGold = user.getPlayerGold();
        playerStrength = user.getPlayerStrength();
        playerDexterity = user.getPlayerDexterity();
        playerIntelligence = user.getPlayerIntelligence();
        
        //set player stats labels to player stat varible values
        pHealthLabel.setText(String.valueOf(playerHealth));
        pNameLabel.setText(playerName);
        pStrLabel.setText(String.valueOf(playerStrength));
        pDexLabel.setText(String.valueOf(playerDexterity));
        pIntLabel.setText(String.valueOf(playerIntelligence));
        totalGold.setText(String.valueOf(playerTotalGold));
        numRoomClear = 0;
        roomsCleared.setText("0");
        totalStepsTaken = 0;
        totalSteps.setText("0");
        monstersKilled = 0;
        totalMonstersKilled.setText("0");
    }
    
    public void makeMonster() {
        Monster monster = new Monster();
        monsterHealth = monster.getMonsterHealth();
        monsterStrength = monster.getMonsterStrength();
        monsterDexterity = monster.getMonsterDexterity();
        monsterIntelligence = monster.getMonsterIntelligence();
        
        //set monster stats labels to value of attribute vars
        mHealthLabel.setText(String.valueOf(monsterHealth));
        mStrLabel.setText(String.valueOf(monsterStrength));
        mDexLabel.setText(String.valueOf(monsterDexterity));
        mIntLabel.setText(String.valueOf(monsterIntelligence));
   }
//______________________________________________________________________________
    //method to make a new wall in a random direction
    public void makeWall() {
        int wallChance = new Random().nextInt(4);
        switch (wallChance) {
            case 0: topWall.setVisible(true);
                    break;
            case 1: rightWall.setVisible(true);
                    break;
            case 2: bottomWall.setVisible(true);
                    break;
            case 3: leftWall.setVisible(true);
                    break;
        }
        checkRoom();
    }
//______________________________________________________________________________
    //check if all 4 walls are up
    public Boolean checkRoom() {
        if (topWall.isVisible() && bottomWall.isVisible()) {
            if (rightWall.isVisible() && leftWall.isVisible()) {
                room = true;
                travelBackground.setVisible(false);
                combatBackground.setVisible(true);
                playerSprite.setRotate(0);
                roomSetup();
            }
        } else {
            room = false;
        }
        return room;
    }        
//______________________________________________________________________________
   //methods for creating a room and wiping a room
   public void roomSetup() {
       int u = new Random().nextInt(3);
        //chose random number 0-2, 33% chance of u being 0
        //if u is 0, spawn monster
        if (u == 0) {
            makeMonster();
            mBox.setVisible(true);
            enemySprite.setVisible(true);
            monsterInRoom = true;
            promptArea.appendText("A monster has appeared! \n"
                    + "You can either fight it or try to run away.\n");
          //if u not 0, spawn empty room 
        } else {
            promptArea.clear();
            monsterInRoom = false;
            promptArea.appendText("The room seems empty. \n"
                    + "Maybe there is gold hidden around the room. \n"
                    + "Or you can sleep now to regain your lost health. \n"
                    + "But be careful, theres always monsters nearby... \n");
        }
    }    
//______________________________________________________________________________
    //methods for room resetting and checking
    public void resetRoom() {
        promptArea.appendText("\nYou may continue onward.");
       
        //reset player stuff
        totalGold.setText(String.valueOf(playerTotalGold));
        pHealthLabel.setText(String.valueOf(playerHealth));
        pDamageValBox.setVisible(false);
       
        //reset walls
        topWall.setVisible(false);
        leftWall.setVisible(false);
        bottomWall.setVisible(false);
        rightWall.setVisible(false);
       
        //reset monster stuff
        monRollValGrid.setVisible(false);
        travelBackground.setVisible(true);
        combatBackground.setVisible(false);
        enemySprite.setVisible(false);
        monsterInRoom = false;
        inFight = false;
        room = false;
        search = false;
        numRoomClear += 1;
        roomsCleared.setText(String.valueOf(numRoomClear));
    }
    
    //reset entire game for play agin feature
    public void resetGame() {
        adminBox.setVisible(false);
        runMon = false;
        adminRunMon.setSelected(false);
        sleepMon = false;
        adminSleepMon.setSelected(false);
        
        resetButton.setVisible(false);
        alive = true;
        rollValLabel.setText(" ");
        
        makePlayer();
        mBox.setVisible(false);
        resetRoom();
        numRoomClear = 0;
        roomsCleared.setText(String.valueOf(numRoomClear));
        promptArea.clear();
        promptArea.appendText("Hello adventurer! Use the arrow keys to move, \n"
                + "walls will close in on you as you walk around. \n"
                + "Be careful of monsters roaming around, \n"
                + "and make sure you look for gold when possible!");
    }
    
    //method for when playerHealth = 0
    public void checkAlive() {
        if (playerHealth <= 0) {
            resetButton.setVisible(true);
            alive = false;
            promptArea.appendText("\nYou died. Click the reset button to play again.");
        }
    }

//______________________________________________________________________________
   public FinalProjectController () {
        room = false;
        inFight = false;
        search = false;
        alive = true;
        runMon = false;
        sleepMon = false;
    } 
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        mBox.setVisible(false);
        resetGame();
    }
    /*
________________________________________________________________________________
    Event Handlers:
________________________________________________________________________________ 
    */

    //arrow key button event handler

    @FXML
    private void moveKeys(ActionEvent event) {
        
        //as long as the player is not in a room and alive, these will execute
        if (room == false && alive) {
            
            //hide the monster box if theres is one, clear promptArea and rollVal
            mBox.setVisible(false);
            promptArea.clear();
            rollValLabel.setText(" ");
            
            //up arrow pressed
            if (event.getSource() == upButton ) {
                playerSprite.setRotate(0);
                if (topWall.isVisible() == false) {
                    makeWall();
                    totalStepsTaken++;
                    totalSteps.setText(String.valueOf(totalStepsTaken));
                }
            }

            //down arrow pressed
            if (event.getSource() == downButton ) {
                playerSprite.setRotate(180);
                if (bottomWall.isVisible() == false) {
                    makeWall();
                    totalStepsTaken++;
                    totalSteps.setText(String.valueOf(totalStepsTaken));
                }
            }

            //left arrow pressed
            if (event.getSource() == leftButton ) {
                playerSprite.setRotate(270);
                if (leftWall.isVisible() == false) {
                    makeWall();
                    totalStepsTaken++;
                    totalSteps.setText(String.valueOf(totalStepsTaken));
                }
            }

            //right arrow pressed
            if (event.getSource() == rightButton ) {
                playerSprite.setRotate(90);
                if (rightWall.isVisible() == false) {
                    makeWall();
                    totalStepsTaken++;
                    totalSteps.setText(String.valueOf(totalStepsTaken));
                }
            }
        }
    }
//______________________________________________________________________________
    
    @FXML
    private void abilityBar(ActionEvent event) {
        
        
        //if monster in room button actions
        if (alive && room && monsterInRoom) {
            
            //if the user has not chosen to fight the monster yet, these are allowed
            if (inFight == false) {
                //fight button
                if (event.getSource() == fightButton ) {
                    
                    //set up env for monster fight and set inFight to true
                    promptArea.clear();
                    monRollValGrid.setVisible(true);
                    pDamageValBox.setVisible(true);
                    rollValLabel.setText(" ");
                    damageValLabel.setText(" ");
                    monRollValLabel.setText(" ");
                    monDamageValLabel.setText(" ");
                    promptArea.appendText("You have chosen to fight the monster! \n"
                            + "Roll the die to attack the monster and try to stay alive! \n");
                    inFight = true;
                }

                //run button
                if (event.getSource() == runButton ) {
                    promptArea.clear();
                    promptArea.appendText("You have chosen to try to run from the fight \n");
                    
                    int monsterRunRoll = dice.roll();
                    if (runMon == true) {
                        monsterRunRoll = (monsterIntelligence - 1);                    }
                    
                    //if the monster saw the player it gets to attack
                    if (monsterRunRoll < monsterIntelligence) {
                        int monsterRunAttack = dice.rollDamage(monsterRunRoll,playerDexterity,monsterStrength);
                        if (runMon == true) {
                            monsterRunAttack = (playerHealth / 3);
                        }
                        
                        //if the monster hits, update health info and display
                        if (monsterRunAttack > 0) {
                            playerHealth -= monsterRunAttack;
                            pHealthLabel.setText(String.valueOf(playerHealth));
                            promptArea.appendText("The monster attacked you as you ran away! \n"
                                    + "It dealt " + monsterRunAttack + " damage as you fled!");
                            
                          //if monster misses, display 0 damage
                        } else {
                            promptArea.appendText("The monster saw you run and tried to attack you. \n"
                                    + "It missed and dealth 0 damage. \n");
                        }
                        
                      //if monster does not see player, display  
                    } else {
                        promptArea.appendText("You ran away safely.");
                        
                    }
                    checkAlive();
                    resetRoom();
                }

                //search button
                if (event.getSource() == searchButton ) {
                    promptArea.clear();
                    promptArea.appendText("You can't search the room! Theres a monster here! \n");
                }

                //sleep button
                if (event.getSource() == sleepButton ) {
                    promptArea.clear();
                    promptArea.appendText("You can't sleep now! Theres a monster in the room! \n");
                }         
            }
            
            //if the user has chosen to fight the monster, only allow roll button
            if (inFight) {
                
                //run button when in combat
                if (event.getSource() == runButton ) {
                    promptArea.clear();
                    promptArea.appendText("You have chosen to try to run from the fight \n");
                    int monsterRunRoll = dice.roll();
                    if (runMon == true) {
                        monsterRunRoll = (monsterIntelligence - 1);
;                    }
                    
                    //if the monster saw the player it gets to attack
                    if (monsterRunRoll < monsterIntelligence) {
                        int monsterRunAttack = dice.rollDamage(monsterRunRoll,playerDexterity,monsterStrength);
                        if (runMon == true) {
                            monsterRunAttack = 5;
                        }
                        
                        //if the monster hits, update health info and display
                        if (monsterRunAttack > 0) {
                            playerHealth -= monsterRunAttack;
                            pHealthLabel.setText(String.valueOf(playerHealth));
                            promptArea.appendText("The monster attacked you as you ran away! \n"
                                    + "It dealt " + monsterRunAttack + " damage as you fled!");
                            
                          //if monster misses, display 0 damage
                        } else {
                            promptArea.appendText("The monster saw you run and tried to attack you. \n"
                                    + "It missed and dealth 0 damage. \n");
                        }
                        
                      //if monster does not see player, display  
                    } else {
                        promptArea.appendText("You ran away safely.");
                        
                    }
                    checkAlive();
                    resetRoom();
                }

                //rollButton when in combat
                if(event.getSource() == rollButton) {
                    promptArea.clear();

                    //player and monster roll d20's
                    int playerDamageRoll = dice.roll();
                    int monsterDamageRoll = dice.roll();
                    
                    //calculate player attack, display roll and damage in labels
                    int playerAttack = dice.rollDamage(playerDamageRoll, monsterDexterity, playerStrength);
                    damageValLabel.setText(String.valueOf(playerAttack));
                    rollValLabel.setText(String.valueOf(playerDamageRoll));

                    //if player hits, display prompt and update monster health
                    if (playerAttack > 0) {
                        promptArea.appendText("You rolled a " + playerDamageRoll + 
                            " and hit the monster for " + playerAttack + " health! \n");
                    monsterHealth -= playerAttack;
                    mHealthLabel.setText(String.valueOf(monsterHealth));

                      //if player does not hit, tell them in prompt
                    } else {
                        promptArea.appendText("You rolled a " + playerDamageRoll +
                                " and missed the monster doing 0 damage! \n");
                    }

                    //if monster is still alive after player attack
                    if(monsterHealth > 0) {

                        //display how much health the monster has left
                        promptArea.appendText("The monster now has " + monsterHealth 
                         + " health remaining. \n");

                        //get damage from monster using roll
                        int monsterAttack = dice.rollDamage(monsterDamageRoll, playerDexterity, monsterStrength);
                        
                        //if monster hits (attack damage > 0)
                        if (monsterAttack > 0) {
                            monRollValLabel.setText(String.valueOf(monsterDamageRoll));
                            monDamageValLabel.setText(String.valueOf(monsterAttack));
                            playerHealth -= monsterAttack;
                            pHealthLabel.setText(String.valueOf(playerHealth));
                            promptArea.appendText("The monster rolled a " + monsterDamageRoll 
                                + " and hit you for " + monsterAttack + " health! \n");
                            checkAlive();
                        }
                        
                        //if monster misses (attack damage = 0)
                        if (monsterAttack == 0) {
                            monRollValLabel.setText(String.valueOf(monsterDamageRoll));
                            monDamageValLabel.setText(String.valueOf(monsterAttack));
                            promptArea.appendText("The monster rolled a " + monsterDamageRoll 
                                + " and missed you dealing 0 damage!");
                        }

                      //if monster is out of health display in prompt area and reset room
                    } else {
                        promptArea.appendText("You killed the monster! \n");
                        mHealthLabel.setText("dead");
                        monstersKilled += 1;
                        totalMonstersKilled.setText(String.valueOf(monstersKilled));
                        resetRoom();
                    }
                }
            }
        }
        
        //if no monster in room button actions
        if (alive && room && monsterInRoom == false) {
            
            if (search == false) {
                //fight button
                if (event.getSource() == fightButton) {
                    promptArea.clear();
                    promptArea.appendText("Theres nothing to fight here. \n");
                }

                //run button
                if (event.getSource() == runButton) {
                    promptArea.clear();
                    promptArea.appendText("Theres nothing to run from, relax. \n");
                }

                //search button
                if (event.getSource() == searchButton) {
                    promptArea.clear();
                    promptArea.appendText("Roll the die. \n If the roll is less than your "
                            + "intelligence, you may find some gold nearby...");
                    search = true;
                }

                //sleep button
                if (event.getSource() == sleepButton) {
                    promptArea.clear();
                    promptArea.appendText("You try to sleep to regain some strength\n");
                    
                    //chose random number from 1-6, if 3 then monster attacks
                    int sleepAttackChance = 1 + new Random().nextInt(7);
                    if (sleepMon == true) {
                        sleepAttackChance = 3;
                    }
                    int noSleep = 3;

                    if (sleepAttackChance == noSleep) {
                        makeMonster();
                        mBox.setVisible(false);
                        int sleepAttackRoll = dice.roll();
                        int sleepAttackDamage = dice.rollDamage(sleepAttackRoll, playerDexterity, monsterStrength);
                        if (sleepMon == true) {
                            sleepAttackDamage = 5;
                        }
                        
                        if (sleepAttackDamage > 0) {
                            promptArea.appendText("A monster attacked you in your sleep! \n"
                                + "You gained no stregnth and lost " + sleepAttackDamage +
                                " health. \n");
                            playerHealth -= sleepAttackDamage;
                            resetRoom();
                            checkAlive();
                        } else {
                            promptArea.appendText("You wake up feeling well-rested. \n"
                                + "You are back at full health. \n");
                            playerHealth = 20;
                            resetRoom();
                        }

                    } else {
                        promptArea.clear();
                        promptArea.appendText("You wake up feeling well-rested. \n"
                                + "You are back at full health. \n");
                        playerHealth = 20;
                        resetRoom();
                    }

                }
            }
            
            //if search is true, only the roll button can be pressed
            if (event.getSource() == rollButton && search) {
                promptArea.clear();
                int searchRoll = dice.roll();
                rollValLabel.setText(String.valueOf(searchRoll));
                
                    if (searchRoll < playerIntelligence) {
                        int goldFound = new Random().nextInt(1000);
                        playerTotalGold += goldFound;
                        promptArea.appendText("You found " + 
                                String.valueOf(goldFound) + " gold in your search! \n");
                        resetRoom();
                    } else {
                        promptArea.appendText("You didn't find any gold in your search \n");
                        resetRoom();
                    }
            }
        }
    }
//______________________________________________________________________________
    //reset button controller
    @FXML
    private void resetGameButton(ActionEvent event) {
        if(alive == false) {
            resetGame();
        }
    }
//______________________________________________________________________________
    //admin buttons controller
    @FXML
    private void adminAction(ActionEvent event) {
        
        //toggle admin box 
        if (adminToggle.isSelected() == true) {
            adminBox.setVisible(true);
        } else {
            adminBox.setVisible(false);
        }
        
        //kill player button
        if (event.getSource() == adminKillPlayer) {
            playerHealth = 0;
            pHealthLabel.setText(String.valueOf(playerHealth));
            checkAlive();
        }
        
        //always run monster attack
        if (adminRunMon.isSelected() == true) {
            runMon = true;
        } else {
            runMon = false;
        }
        
        //always sleep monster attack
        if (adminSleepMon.isSelected() == true) {
            sleepMon = true;
        } else {
            sleepMon = false;
        }
    }
}
