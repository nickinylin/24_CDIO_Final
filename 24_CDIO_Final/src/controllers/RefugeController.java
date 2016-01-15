package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Refuge;
import game.Player;

public class RefugeController {

	public void landOnRefuge(Player[] players, Player player, Refuge refuge, Field[] fields) {
		GUI.displayChanceCard(player.getName() +"<br><br>"+ Language.ownable_landon1 + refuge.getName() +".");
	}
}
