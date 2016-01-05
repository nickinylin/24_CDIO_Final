package game;

public class Jail {

	private boolean jail;

	public boolean setInJail() {
		jail = true;
		return jail;
	}
	
	public boolean getOutofJail() {
		jail = false;
		return jail;
	}

	public void getJailStatus(Player player) {
		this.jail = player;
	}
	
	
}
