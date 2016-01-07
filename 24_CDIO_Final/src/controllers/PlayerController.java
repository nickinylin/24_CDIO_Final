package controllers;

import desktop_resources.GUI;
import fields.Field;
import game.Bank;
import game.Player;

public class PlayerController {
    private int move = 1;
    private Bank bank;

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
            GUI.setBalance(player.getName(), bank.getMoney());
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
    GUI.removeCar(this.move, player.getName());
	move=destination;
    GUI.setBalance(player.getName(), bank.getMoney());
    GUI.setCar(move, player.getName());
    }
    else
    GUI.removeCar(this.move, player.getName());
	move=destination;
    GUI.setCar(move, player.getName());
    }

}
