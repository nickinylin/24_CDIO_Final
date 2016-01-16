/**
 * Refuge
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package fields;

public class Refuge extends Field {
    
    private int bonus;
    private String type;
    
    public Refuge(String name, String type, int bonus){
        super(name);
        this.type = type;
        this.bonus = bonus;
    }
    
    public String type() {
    	return type;
    }
    
    public int getRent() {
        return bonus;
    }

    public int getPrice() {
        return 0;
    }
    
	@Override
	public String getFieldType() {
		return "Refuge";
	}
    
}
