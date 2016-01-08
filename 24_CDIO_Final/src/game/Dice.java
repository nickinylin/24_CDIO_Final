/**
 * Dice - Terninger
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package game;

public class Dice {
    private static int value1;
    private static int value2;

    
    /**
     * Creating the dices and calling the roll() method, to set dices value to a random,
     * even though they arent used yet.
     */
    public Dice(){
        roll();
    }
    
    /**
     * roll() method is generating two random numbers [1-6]
     * and setting a new value for dice1 and dice2
     */
    public static void roll(){
//        value1 = (int)(Math.random()*6)+1;
//        value2 = (int)(Math.random()*6)+1;
    	value1 = 6;
    	value2 = 6;
    }
    
    /**
     * This will return the number stored in value1 (first dice)
     * @return dice1
     */
    public static int getDice1() {
        return value1;
    }
    
    /**
     * This will return the number stored in value2 (second dice)
     * @return dice2
     */
    public static int getDice2() {
        return value2;
    }
    
    /**
     * The method getSum() is returning the sum of value1 and value2 (sum of dice)
     * @return dice1 + dice2
     */
    public static int getSum(){
        return value1 + value2;
    }
    
    public static boolean issame(){
    	return value1 == value2;
    	
    }
    public static void testRollDiceSame() {
    	value1 = 6;
    	value2 = 6;
    }
    
    
}
