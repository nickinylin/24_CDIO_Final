package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Tax;
import game.Player;

public class TaxController {

	private MenuController menuController = new MenuController();

	public void payTax(Player[] players, Player player, Tax field, Field[] fields) {

		if (field.getSpecial().equals("special")) {

			GUI.displayChanceCard("<center>"+player.getName() + Language.tax_landon1);

			boolean boo = GUI.getUserLeftButtonPressed(player.getName(), Language.tax_10pct, Language.tax_pay + field.getRent());

			if (boo) {
				int payamount = player.getAssets()*10/100;

				player.payMoney(payamount);
				GUI.setBalance(player.getName(), player.getMoney());

			} else {

				player.payMoney(field.getRent());
				GUI.setBalance(player.getName(), player.getMoney());

			}

		} else { // There was no special tax option

			player.payMoney(field.getRent());
			GUI.setBalance(player.getName(), player.getMoney());

		}
	}
}
