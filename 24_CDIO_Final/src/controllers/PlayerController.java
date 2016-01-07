package controllers;

import desktop_resources.GUI;
import fields.Field;
import game.Bank;
import game.Player;

public class PlayerController {

	private Bank bank;

	/**
	 * Method moves the player in the GUI
	 * @param player
	 * @param move
	 */

	public void movePlayer(Player player, int move, Field[] fields) {

		int nextposition = player.getPlayerPosition() + move;
		int maxmove = nextposition%fields.length;

		if (nextposition >= fields.length) {

			for (int i = 1; i < move; i++) {

				try {
					Thread.sleep(200);
					if (player.getPlayerPosition() + i >= fields.length) {
						int x = 1;
						GUI.removeCar(player.getMove()+i, player.getName());
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

			for (int i = 1; i <= move+1; i++) {

				try {
					Thread.sleep(200);
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
