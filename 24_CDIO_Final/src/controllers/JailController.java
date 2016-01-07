package controllers;

import game.Player;

public class JailController {

	public void jail(Player player) {
		player.setJail(true);;
		if (player.getPlayerPosition()>10)
		player.payMoney(4000);
		player.setMove(10);
		
	}
}
