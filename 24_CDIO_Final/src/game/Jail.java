package game;

public class Jail extends Field {
	
	
    public Jail(String name) {
        super(name);
    }
    
    public void JailEmpty(String name) {

    }

	@Override
	public void landOnField(Player player) {
		player.setJail(true);
		
	}
    
}