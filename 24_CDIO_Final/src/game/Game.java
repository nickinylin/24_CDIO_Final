/**
 * Game
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package game;

import desktop_resources.GUI;
import setup.Setup;


public class Game {

<<<<<<< HEAD
	private static Player[] player;
	protected static Field[] fields;
=======

	private static Field[] fields;

>>>>>>> branch 'master' of https://github.com/nickinylin/24_CDIO_Final

	public static void main(String[] args) {
		new Game();
	}

	public Game() {

		// Create Fields
		fields = Field.createFields();
		
		String test = GUI.getUserButtonPressed("Vælg en knap", "1","2","3","4","5");

<<<<<<< HEAD
		boolean noWinner = true;
		
		while (noWinner) {
			
			for (int i = 0; i < player.length; i++) {
				
				if (player[i].isInJail()) {
					
					doJailTurn(player[i]);
					
				} else {
					
					doNormalTurn(player[i]);
					
=======
		Setup setup = new Setup();
		Player[] players = setup.createPlayers();


		while (true) {

			for (int i = 0; i < players.length; i++) {

				int count = 1;

				for (int x = 0; x < players.length; x++) {

					if (players[x].bankrupt()) {
						GUI.removeAllCars(players[x].getName());
						count++;
					}
>>>>>>> branch 'master' of https://github.com/nickinylin/24_CDIO_Final
				}
				
				if (player[i].bankrupt()) {
					checkWinner();
				}
<<<<<<< HEAD
				
=======

				// If a player is bankrupt
				if (players[i].getAssets() < 0) {
					continue;
				}
				// Tjekker om spilleren er i fængsel
				if (players[i].isJail()){

					//Ser om spilleren vil betale for at komme ud eller slå med terningerne
					boolean boo = GUI.getUserLeftButtonPressed(""+players[i].getName()+"", "Betal 1000 kr", "Slå med terningerne");
					//hvis spilleren vælger at betale bliver han trukket 1000 og får en normal tur
					if (boo) {
						players[i].payMoney(1000);
						players[i].setJail(false);
						players[i].setJailDice(0);
						GUI.setBalance(players[i].getName(), players[i].getMoney());

						//Hvis spilleren vælger at kaste terningerne:
					} else {
						// Kontrollerer om han har været i fængsel i 3 omgange
						if (players[i].getJailDice() < 3){
							GUI.getUserButtonPressed("", players[i].getName()+": Roll Dices");
							Dice.roll();
							GUI.setDice(Dice.getDice1(), Dice.getDice2());
							//Hvis han slår to ens kommer han ud af fængslet og får en ekstra tur
							if (Dice.issame()){
								players[i].movePlayer(players[i], Dice.getSum());
								GUI.setCar(players[i].getPlayerPosition(), players[i].getName());
								players[i].setJailDice(0);
								players[i].setJail(false);
								count--;
								continue;
								// Hvis han ikke slår to ens bliver det næste spillers tur
							} else {

								players[i].setJailDice(players[i].getJailDice()+1);
								continue;
							}
							/**
							 * Hvis han har været i fængsel 3 omgange, betaler man automatisk 1000 for at komme ud
							 * og får en normal tur herefter
							 */

						} else{
							GUI.getUserButtonPressed("Du har været i fængsel i 3 omgange og skal betale 1000 for at komme ud", "Betal 1000");
							players[i].payMoney(1000);
							GUI.setBalance(players[i].getName(), players[i].getMoney());
							players[i].setJail(false);
							players[i].setJailDice(0);
						}

					}


				}



				// Roll Dices
				GUI.getUserButtonPressed("", players[i].getName()+": Roll Dices");
				Dice.roll();
				GUI.setDice(Dice.getDice1(), Dice.getDice2());

				// Move Player
				players[i].movePlayer(players[i], Dice.getSum());

				// This determains what action will accure depending on the field type.
				fields[players[i].getPlayerPosition()-1].landOnField(players[i]);
				players[i].bankrupt();

