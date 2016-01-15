package controllers;

import fields.Field;
import fields.Labor;
import game.Player;

public class LaborController {

	public void landOnLabor(Player[] players, Player player, Labor labor, Field[] fields) {

		if (labor.fieldowned) {

			if (labor.fieldowner.equals(player) == false) {

				labor.payRent(player, fields);

			}

		} else {

			labor.updateFieldGroup(player, labor, fields);
		}

	}

}