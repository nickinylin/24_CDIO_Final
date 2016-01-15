package controllers;

import desktop_resources.GUI;
import fields.*;
import game.Dice;
import game.Player;

public class JailController {

	Dice dice = new Dice();
	
	public void jail(Player player, Field[] fields) {
		
		player.setIsInJail(true);
		player.resetExtraTurns();

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

	public boolean rollOutOfJail(Player[] players,Player player, Field[] fields) {

		if (player.getJailTurn() < 2){
			GUI.getUserButtonPressed("", player.getName() + Language.roll);
			Dice.roll();
			GUI.setDice(Dice.getDice1(), Dice.getDice2());

			//Hvis player slår to ens kommer han ud af fængslet og får en ekstra tur
			if (Dice.issame()){
				player.setIsInJail(false);
				player.resetJailTurn();
				player.setNumberOfExtraTurns(1);
				return true;
			}else{
			player.setJailTurn(1);
			GUI.showMessage(player.getName() + Language.prison_stillinjail);
			return false;
			}
		} else {
			GUI.getUserButtonPressed("", player.getName() + Language.roll);
			Dice.roll();
			GUI.setDice(Dice.getDice1(), Dice.getDice2());
			if (Dice.issame()){
				player.setIsInJail(false);
				player.resetJailTurn();
				player.setNumberOfExtraTurns(1);
				return true;
			}else{
			player.setIsInJail(false);
			player.resetJailTurn();
			player.payMoney(1000);
			GUI.setBalance(player.getName(), player.getMoney());
			GUI.displayChanceCard(Language.prisonmaxturns);
			return true;
		}

	}

	}
}
