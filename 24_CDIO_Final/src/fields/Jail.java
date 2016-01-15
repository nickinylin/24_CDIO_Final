package fields;

import game.Player;

public class Jail extends Field {
	
	
    public Jail(String name) {
        super(name);
    }
    
    
	@Override
	public String getFieldType() {
		return "Jail";
	}
}