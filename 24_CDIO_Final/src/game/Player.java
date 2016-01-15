/**
 * Player
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package game;


public class Player {

	private Bank bank;
	private String name;
	private int extraturn = 0;
	private boolean jail = false;
	private int jailTurn = 0;
	private int move = 1;
	private boolean bankrupt = false;



	public Player(String name) {
		this.name = name;
		this.bank = new Bank();
	}

	/**
	 * Method gets the name of the player.
	 * @return String Playername
	 */
	public String getName() {
		return name;
	}

	public int getNumberOfExtraTurns() {
		return extraturn;
	}

	public int setNumberOfExtraTurns(int rollturn) {
		extraturn = extraturn + rollturn;
		return extraturn;
	}

	public int resetExtraTurns() {
		extraturn = 0;
		return extraturn;
	}

	/**
	 * Method gets the position the player is on the board.
	 * @return int position
	 */
	public int getPlayerPosition() {
		return this.move;
	}

	public void setPlayerPosition(int destination) {
		this.move = this.move + destination;
	}

	public void setPlayerPositionToField(int destination) {
		this.move = destination;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		return true;
	}

	public boolean bankruptCheck() {

		boolean bankrupt = false;

		if (getAssets() < 0) {
			bankrupt = true;
			this.bankrupt = true;
		}
		return bankrupt;
	}
	
	public void bankrupt() {
		bank.payMoney(bank.getMoney());
		bank.setAssets(bank.getAssets()-1);
		this.bankrupt = true;
	}

	public int getMoney(){
		return bank.getMoney();
	}
	public void payMoney(int payamount){
		bank.payMoney(payamount);
	}
	public void giveMoney(int newamount){
		bank.giveMoney(newamount);
	}
	public int getAssets(){
		return bank.getAssets();
	}
	public void setAssets(int newamount){
		bank.setAssets(newamount);
	}

	public void setIsInJail(boolean jail) {
		this.jail = jail;
	}

	public int getJailTurn() {
		return jailTurn;
	}

	public void setJailTurn(int jailTurn) {
		this.jailTurn = this.jailTurn + jailTurn;
	}
	public boolean isInJail() {
		return jail;
	}

	public void resetJailTurn() {
		this.jailTurn = 0;
	}

}