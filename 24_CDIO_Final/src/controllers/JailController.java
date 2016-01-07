package controllers;

import desktop_resources.GUI;
import fields.Field;
import game.Dice;
import game.Player;

public class JailController {

	public void jail(Player player) {
		player.setJail(true);

		if (player.getPlayerPosition() > 10) {
			player.payMoney(4000);
			player.setMove(10);
		}

	}

	public void payJail(Player player) {
		player.payMoney(1000);
		player.setJail(false);
		player.setJailTurn(0);
		GUI.setBalance(player.getName(), player.getMoney());
	}

	public boolean rollOutOfJail(Player player) {

		if (player.getJailTurn() < 3){
			GUI.getUserButtonPressed("", player.getName()+": Roll Dices");
			Dice.roll();
			GUI.setDice(Dice.getDice1(), Dice.getDice2());
			
			//Hvis han slår to ens kommer han ud af fængslet og får en ekstra tur
			if (Dice.issame()){
				player.setJail(false);
				player.setJailTurn(0);
				return true;
			}
			return false;
		}
		return false;
	}

	public void jailRollFailed(Player player) {
		player.setJailTurn(player.getJailTurn()+1);
	}

	public void jailRollMaxed(Player player) {

		if (player.getJailTurn() == 3) {

			player.payMoney(1000);
			GUI.setBalance(player.getName(), player.getMoney());
			GUI.displayChanceCard("Du har været i fængsel i 3 omgange og betaler automatisk 1000 kr for at komme ud");
			player.setJail(false);
			player.setJailTurn(0);
			
		}
	}
	
	public void doubbleRollJail(Player player, Field[] fields) {
		PlayerController.movePlayer(player, Dice.getSum(), fields);
		GUI.setCar(player.getPlayerPosition(), player.getName());
		player.setJailTurn(0);
		player.setJail(false);
	}

}
