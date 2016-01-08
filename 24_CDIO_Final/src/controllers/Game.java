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
	private Player[] player;
	protected Field[] fields;

	public static void main(String[] args) {
		new Game();
	}

	public Game() {
		
		Setup setup = new Setup();
		// Create Fields players and card deck
		fields = setup.createFields();
		player = setup.createPlayers();
		

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
        
        // Check for doubles
		if (Dice.issame()) {
			if (player.getNumberOfExtraTurns() < 3) {
				player.setNumberOfExtraTurns(1);
				
				PlayerController.movePlayer(player, Dice.getSum(), fields);
				
				Field currentfield = fields[player.getPlayerPosition()-1];
				
				if (currentfield instanceof Territory) {
					territoryController.landOnTerritory(player, ((Territory) currentfield), fields);
				} else if (currentfield instanceof Fleet) {
					fleetController.landOnFleet(player, ((Fleet) currentfield), fields);
				} else if (currentfield instanceof Labor) {
					laborController.landOnLabor(player, ((Labor) currentfield), fields);
				} else if (currentfield instanceof Refuge) {
					refugeController.landOnRefuge(player, ((Refuge) currentfield), fields);
				} else if (currentfield instanceof Luck) {
					//luckController.landOnLuck(player, fields, this.player);
				} else if (currentfield instanceof Jail) {
					jailController.jail(player, fields);
				} else if (currentfield instanceof Tax) {
					taxController.payTax(player, ((Tax) currentfield));
				}
				
				doNormalTurn(player);
			} else {
				jailController.jail(player, fields);
			}
		}
		
        // Move the Player
        PlayerController.movePlayer(player, Dice.getSum(), fields);
        
        // Where is the Player?
		Field currentfield = fields[player.getPlayerPosition()-1];

		// Which action should be taken?
		if (currentfield instanceof Territory) {
			territoryController.landOnTerritory(player, ((Territory) currentfield), fields);
		} else if (currentfield instanceof Fleet) {
			fleetController.landOnFleet(player, ((Fleet) currentfield), fields);
		} else if (currentfield instanceof Labor) {
			laborController.landOnLabor(player, ((Labor) currentfield), fields);
		} else if (currentfield instanceof Refuge) {
			refugeController.landOnRefuge(player, ((Refuge) currentfield), fields);
		} else if (currentfield instanceof Luck) {
			//luckController.landOnLuck(player, fields, this.player);
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

			if (jailController.rollOutOfJail(player)) {
				jailController.doubbleRollJail(player, fields);
			}
				PlayerController.movePlayer(player, Dice.getSum(), fields);
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
