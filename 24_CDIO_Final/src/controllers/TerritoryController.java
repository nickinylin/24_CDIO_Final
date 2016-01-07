package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Ownable;
import fields.Territory;
import game.Player;

public class TerritoryController {

	public void landOnTerritory(Player player, Territory territory, Field[] fields) {

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

				boolean buyfield = GUI.getUserLeftButtonPressed(""+player.getName()+" du er landet på "+((Territory) territory).getName()+", vil du købe grunden?", "Ja", "Nej");

				if (buyfield) {

					((Territory) territory).buyField(player, fields);
			        GUI.setBalance(player.getName(), player.getMoney());
			        // TODO upgrade fields
			        territory.updateFieldGroup(player, territory, fields);
				}

			}

		}
	}
