package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Labor;
import fields.Ownable;
import fields.Territory;
import game.Player;

public class LaborController {

	private MenuController menuController = new MenuController();

	public void landOnLabor(Player[] players, Player player, Labor labor, Field[] fields) {

		if (labor.fieldowned) {

			if (labor.fieldowner.equals(player) == false) {

				labor.payRent(player, fields);

//				showMenu(players, player, labor, fields);

			}

		} else {

			//			menuController.showMenu(players, player, labor, fields);

//			showMenu(players, player, labor, fields);
			//			boolean buylabor = GUI.getUserLeftButtonPressed(""+player.getName()+" du er landet på "+labor.getName()+", vil du købe denne fabrik?", "Ja", "Nej");
			//
			//			if (buylabor) {
			//				labor.buyField(player, fields);
			//		        GUI.setBalance(player.getName(), player.getMoney());
			//		        labor.updateFieldGroup(player, labor, fields);
			//			}
			//			boolean buylabor = GUI.getUserLeftButtonPressed(""+player.getName()+" du er landet på "+labor.getName()+", vil du købe denne fabrik?", "Ja", "Nej");
			//
			//			if (buylabor) {
			//				labor.buyField(player, fields);
			//		        GUI.setBalance(player.getName(), player.getMoney());
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