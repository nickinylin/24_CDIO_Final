package fields;

import cards.Cards;
import cards.CardsDeck;
import cards.CardsMoveto;
import game.Player;

public class Luck extends Field {
		
    public Luck(String name) {
        super(name);
    }
	
	@Override
	public String getFieldType() {
		return "Luck";
	}
    
}
