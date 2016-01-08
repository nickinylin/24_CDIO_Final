package controllers;

import desktop_resources.GUI;
import fields.Field;
import game.Dice;
import game.Player;

public class JailController {

	Dice dice = new Dice();
	
	public void jail(Player player, Field[] fields) {
		
		player.setIsInJail(true);

		if (player.getPlayerPosition()-1 > 11) {
			
			player.setPlayerPositionToField(11);
			GUI.removeAllCars(player.getName());
			GUI.setCar(11, player.getName());
			
		} else {

			player.setPlayerPositionToField(11);
			GUI.removeAllCars(player.getName());
			GUI.setCar(11, player.getName());
			
		}

	}

	public void payJail(Player player) {
		
		player.payMoney(1000);
		player.setIsInJail(false);
		player.resetJailTurn();
		GUI.setBalance(player.getName(), player.getMoney());
		
	}

	public boolean rollOutOfJail(Player player, Field[] fields) {

		if (player.getJailTurn() < 3){
			GUI.getUserButtonPressed("", player.getName()+": Roll Dices");
			Dice.roll();
			GUI.setDice(Dice.getDice1(), Dice.getDice2());

			//Hvis player slår to ens kommer han ud af fængslet og får en ekstra tur
			if (Dice.issame()){
				player.setIsInJail(false);
				player.resetJailTurn();
				return true;
			}
			player.setJailTurn(1);
			GUI.showMessage("You are still in Jail");
			return false;
		} else {
			player.setIsInJail(false);
			player.resetJailTurn();
			player.payMoney(1000);
			GUI.setBalance(player.getName(), player.getMoney());
			GUI.displayChanceCard("Du har været i fængsel i 3 omgange og betaler automatisk 1000 kr for at komme ud");
			
			GUI.getUserButtonPressed("", player.getName()+": Roll Dices");
			Dice.roll();
			GUI.setDice(Dice.getDice1(), Dice.getDice2());
			
			return true;
		}

	}

}
