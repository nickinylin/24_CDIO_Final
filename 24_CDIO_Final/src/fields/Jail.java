package fields;

import game.Player;

public class Jail extends Field {
	
	
    public Jail(String name) {
        super(name);
    }
    
    public void gotoJail(Player player) {
    	
    }
    
	@Override
	public String getFieldType() {
		return "Jail";
	}
}