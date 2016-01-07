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
    public void buyField(Player player, Field[] fields){
        player.payMoney(fieldprice);
        player.setAssets(fieldprice);
        fieldowned = true;
        fieldowner = player;
        // TODO Bør flyttes til alle controlers
        // Kald direkte til GUI fra entiti er et problem
        GUI.setOwner(player.getPlayerPosition(), player.getName());
        GUI.setBalance(player.getName(), player.getMoney());
        GUI.setSubText(player.getPlayerPosition(), "Leje: "+getRent(fields)+"");
    }
    
    public void updateFieldGroup(Player player, Field currentField, Field[] fields) {
    	
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				if (f instanceof Territory) {
					Territory territory = (Territory) f;

					if (territory.getFieldGroup() == ((Territory) currentField).getFieldGroup()) {
						GUI.setSubText(i+1, "Leje: "+territory.getRent(fields)+"");
					}
				}
				
				if (f instanceof Fleet) {
					Fleet fleet = (Fleet) f;
					
					if (player.equals(((Fleet) currentField).fieldowner)) {
						GUI.setSubText(i+1, "Leje: "+fleet.getRent(fields)+"");
					}
				}
			}

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
