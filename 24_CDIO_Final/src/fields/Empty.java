package fields;

import game.Player;

public class Empty extends Field {
	
    public Empty(String name) {
        super(name);
    }

	@Override
	public String getFieldType() {
		return null;
	}
    
}