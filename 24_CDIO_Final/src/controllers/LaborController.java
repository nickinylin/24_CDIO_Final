package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Labor;
import fields.Ownable;
import game.Player;

public class LaborController {

	public void landOnLabor(Player player, Labor labor, Field[] fields) {

		if (labor.fieldowned) {

			if (labor.fieldowner.equals(player) == false) {

				labor.payRent(player, fields);

			}

		} else {

			boolean buylabor = GUI.getUserLeftButtonPressed(""+player.getName()+" du er landet på "+labor.getName()+", vil du købe denne fabrik?", "Ja", "Nej");

			if (buylabor) {
				labor.buyField(player, fields);
		        GUI.setBalance(player.getName(), player.getMoney());
		        // TODO upgrade fields
		        labor.updateFieldGroup(player, labor, fields);
			}

		}

	}
}
