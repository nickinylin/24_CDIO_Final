package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Ownable;
import fields.Territory;
import game.Player;

public class TerritoryController {

	private MenuController menuController = new MenuController();
	
	public void landOnTerritory(Player[] players, Player player, Territory territory, Field[] fields) {

		if (territory.fieldowned) {

			if (player.equals(territory.fieldowner)) {

				while (menuController.menuBuild(players, player, territory, fields) == false) {
					menuController.menuBuild(players, player, territory, fields);
				}
				
			} else {
				player.payMoney(territory.getRent(player, fields));
				territory.getOwner().giveMoney(territory.getRent(player, fields));
				GUI.setBalance(player.getName(), player.getMoney());
				GUI.setBalance(territory.getOwner().getName(), territory.getOwner().getMoney());

				while(menuController.menuBuild(players, player, territory, fields) == false) {
					menuController.menuBuild(players, player, territory, fields);
				}
			}

		} else {

			
			while (menuController.menuBuild(players, player, territory, fields) == false) {
				menuController.menuBuild(players, player, territory, fields);
			}

		}
	}

}
