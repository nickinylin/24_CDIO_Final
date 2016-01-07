/**
 * Ownable
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package fields;

import desktop_resources.GUI;
import game.Player;

public abstract class Ownable extends Field {
    
    public Ownable(String name) {
        super(name);
    }
    
    public Player fieldowner;
    protected int fieldprice;
    public boolean fieldowned;
    
    public abstract int getRent();
    
    /**
     * Bruges når en spiller lander på et felt der er ejet af en anden spiller.
     * @param player
     */
    public void payRent(Player player) {
        int payamount = getRent();
        player.payMoney(payamount);
        this.fieldowner.giveMoney(payamount);
        
        //ejerskab b�r defineres, evt. udnytte dele fra landsOnField().
    }
    
    
    
    /**
     * N�r spilleren �nsker at k�be et ledigt felt. Tager den aktuelle Player.
     * @param player
     */
    public void buyField(Player player){
        player.payMoney(fieldprice);
        player.setAssets(fieldprice);
        fieldowned = true;
        fieldowner = player;
        GUI.setOwner(player.getPlayerPosition(), player.getName());
        GUI.setBalance(player.getName(), player.getMoney());
        GUI.setSubText(player.getPlayerPosition(), "Leje: "+getRent()+"");
    }
    
    
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
