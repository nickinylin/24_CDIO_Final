/**
 * Refuge
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package fields;

import desktop_resources.GUI;
import game.Player;

public class Refuge extends Field {
    
    protected String fieldname;
    protected int bonus;
    protected String type;
    
    public Refuge(String name, String type, int bonus){
        super(name);
        this.type = type;
        this.bonus = bonus;
    }
    
    public void landOnField(Player player){
        GUI.displayChanceCard("<center>"+player.getName()+" have landed on a BONUS field<br><br>You recieve "+bonus+".");
        player.giveMoney(bonus);
        GUI.setBalance(player.getName(), player.getMoney());
    }
    
    public String type() {
    	return type;
    }
    
    public int getRent() {
        return bonus;
    }

    @Override
    public String getName() {
        return fieldname;
    }

    public int getPrice() {
        return 0;
    }

    
}
