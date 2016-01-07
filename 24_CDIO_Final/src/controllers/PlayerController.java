package controllers;

import desktop_resources.GUI;
import fields.Field;
import game.Bank;
import game.Player;

public class PlayerController {

	private static Bank bank;

	/**
	 * Method moves the player in the GUI
	 * @param player
	 * @param move
	 */

	public static void movePlayer(Player player, int move, Field[] fields) {

		int nextposition = player.getPlayerPosition() + move;
		int maxmove = nextposition%fields.length;

		if (nextposition >= fields.length) {

			for (int i = 1; i <= move; i++) {

				try {
					Thread.sleep(100);
					if (player.getPlayerPosition() + i >= fields.length) {
						int x = 1;
						GUI.removeCar(i, player.getName());
						GUI.removeCar(x, player.getName());
						GUI.setCar(x, player.getName());
						x++;
					} else {
						GUI.removeCar(player.getMove()+i-1, player.getName());
						GUI.setCar(+i, player.getName());
					}
				}
				catch(InterruptedException E) {
					Thread.currentThread().interrupt();
				}

			}

			bank.giveMoney(4000);
			GUI.setBalance(player.getName(), bank.getMoney());

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
			player.setPlayerPosistion(move);
		}

	}


}
