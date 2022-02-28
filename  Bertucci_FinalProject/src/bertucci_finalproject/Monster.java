
package bertucci_finalproject;
import java.util.Random;

public class Monster {
    
    //attributes
    private String name;
    private int health, strength, dexterity, intelligence;
    
    //constructor
    public Monster () {
        name = "monster";
        health = rollStatsDie();
        strength = (health * 2);
        dexterity = (health * 2);
        intelligence = (health * 2);
    }
    
    //methods
    private int rollStatsDie () {
        int roll = 1 + new Random().nextInt(6);
        return roll;
    }
    
    public int getMonsterHealth () {
        return health;
    }
    
    public int getMonsterStrength () {
        return strength;
    }
    
    public int getMonsterIntelligence () {
        return intelligence;
    }
    
        public int getMonsterDexterity () {
        return dexterity;
    }
        
}


