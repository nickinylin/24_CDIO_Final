package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Refuge;
import game.Player;

public class RefugeController {

	public void landOnRefuge(Player[] players, Player player, Refuge refuge, Field[] fields) {
		
		if (refuge.getName() == Language.field_Start) {
			GUI.displayChanceCard(player.getName() +"<br><br>"+ Language.ownable_landon1 + refuge.getName() + Language.field_StartText);
		} else if (refuge.getName() == Language.field_VisitJail) {
			GUI.displayChanceCard(player.getName() +"<br><br>"+ Language.field_VisitText);	
		} else if (refuge.getName() == Language.field_Parking) {
			GUI.displayChanceCard(player.getName() +"<br><br>"+ Language.ownable_landon1 + refuge.getName() +".<br><br>"+ Language.field_ParkingDescription);
		}

	}
}
