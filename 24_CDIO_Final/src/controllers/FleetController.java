package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Fleet;
import game.Player;

public class FleetController {
	
	public void landOnFleet(Player[] players, Player player, Fleet fleet, Field[] fields) {

		if (fleet.fieldowned) {

			if (fleet.fieldowner.equals(player) == false) {

				fleet.payRent(player, fields);
				GUI.setBalance(player.getName(), player.getMoney());
				
			}

		} else {

			fleet.updateFieldGroup(player, fleet, fields);
			
		}

	}
	
}
