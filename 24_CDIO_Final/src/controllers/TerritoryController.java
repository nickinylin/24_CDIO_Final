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

				((Territory) territory).payRent(player, fields);
				GUI.setBalance(player.getName(), player.getMoney());
			}

		} else { // The Territory field was not owned
			int count = 0;
			String[] tempmenu = new String[5];
			boolean ownsfields = false;
			
			final String BTN1 = "Buy field";
			final String BTN2 = "Sælg felt";
			final String BTN3 = "Pantsæt";
			final String BTN4 = "Afslut";
			
			tempmenu[count++] = BTN1;
			
			for (Field f : fields) {
				if (f instanceof Territory) {
					Territory t = (Territory) f;

					if (t.getOwner() == player) {
						ownsfields = true;
					}
				}
			}

			if (ownsfields) {
				tempmenu[count++] = BTN2;
				tempmenu[count++] = BTN3;
			}

			tempmenu[count++] = BTN4;

			String[] menu = new String[count];
			
			for (int i = 0; i < menu.length; i++) {
				menu[i] = tempmenu[i];
			}
			
			String button = GUI.getUserButtonPressed(player.getName(), menu);

			switch(button) {
			case BTN1:
				((Territory) territory).buyField(player, fields);
				GUI.setBalance(player.getName(), player.getMoney());
				territory.updateFieldGroup(player, territory, fields);
				break;
			case BTN2:
				
				//TODO valg af felt
				String sellto = GUI.getUserButtonPressed(player.getName(), "Sælg felt til Spillere", "Sælg felt til Bank", "Fortryd");
				if ("Sælg felt til Bank" == sellto) {
					((Territory) territory).sellFieldToPlayer(players, player, ((Territory) territory), fields);
					GUI.setBalance(player.getName(), player.getMoney());
					territory.updateFieldGroup(player, territory, fields);
				} else if ("Sælg felt til Bank" == sellto){
					((Territory) territory).sellFieldToBank(player, ((Territory) territory), fields);
					GUI.setBalance(player.getName(), player.getMoney());
					territory.updateFieldGroup(player, territory, fields);
				}
				break;
			case BTN3:
				((Territory) territory).setPawned(true);
				GUI.setBalance(player.getName(), player.getMoney());
				territory.updateFieldGroup(player, territory, fields);
				break;
			case BTN4:
				((Territory) territory).setPawned(false);
				GUI.setBalance(player.getName(), player.getMoney());
				territory.updateFieldGroup(player, territory, fields);
				break;
			default:
			}

		}

	}
}
