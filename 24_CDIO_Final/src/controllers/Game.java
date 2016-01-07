/**
 * Game
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package controllers;

import cards.Cards;
import cards.CardsDeck;
import cards.CardsMoveto;
import desktop_resources.GUI;
import fields.Empty;
import fields.Field;
import fields.Fleet;
import fields.Jail;
import fields.Labor;
import fields.Luck;
import fields.Ownable;
import fields.Refuge;
import fields.Tax;
import fields.Territory;
import game.Dice;
import game.Player;
import setup.Setup;


public class Game {
	private TaxController taxController = new TaxController();
	private LuckController luckController = new LuckController();
	private TerritoryController territoryController = new TerritoryController();
	private FleetController FleetController = new FleetController();
	private LaborController LaborController = new LaborController();
	private JailController jailController = new JailController();
	private static Player[] player;
	protected static Field[] fields;

	public static void main(String[] args) {
		new Game();
	}

	public Game() {

		Setup setup = new Setup();
		// Create Fields players and card deck
		fields = setup.createFields();
		player = setup.createPlayers();
		
		String test = GUI.getUserButtonPressed("Vælg en knap", "1","2","3","4","5");

		boolean noWinner = true;
		
		while (noWinner) {
			
			for (int i = 0; i < player.length; i++) {
				
				if (player[i].bankrupt()) {
					checkWinner();
				} 
				
				if (player[i].isInJail()) {
					doJailTurn(player[i]);
				} else {
					doNormalTurn(player[i]);
				}
				
			}
			
		}
		


	}

	
	
	public void doNormalTurn(Player player) {
		
        // Roll Dices
        GUI.getUserButtonPressed("", player.getName()+": Roll Dices");
        Dice.roll();
        GUI.setDice(Dice.getDice1(), Dice.getDice2());
		
        // Move the Player
        player.movePlayer(player, Dice.getSum());
        
        // Where is the Player?
		Field currentfield = fields[player.getPlayerPosition()-1];

		// Which action should be taken?
		if (currentfield instanceof Ownable) {

			landOnOwnable(player, currentfield);

		} else if (currentfield instanceof Luck) {
			
			//landOnLuck(player, currentfield);

		} else if (currentfield instanceof Jail) {
			
			landOnJail(player, currentfield);

		} else if (currentfield instanceof Refuge) {
			jailController.jail();
		} else if (currentfield instanceof Tax) {
			taxController.payTax(player, ((Tax) currentfield));
		}
		
		if (Dice.issame()) {
			
			if (player.getNumberOfExtraTurns() < 3) {
				
				player.setNumberOfExtraTurns(1);
				doNormalTurn(player);
				
			} else {
				
				// Move play to jail
				
			}
			
		}
		
	}



	private void landOnJail(Player player, Field currentfield) {
		
		// Move player to Jail
		
	}



	private void landOnOwnable(Player player, Field currentfield) {
		
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


	public void checkWinner() {
		
		int numberofplayers = player.length;
		int count = 0;
		
		for (int i = 0; i < player.length; i++) {
			
			if (player[i].bankrupt()) {
				count++;
			}
			
			if (numberofplayers == count) {
				GUI.displayChanceCard("<center>"+ player[i].getName() +" have won the game with a total of <br><br> "+player[i].getAssets()+"<br>assets.");
			}
			
		}
		
	}

}
