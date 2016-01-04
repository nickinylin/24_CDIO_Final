/**
 * Tax
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package game;

public class Tax extends Field {
    protected String fieldname;
    private int taxAmount;
    private String special;
    
    public Tax(String name,int tax, String special){
        super(name);
        taxAmount=tax;
        this.special = special;
    }
    
    
}
