package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Tax;
import game.Player;

public class TaxController {

	public void payTax(Player[] players, Player player, Tax field, Field[] fields) {

		if (field.getSpecial().equals("special")) {

			GUI.displayChanceCard("<center>"+player.getName() + Language.tax_landon1);

			boolean boo = GUI.getUserLeftButtonPressed(player.getName(), Language.tax_10pct, Language.tax_pay + field.getRent());

			if (boo) {
				int payamount = player.getAssets()*10/100;

				player.payMoney(payamount);
				GUI.setBalance(player.getName(), player.getMoney());
				GUI.displayChanceCard(player.getName() +"<br><br>"+ Language.tax_landon2 + Language.tax_10 + Language.tax_landon3 +"<br><br>"+ payamount);

			} else {

				player.payMoney(field.getRent());
				GUI.setBalance(player.getName(), player.getMoney());
				GUI.displayChanceCard(player.getName() +"<br><br>"+ Language.tax_landon2 +"4000");

			}

		} else { // There was no special tax option

			player.payMoney(field.getRent());
			GUI.setBalance(player.getName(), player.getMoney());
			GUI.displayChanceCard(player.getName() +"<br><br>"+ Language.tax_landon4);
		}
	}
}
