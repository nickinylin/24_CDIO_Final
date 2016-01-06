/**
 * Tax
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package fields;

import desktop_resources.GUI;
import game.Player;

public class Tax extends Field {
    protected String fieldname;
    private int taxAmount;
    private String special;
    
    public Tax(String name,int tax, String special){
        super(name);
        taxAmount=tax;
        this.special = special;
    }
    
    public int getRent() {
        return taxAmount;
    }
    
    @Override
    public String getName() {
        return this.fieldname;
    }
    
    public String getSpecial() {
    	return this.special;
    }
    
    
}
