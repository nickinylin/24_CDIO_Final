package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Fleet;
import fields.Labor;
import fields.Ownable;
import game.Player;

public class FleetController {

	private MenuController menuController = new MenuController();
	
	public void landOnFleet(Player[] players, Player player, Fleet fleet, Field[] fields) {

		if (fleet.fieldowned) {

			if (fleet.fieldowner.equals(player) == false) {

				fleet.payRent(player, fields);
				GUI.setBalance(player.getName(), player.getMoney());
				
			}

		} else {

			showMenu(players, player, fleet, fields);
			
//			boolean buyfleet = GUI.getUserLeftButtonPressed(""+player.getName()+" du er landet på "+fleet.getName()+", vil du købe flåden?", "Ja", "Nej");
//
//			if (buyfleet) {
//				fleet.buyField(player, fields);
//		        GUI.setBalance(player.getName(), player.getMoney());
//		        // TODO upgrade fields
//		        fleet.updateFieldGroup(player, fleet, fields);
//			}

		}

	}
	
	private void showMenu(Player[] players, Player player, Fleet fleet, Field[] fields) {
		boolean res;
		do{
			res = menuController.menuBuild(players, player, fleet, fields);
		}while(!res);
	}
	
}
