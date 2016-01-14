package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Territory;
import game.Player;

public class TerritoryController {
	
	public void landOnTerritory(Player[] players, Player player, Territory territory, Field[] fields) {

		if (territory.fieldowned) {

			if (player.equals(territory.fieldowner)) {
	
			} else {
				
				player.payMoney(territory.getRent(territory.getOwner(), fields));
				territory.getOwner().giveMoney(territory.getRent(territory.getOwner(), fields));
				GUI.setBalance(player.getName(), player.getMoney());
				GUI.setBalance(territory.getOwner().getName(), territory.getOwner().getMoney());

			}
		} 
	}
}