>>>>>>> branch 'master' of https://github.com/nickinylin/24_CDIO_Final
			}
			
		}
		


	}

	
	
	public void doNormalTurn(Player player) {
		
		Field currentfield = fields[Player.getPlayerPosition()-1];

		if (currentfield instanceof Ownable) {

			landOnOwnable(player, currentfield);

		} else if (currentfield instanceof Luck) {
			
			

		} else if (currentfield instanceof Jail) {

		} else if (currentfield instanceof Refuge) {

		} else if (currentfield instanceof Empty) {

		}
		
	}

	private void landOnOwnable(Player player, Field currentfield) {
		if (currentfield instanceof Territory) {

			if (((Ownable) currentfield).fieldowned) {

				// Hvem ejer feltet?
				if (((Ownable) currentfield).fieldowner.equals(player)) {

					// Du ejer det selv
					int numberofgroupfields = 0;
					int ownedfields = 0;

					for (Field f : fields) {
						if (f instanceof Territory) {
							Territory t = (Territory) f;

							if (t.getFieldGroup() == ((Territory) currentfield).getFieldGroup()) {
								numberofgroupfields++;
								if (t.getOwner().equals(player) ) {
									ownedfields++;
								}
							}
						}
					}

					if (numberofgroupfields == ownedfields) {
						
						//buyhouse or hotel
					}

				} else {

					// Du skal betale rente
					((Ownable) currentfield).payRent(player);

				}

			} else { // The field was not owned
				
				// Want to buy field?
			}

		} else if (currentfield instanceof Fleet) {
			
			if (((Ownable) currentfield).fieldowned) {
				
				if (((Ownable) currentfield).fieldowner.equals(player)) {
					// Du ejer selv flåden.
				} else {
					// Du skal betale rente
					((Ownable) currentfield).payRent(player);
				}
				
			} else {
				// buy fleet
			}

		} else if (currentfield instanceof Labor) {

			if (((Ownable) currentfield).fieldowned) {
				
				if (((Ownable) currentfield).fieldowner.equals(player)) {
					// Du ejer slev Labor
				} else {
					// Du skal betale rente
					((Ownable) currentfield).payRent(player);
				}
				
			} else {
				// buy labor
			}
			
		}
	}

	public void doJailTurn(Player player) {

		boolean boo = GUI.getUserLeftButtonPressed(""+player.getName()+"", "Betal 1000 kr", "Slå med terningerne");

		if (boo) {
			player.payMoney(1000);
			player.setJail(false);
			player.setJailTurn(0);
			GUI.setBalance(player.getName(), player.getMoney());
			doNormalTurn(player);
			
		} else {
			
			// Kontrollerer om han har været i fængsel i 3 omgange
			if (player.getJailTurn() < 3){
				GUI.getUserButtonPressed("", player.getName()+": Roll Dices");
				Dice.roll();
				GUI.setDice(Dice.getDice1(), Dice.getDice2());
				//Hvis han slår to ens kommer han ud af fængslet og får en ekstra tur
				if (Dice.issame()){
					player.movePlayer(player, Dice.getSum());
					GUI.setCar(player.getPlayerPosition(), player.getName());
					player.setJailTurn(0);
					player.setJail(false);
					
					
					// MISSING A LOT
					
					


				} else {

					player.setJailTurn(player.getJailTurn()+1);

				}
				/**
				 * Hvis han har været i fængsel 3 omgange, betaler man automatisk 1000 for at komme ud
				 * og får en normal tur herefter
				 */

			} else{
				player.payMoney(1000);
				GUI.setBalance(player.getName(), player.getMoney());
				GUI.displayChanceCard("Du har været i fængsel i 3 omgange og betaler automatisk 1000 kr for at komme ud");
				player.setJail(false);
				player.setJailTurn(0);
			}


		}


	}


	public static void checkWinner() {
		
		GUI.displayChanceCard("<center>"+player.getName()+" have won the game with a total of <br><br> "+players[x].getAssets()+"<br>assets.");
		GUI.showMessage("");
		return;
		
	}

}
