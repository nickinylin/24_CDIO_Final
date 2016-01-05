/**
 * Player
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package game;

import java.awt.Color;
import desktop_codebehind.Car;
import desktop_resources.GUI;

public class Player {
    
    private Bank bank;
    private String name;
    private static Player[] id;
    private int move = 0;
    private Jail jail;
    
    public static Player[] addPlayer(int antal) {
        
        id = new Player[antal];
        
        for (int i = 0; i < antal; i++) {
            
            
            GUI.displayChanceCard("Type in your name");
            String name = GUI.getUserString("");
            
            if (name.equals("")) {
                name = "Player "+(i+1);
            }
            
            GUI.displayChanceCard(name + " choose your type of car");
            String cartype = GUI.getUserSelection("", "Car", "RaceCar", "Tractor", "Ufo");
            
            Car.Builder builder = new Car.Builder();
            
            switch (cartype) {
                default:
                case "Car": builder.typeCar(); break;
                case "RaceCar": builder.typeRacecar(); break;
                case "Tractor": builder.typeTractor(); break;
                case "Ufo": builder.typeUfo(); break;
            }
            
            GUI.displayChanceCard(name + " choose your "+cartype+" color");
            String color = GUI.getUserSelection("", "Red", "Blue", "Green", "Yellow", "White", "Black", "Pink", "Magenta", "Grey", "Orange", "Cyan");
            
            switch (color) {
                default:
                case "Red": builder.primaryColor(Color.RED); break;
                case "Blue": builder.primaryColor(Color.BLUE); break;
                case "Green": builder.primaryColor(Color.GREEN); break;
                case "Yellow": builder.primaryColor(Color.YELLOW); break;
                case "White": builder.primaryColor(Color.WHITE); break;
                case "Black": builder.primaryColor(Color.BLACK); break;
                case "Pink": builder.primaryColor(Color.PINK); break;
                case "Magenta": builder.primaryColor(Color.MAGENTA); break;
                case "Grey": builder.primaryColor(Color.LIGHT_GRAY); break;
                case "Orange": builder.primaryColor(Color.ORANGE); break;
                case "Cyan": builder.primaryColor(Color.CYAN); break;
            }
            
            Car car = builder.build();
            
            Player player = new Player(name);
            id[i] = player;
            
            GUI.addPlayer(name, player.getMoney(), car);
        }
        
        return id;
    }
    
    public Player(String name) {
        this.name = name;
        this.bank = new Bank();
    }
    /**
     * Method gets the name of the player.
     * @return String Playername
     */
    public String getName() {
        return name;
    }
    /**
     * Method moves the player in the GUI
     * @param player
     * @param move
     */
    public void movePlayer(Player player, int move) {
        
        if (this.move == 0) {
            this.move = move;
            GUI.setCar(move, player.getName());
        } else if (this.move + move > Field.getNumberOfFields()) {
            int go = this.move + move;
            int newmove = go%Field.getNumberOfFields();
            
            GUI.removeCar(this.move, player.getName());
            this.move = newmove;
            GUI.setCar(newmove, player.getName());
        } else {
            GUI.removeCar(this.move, player.getName());
            this.move = this.move + move;
            GUI.setCar(this.move, player.getName());
        }
        
    }
    
    /**
     * Method gets the position the player is on the board.
     * @return int position
     */
    public int getPlayerPosition() {
        return this.move;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Player other = (Player) obj;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        return true;
    }
    
    public boolean moveToJail(Player player) {
    	jail.getJailStatus(player);
    }
    
    public boolean getJailStatus() {
        return this.jail;
    }
    
    public boolean bankrupt() {
        
        boolean lose = false;
        
        if (getAssets() < 0) {
            lose = true;
        }
        return lose;
    }
    
    public int getMoney(){
        return bank.getMoney();
    }
    public void payMoney(int payamount){
        bank.payMoney(payamount);
    }
    public void giveMoney(int newamount){
        bank.giveMoney(newamount);
    }
    public int getAssets(){
        return bank.getAssets();
    }
    public void setAssets(int newamount){
        bank.setAssets(newamount);
    }
}