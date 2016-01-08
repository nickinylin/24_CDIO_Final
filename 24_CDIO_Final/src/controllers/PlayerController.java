package controllers;

import desktop_resources.GUI;
import fields.Field;
import game.Player;

public class PlayerController {


	

	/**
	 * Method moves the player in the GUI
	 * @param player
	 * @param move
	 */

	public static void movePlayer(Player player, int move, Field[] fields) {

		
		int nextposition = player.getPlayerPosition() + move;
		int maxmove = (nextposition-1)%fields.length+1;

		if (nextposition >= fields.length) {

			for (int i = 1; i <= move; i++) {

				try {
					Thread.sleep(100);
					if (player.getPlayerPosition() + i > fields.length) {
			            int go = player.getPlayerPosition() + i;
			            int newmove = go%fields.length;
			            GUI.removeAllCars(player.getName());
			            GUI.setCar(newmove, player.getName());
					} else {
						GUI.removeAllCars(player.getName());
						GUI.setCar(player.getPlayerPosition()+i, player.getName());
					}
				}
				catch(InterruptedException E) {
					Thread.currentThread().interrupt();
				}

			}
			
			player.setPlayerPositionToField(maxmove);
			player.giveMoney(4000);
			GUI.setBalance(player.getName(), player.getMoney());

		} else {

			for (int i = 1; i <= move; i++) {

				try {
					Thread.sleep(100);
					GUI.removeCar(player.getPlayerPosition()+i-1, player.getName());
					GUI.setCar(player.getPlayerPosition()+i, player.getName());
				}
				catch(InterruptedException E) {
					Thread.currentThread().interrupt();
				}

			}
			player.setPlayerPosition(move);
		}

	}

}
