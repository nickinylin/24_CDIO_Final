package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Ownable;
import fields.Territory;
import game.Player;

public class TerritoryController {
	
	public void landOnTerritory(Player[] players, Player player, Territory territory, Field[] fields) {

		if (territory.fieldowned) {

			if (player.equals(territory.fieldowner)) {
//				menuController.showMenu(players, player, territory, fields);
//				showMenu(players, player, territory, fields);
	
			} else {
				player.payMoney(territory.getRent(territory.getOwner(), fields));
				territory.getOwner().giveMoney(territory.getRent(player, fields));
				GUI.setBalance(player.getName(), player.getMoney());
				GUI.setBalance(territory.getOwner().getName(), territory.getOwner().getMoney());

//				menuController.showMenu(players, player, territory, fields);
//				showMenu(players, player, territory, fields);
			}
		} else {
//			menuController.showMenu(players, player, territory, fields);
//			showMenu(players, player, territory, fields);
		}
	}


}
