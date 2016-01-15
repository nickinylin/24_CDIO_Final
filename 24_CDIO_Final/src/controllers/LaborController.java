package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Labor;
import game.Player;

public class LaborController {

	public void landOnLabor(Player[] players, Player player, Labor labor, Field[] fields) {

		if (labor.fieldowned) {

			if (player.equals(labor.fieldowner)) {
				GUI.displayChanceCard(player.getName() +"<br><br>"+ Language.ownable_landon3 + labor.getName() + Language.ownable_landon4);
			} else {
				GUI.displayChanceCard(player.getName() +"<br><br>"+ Language.ownable_landon1 + labor.getName() + Language.ownable_landon2 + labor.getOwner().getName() +"<br><br>"+ Language.ownable_rent1 + labor.getRent(labor.getOwner(), fields) + Language.ownable_rent2);
				labor.payRent(player, fields);
			}

		} else {
			GUI.displayChanceCard(player.getName() +"<br><br>"+ Language.ownable_landon1 + labor.getName() + Language.ownable_buy1 +"<br><br>"+ Language.ownable_buy2 + labor.getPrice());
			labor.updateFieldGroup(player, labor, fields);
		}

	}

}