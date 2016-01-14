/**
 * Tax
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package fields;

public class Tax extends Field {
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
    
    public String getSpecial() {
    	return this.special;
    }
    
	@Override
	public String getFieldType() {
		return "Tax";
	}
    
}
