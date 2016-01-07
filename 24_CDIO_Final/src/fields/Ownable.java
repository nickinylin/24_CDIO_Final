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
    
    public abstract int getRent(Field[] fields);
    
    /**
     * Bruges når en spiller lander på et felt der er ejet af en anden spiller.
     * @param player
     */
    public void payRent(Player player, Field[] fields) {
        int payamount = getRent(fields);
        player.payMoney(payamount);
        this.fieldowner.giveMoney(payamount);
        
        //ejerskab b�r defineres, evt. udnytte dele fra landsOnField().
    }
    
    
    
    /**
     * N�r spilleren �nsker at k�be et ledigt felt. Tager den aktuelle Player.
     * @param player
     */
    public abstract void buyField(Player player, Field[] fields);
    
    public abstract void updateFieldGroup(Player player, Field currentField, Field[] fields);
    
    public void setStatus(boolean boo) {
        fieldowned = boo;
    }
    
    public boolean getStatus() {
        return fieldowned;
    }
    
    public void setOwner(Player player) {
        fieldowner = player;
    }
    
    public Player getOwner() {
        return fieldowner;
    }

    
}
