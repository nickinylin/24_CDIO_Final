/**
 * Refuge
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package game;

public class Refuge extends Field {
    
    protected String fieldname;
    protected int bonus;
    
    public Refuge(String name, int bonus){
        super(name);
        this.bonus = bonus;
    }
    
    public void landOnField(Player player){
        GUI.displayChanceCard("<center>"+player.getName()+" have landed on a BONUS field<br><br>You recieve "+bonus+".");
        player.giveMoney(bonus);
        GUI.setBalance(player.getName(), player.getMoney());
    }

    @Override
    public int getRent() {
        return bonus;
    }

    @Override
    public String getName() {
        return fieldname;
    }

    @Override
    public int getPrice() {
        return 0;
    }

    
}
