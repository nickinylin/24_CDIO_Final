package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Fleet;
import game.Player;

public class FleetController {
	
	public void landOnFleet(Player[] players, Player player, Fleet fleet, Field[] fields) {

		if (fleet.fieldowned) {

			if (player.equals(fleet.fieldowner)) {
				GUI.displayChanceCard(player.getName() +"<br><br>"+ Language.ownable_landon3 + fleet.getName() + Language.ownable_landon4);
			} else {
				GUI.displayChanceCard(player.getName() +"<br><br>"+ Language.ownable_landon1 + fleet.getName() + Language.ownable_landon2 + fleet.getOwner().getName() +"<br><br>"+ Language.ownable_rent1 + fleet.getRent(fleet.getOwner(), fields) + Language.ownable_rent2);				fleet.payRent(player, fields);
				GUI.setBalance(player.getName(), player.getMoney());
			}

		} else {
			GUI.displayChanceCard(player.getName() +"<br><br>"+ Language.ownable_landon1 + fleet.getName() + Language.ownable_buy1 +"<br><br>"+ Language.ownable_buy2 + fleet.getPrice());
			fleet.updateFieldGroup(player, fleet, fields);
			
		}

	}
	
}
