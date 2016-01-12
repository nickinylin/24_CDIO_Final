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
	private FleetController fleetController = new FleetController();
	private LaborController laborController = new LaborController();
	private JailController jailController = new JailController();
	private RefugeController refugeController = new RefugeController();
	private PlayerController playerController = new PlayerController();
	private MenuController menuController = new MenuController();
	private Player[] players;
	protected Field[] fields;

	public static void main(String[] args) {
		new Game();
	}

	public Game() {

		Setup setup = new Setup();
		// Create Fields players and card deck
		fields = setup.createFields();
		players = setup.createPlayers();


		boolean noWinner = true;

		while (noWinner) {

			for (int i = 0; i < players.length; i++) {

				if (players[i].bankrupt()) {
					checkWinner();
<<<<<<< HEAD
				} else if (players[i].isInJail()) {
=======
				} 
				else if (players[i].isInJail()) {
>>>>>>> branch 'master' of https://github.com/nickinylin/24_CDIO_Final
					doJailTurn(players[i]);
				} else {
					doNormalTurn(players[i]);
				}

			}
			
		}

	}



	public void doNormalTurn(Player player) {


		// Roll Dices
		GUI.getUserButtonPressed("", player.getName()+": Roll Dices");
		Dice.roll();
		GUI.setDice(Dice.getDice1(), Dice.getDice2());

		// Check for doubles
		if (Dice.issame()) {
			player.setNumberOfExtraTurns(1);
			if (player.getNumberOfExtraTurns() < 3) {

				PlayerController.movePlayer(player, Dice.getSum(), fields);

				Field currentfield = fields[player.getPlayerPosition()-1];

				chooseAction(player, currentfield);
				
				if (player.isInJail()) {
					jailController.jail(player, fields);
				} else {
					doNormalTurn(player);
				}
				
			} else {
				jailController.jail(player, fields);

			}
		} else {
			
			// Reset players extra turn
			player.resetExtraTurns();
			
			// Move the Player
			PlayerController.movePlayer(player, Dice.getSum(), fields);

			// Where is the Player?
			Field currentfield = fields[player.getPlayerPosition()-1];

			chooseAction(player, currentfield);
			menuController.showMenu(players, player, currentfield, fields);
			
		}
	}

	private void chooseAction(Player player, Field currentfield) {
		// Which action should be taken?
		if (currentfield instanceof Territory) {
			territoryController.landOnTerritory(players, player, ((Territory) currentfield), fields);
		} else if (currentfield instanceof Fleet) {
			fleetController.landOnFleet(players, player, ((Fleet) currentfield), fields);
		} else if (currentfield instanceof Labor) {
			laborController.landOnLabor(players, player, ((Labor) currentfield), fields);
		} else if (currentfield instanceof Refuge) {
			refugeController.landOnRefuge(players, player, ((Refuge) currentfield), fields);
		} else if (currentfield instanceof Luck) {
<<<<<<< HEAD
			luckController.landOnLuck(players, player, ((Luck) currentfield), fields);
=======
			
			luckController.landOnLuck(player, fields, this.players);
>>>>>>> branch 'master' of https://github.com/nickinylin/24_CDIO_Final
		} else if (currentfield instanceof Jail) {
			jailController.jail(player, fields);
		} else if (currentfield instanceof Tax) {
			taxController.payTax(player, ((Tax) currentfield));
		}
		
	}


	public void doJailTurn(Player player) {

		boolean payFine = GUI.getUserLeftButtonPressed(""+player.getName()+"", "Betal 1000 kr", "Slå med terningerne");

		if (payFine) {

			jailController.payJail(player);
			doNormalTurn(player);

		} else {

			if (jailController.rollOutOfJail(player, fields)) {
				PlayerController.movePlayer(player, Dice.getSum(), fields);
				GUI.setCar(player.getPlayerPosition(), player.getName());
				chooseAction(player,fields[player.getPlayerPosition()-1]);
				doNormalTurn(player);
			}

		}

	}


	public void checkWinner() {

		int numberofplayers = players.length;
		int count = 1;

		for (int i = 1; i < players.length; i++) {

			if (players[i].bankrupt()) {
				count++;
			}
			
			if (numberofplayers == count) 
			{

<<<<<<< HEAD
			if (numberofplayers == count) {
				
				for (int x = 1; x < players.length; x++) {
					
				}
					if (players[i].bankrupt()) {
				}
				GUI.displayChanceCard("<center>"+ players[i].getName() +" have won the game with a total of <br><br> "+players[i].getAssets()+"<br>assets.");
=======
                for (int x = 0; x < players.length; x++) 
                {
                    
                    if (players[x].bankrupt() == false) 
                    {
                        GUI.displayChanceCard("<center>"+players[x].getName()+" have won the game with a total of <br><br> "+players[x].getAssets()+"<br>assets.");
                        GUI.showMessage("");
                        return;
                    }
                }
                        				
>>>>>>> branch 'master' of https://github.com/nickinylin/24_CDIO_Final
			}


		}

	}

}
