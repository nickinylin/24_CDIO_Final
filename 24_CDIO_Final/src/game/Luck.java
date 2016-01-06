package game;

public class Luck extends Field {
	
	private CardsDeck deck;
	
    public Luck(String name) {
        super(name);
    }

	@Override
	public void landOnField(Player player) {
		Cards card= deck.drawcard();
		if (card instanceof CardsMoveto) {
            CardsMoveto move=(CardsMoveto) card;
            if (move.getExtraMoves()==0){
            	
            }
            	
            
            }
	}
    
}
