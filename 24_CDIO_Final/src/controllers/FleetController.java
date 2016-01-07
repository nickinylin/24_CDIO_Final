package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Fleet;
import fields.Ownable;
import game.Player;

public class FleetController {

	public void landOnFleet(Player player, Fleet fleet, Field[] fields) {

		if (fleet.fieldowned) {

			if (fleet.fieldowner.equals(player) == false) {

				fleet.payRent(player, fields);

			}

		} else {

			boolean buyfleet = GUI.getUserLeftButtonPressed(""+player.getName()+" du er landet på "+fleet.getName()+", vil du købe flåden?", "Ja", "Nej");

			if (buyfleet) {
				fleet.buyField(player, fields);
			}

		}

	}
}