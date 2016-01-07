package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Fleet;
import fields.Ownable;
import game.Player;

public class FleetController {

	public void landOnFleet(Player player, Fleet fleet, Field[] fields) {

		if (fleet.fieldowned) {

			if (fleet.fieldowner.equals(player) == false) {

				fleet.payRent(player, fields);

			}

		} else {

			boolean buyfleet = GUI.getUserLeftButtonPressed(""+player.getName()+" du er landet på "+fleet.getName()+", vil du købe flåden?", "Ja", "Nej");

			if (buyfleet) {
				fleet.buyField(player, fields);
			}

		}

	}
	
    public void buyFleet(Player player, Field[] fields){
        player.payMoney(fields.fieldprice);
        player.setAssets(fieldprice);
        fieldowned = true;
        fieldowner = player;
        // TODO Bør flyttes til alle controlers
        // Kald direkte til GUI fra entities er et problem
        GUI.setOwner(player.getPlayerPosition(), player.getName());
        GUI.setBalance(player.getName(), player.getMoney());
        GUI.setSubText(player.getPlayerPosition(), "Leje: "+getRent(fields)+"");
    }
}
