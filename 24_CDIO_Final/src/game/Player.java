/**
 * Player
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package game;



import desktop_resources.GUI;
import fields.Field;

public class Player {
    
    private Bank bank;
    private String name;
    private int move = 1;
    private int extraturn = 0;
    private boolean jail = false;
    private int jailTurn = 0;
    
    
    
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
    
    public int getNumberOfExtraTurns() {
    	return extraturn;
    }
    
    public int setNumberOfExtraTurns(int roll) {
    	extraturn = extraturn + roll;
    	return extraturn;
    }
    
    public int resetExtraTurns() {
    	extraturn = 0;
    	return extraturn;
    }
    
    /**
     * Method moves the player in the GUI
     * @param player
     * @param move
     */
    //TODO refactor - move to controller
    public void movePlayer(Player player, int move, Field[] fields) {
    	
    	if (this.move + move > fields.length) {
            int go = this.move + move;
            int newmove = go%fields.length;
            bank.giveMoney(4000);
            GUI.removeCar(this.move, player.getName());
            GUI.setBalance(name, bank.getMoney());
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
    public void setPlayerPosistion(Player player, int destination){
    if (move>destination){

    bank.giveMoney(4000);
    GUI.removeCar(this.move, name);
	move=destination;
    GUI.setBalance(name, bank.getMoney());
    GUI.setCar(move, name);
    }
    else
    GUI.removeCar(this.move, name);
	move=destination;
    GUI.setCar(move, name);
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

	public void setJail(boolean jail) {
		this.jail = jail;
	}

	public int getJailTurn() {
		return jailTurn;
	}

	public void setJailTurn(int jailTurn) {
		this.jailTurn = jailTurn;
	}
	public boolean isInJail() {
		return jail;
	}
}