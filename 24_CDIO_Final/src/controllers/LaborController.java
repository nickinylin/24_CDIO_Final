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



//	private void showMenu(Player[] players, Player player, Labor labor, Field[] fields) {
//		boolean res;
//		do{
//			res = menuController.menuBuild(players, player, labor, fields);
//		}while(!res);
//	}
}