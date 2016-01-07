/**
 * Field
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package fields;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;

import desktop_fields.Brewery;
import desktop_fields.Chance;
import desktop_fields.Shipping;
import desktop_fields.Street;
import desktop_resources.GUI;
import game.Player;

public abstract class Field {

	protected String fieldname;
	protected static Field[] fields;

	public Field(String name) {
		fieldname = name;
	}
	

	/*
	 * This method will sell all player assets
	 */
	public void sellAllFields(Player player) {

		for (int i = 0; i < fields.length; i++) {

			if (fields[i] instanceof Territory) {
				Territory territory = (Territory) fields[i];
				if (territory.fieldowned && territory.fieldowner.equals(player)) {
					player.giveMoney(territory.fieldprice);
					territory.fieldowned = false;
					territory.fieldowner = null;
					player.setAssets(-territory.fieldprice);
					GUI.removeOwner(i+1);
					GUI.setBalance(player.getName(), player.getMoney());
				}
			}

			if (fields[i] instanceof Labor) {
				Labor labor = (Labor) fields[i];
				if (labor.fieldowned && labor.fieldowner.equals(player)) {
					player.giveMoney(labor.fieldprice);
					labor.fieldowned = false;
					labor.fieldowner = null;
					player.setAssets(-labor.fieldprice);
					GUI.removeOwner(i+1);
					GUI.setBalance(player.getName(), player.getMoney());
				}
			}

			if (fields[i] instanceof Fleet) {
				Fleet fleet = (Fleet) fields[i];
				if (fleet.fieldowned && fleet.fieldowner.equals(player)) {
					player.giveMoney(fleet.fieldprice);
					fleet.fieldowned = false;
					fleet.fieldowner = null;
					player.setAssets(-fleet.fieldprice);
					GUI.removeOwner(i+1);
					GUI.setBalance(player.getName(), player.getMoney());
				}
			}


		}

	} 
	/**
	 * Method returns the name of the field.
	 * @return fieldName
	 */

	public final String getName() {
		return fieldname;
	}


	public static int getNumberOfFields() {
		return fields.length;
	}

	public void setName(String name) {
		this.fieldname = name;
	}

}