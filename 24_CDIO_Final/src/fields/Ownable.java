/**
 * Ownable
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package fields;

import controllers.PlayerController;
import desktop_resources.GUI;
import game.Player;

public abstract class Ownable extends Field {
    
    public Ownable(String name) {
        super(name);
    }
    
    public Player fieldowner;
    protected int fieldprice;
    public boolean fieldowned;
	protected boolean pawned = false;
    
    public abstract int getRent(Player player, Field[] fields);
    
    /**
     * Bruges når en spiller lander på et felt der er ejet af en anden spiller.
     * @param player
     */
    public void payRent(Player player, Field[] fields) {
        int payamount = getRent(player, fields);
        player.payMoney(payamount);
        this.fieldowner.giveMoney(payamount);
        GUI.setBalance(player.getName(), player.getMoney());
        GUI.setBalance(this.fieldowner.getName(), this.fieldowner.getMoney());
        
        
        //ejerskab b�r defineres, evt. udnytte dele fra landsOnField().
    }
    
    
    
    /**
     * N�r spilleren �nsker at k�be et ledigt felt. Tager den aktuelle Player.
     * @param player
     */
    public void buyField(Player player, Field[] fields) {
    	if (!pawned){
    		player.payMoney(fieldprice);
    		player.setAssets(fieldprice);
    		fieldowned = true;
    		fieldowner = player;
    	} else {
    		player.payMoney(fieldprice);
    		player.setAssets(fieldprice);
    		fieldowned = true;
    		fieldowner = player;
    		pawned = true;
    	}
    	
    }
    
    public abstract void updateFieldGroup(Player player, Field currentField, Field[] fields);
    
    public abstract boolean getPawned();
    
    public abstract void setPawned(boolean pawn);
    
    public abstract double getPawnPrice();
    
    public abstract int getPrice();
    
    public void setOwned() {
        fieldowned = true;
    }
    
    public boolean getStatus() {
        return fieldowned;
    }
    
    public void setOwner(Player player) {
        fieldowner = player;
    }
    
    public void setOwnedToFalse() {
        fieldowned = false;
    }
    
    public Player getOwner() {
        return fieldowner;
    }

    
}
