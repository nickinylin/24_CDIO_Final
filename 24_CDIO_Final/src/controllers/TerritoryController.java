package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Territory;
import game.Player;

public class TerritoryController {

	public void landOnTerritory(Player[] players, Player player, Territory territory, Field[] fields) {

		if (territory.fieldowned) {

			if (player.equals(territory.fieldowner)) {
				GUI.displayChanceCard(player.getName() +"<br><br>"+ Language.ownable_landon3 + territory.getName() + Language.ownable_landon4);
			} else {
				GUI.displayChanceCard(player.getName() +"<br><br>"+ Language.ownable_landon1 + territory.getName() + Language.ownable_landon2 + territory.getOwner().getName() +"<br><br>"+ Language.ownable_rent1 + territory.getRent(territory.getOwner(), fields) + Language.ownable_rent2);
				player.payMoney(territory.getRent(territory.getOwner(), fields));
				territory.getOwner().giveMoney(territory.getRent(territory.getOwner(), fields));
				GUI.setBalance(player.getName(), player.getMoney());
				GUI.setBalance(territory.getOwner().getName(), territory.getOwner().getMoney());

			}
		} else {
			GUI.displayChanceCard(player.getName() +"<br><br>"+ Language.ownable_landon1 + territory.getName() + Language.ownable_buy1 +"<br><br>"+ Language.ownable_buy2 + territory.getPrice());
		}
	}
}
