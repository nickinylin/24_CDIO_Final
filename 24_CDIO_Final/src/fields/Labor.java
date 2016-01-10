/**
 * Labor
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package fields;

import desktop_resources.GUI;
import game.Dice;
import game.Player;

public class Labor extends Ownable {

	Dice dice = new Dice();

	public Labor(String name) {
		super(name);
		super.fieldprice=3000;
		super.fieldowned=false;
	}

	@Override
	public int getRent(Player player, Field[] fields) {

		int numberofowned = 0;

		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];

			if (f instanceof Labor) {
				Labor labor = (Labor) f;

				if (fieldowner.equals(labor.fieldowner)) {
					numberofowned++;
				}

			}
		}

		if (numberofowned == 2) {
			int fieldrent = 200 * Dice.getSum();
			return fieldrent;
		} else {
			int fieldrent = 100 * Dice.getSum();
			return fieldrent;
		}

	}


	public int getPrice() {
		return super.fieldprice;
	}

	@Override
	public void buyField(Player player, Field[] fields) {
		player.payMoney(fieldprice);
		player.setAssets(fieldprice);
		fieldowned = true;
		fieldowner = player;
	}

	@Override
	public void updateFieldGroup(Player player, Field currentField, Field[] fields) {
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			if (f instanceof Labor) {
				Labor labor = (Labor) f;

				if (player.equals(((Labor) currentField).fieldowner) && player.equals(labor.fieldowner)) {
					GUI.setOwner(i+1, player.getName());
					GUI.setSubText(i+1, "Leje: "+ labor.getOwnedLabors(player, fields) + " x Terning");

				}
			}
		}

	}

	public int getOwnedLabors(Player player, Field[] fields) {
		int count = 0;
		for (Field f : fields) {
			if (f instanceof Labor) {
				Labor labor = (Labor) f;
				if (labor.fieldowned && player.equals(fieldowner)) {
					count++;
				}
			}
		}
		if (count == 2){
			int ownedLabors = 200;
			return ownedLabors;
		} else {
			int ownedLabors = 100;
			return ownedLabors;
		}
	}
}
