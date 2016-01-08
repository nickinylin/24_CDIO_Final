package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Ownable;
import fields.Territory;
import game.Player;

public class TerritoryController {

	public void landOnTerritory(Player[] players, Player player, Territory territory, Field[] fields) {

		if (territory.fieldowned) {

			// Hvem ejer feltet?
			if (territory.fieldowner.equals(player)) {

				// Player ejer det selv
				int numberofgroupfields = 0;
				int numberofownedfields = 0;
				int i = 0;

				Territory[] ownedfields = new Territory[3];

				for (Field f : fields) {
					if (f instanceof Territory) {
						Territory t = (Territory) f;

						if (t.getFieldGroup() == ((Territory) territory).getFieldGroup()) {
							numberofgroupfields++;
							if (player.equals(t.getOwner())) {
								ownedfields[i++] = t;
								numberofownedfields++;
							}
						}
					}
				}

				if (numberofgroupfields == numberofownedfields) {

					boolean buybuilding = GUI.getUserLeftButtonPressed(""+player.getName()+" vil du købe huse eller hoteller på dine grunde?", "Ja", "Nej");

					if (buybuilding) {
						String buy = GUI.getUserSelection(""+player.getName()+" vælg hvilke grunde du vil købe hus på", ""+ownedfields[0].getName()+"", ""+ownedfields[1].getName()+"", ""+ownedfields[2].getName()+"");
					}

				}

			} else {

				((Ownable) territory).payRent(player, fields);
				GUI.setBalance(player.getName(), player.getMoney());
			}

		} else { // The Territory field was not owned
			final String BTN1 = "Buy field";
			final String BTN2 = "Sælg felt (Spillere)";
			final String BTN3 = "Sælg felt (Bank)";
			final String BTN4 = "pantsæt";
			final String BTN5 = "Afslut";
			String button = GUI.getUserSelection("message", BTN1, BTN2, BTN3, BTN4, BTN5);

			switch(button) {
			case BTN1:
				((Territory) territory).buyField(player, fields);
				GUI.setBalance(player.getName(), player.getMoney());
				territory.updateFieldGroup(player, territory, fields);
				break;
			case BTN2:
				((Territory) territory).sellFieldToPlayer(players, player, ((Territory) territory), fields);
				GUI.setBalance(player.getName(), player.getMoney());
				territory.updateFieldGroup(player, territory, fields);
				break;
			case BTN3:
				((Territory) territory).sellFieldToBank(player, ((Territory) territory), fields);
				GUI.setBalance(player.getName(), player.getMoney());
				territory.updateFieldGroup(player, territory, fields);
				break;
			case BTN4:
				((Territory) territory).setPawned(true);
				GUI.setBalance(player.getName(), player.getMoney());
				territory.updateFieldGroup(player, territory, fields);
			default:
			}

		}

	}
}
