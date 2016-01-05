package game;

public class Luck extends Field {
	
	private CardsDeck card;
	
    public Luck(String name) {
        super(name);
    }

	@Override
	public void landOnField(Player player) {
		card.drawcard(player);	
	}
    
}
