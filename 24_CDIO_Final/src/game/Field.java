/**
 * Field
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package game;

import java.awt.Color;

import desktop_fields.Brewery;
import desktop_fields.Shipping;
import desktop_fields.Street;
import desktop_resources.GUI;

public abstract class Field {

	protected String fieldname;
	protected static Field[] field;

	/**
	 * creates the fields for both the GUI and gamelogic to interact with.
	 * @return Field[]
	 */
	public static Field[] createFields() {

		field = new Field[] { 
				new Refuge ("Start",4000),
				new Territory ("Rødovrevej",1,1200,1000,50,250,750,2250,4000,6000),
				new Luck ("Prøv lykken"),
				new Territory ("Hvidovrevej",1,1200,1000,50,250,750,2250,4000,6000),
				new Tax ("Betal indkomst skat",4000, "special"),
				new Fleet ("Scanlines! Helsingør - Helsingborg"),
				new Territory ("Roskildevej",2, 2000, 1000,100,600,1800,5400,8000,11000),
				new Luck ("Prøv lykken"),
				new Territory ("Valby Langgade", 2, 2000, 1000,100,600,1800,5400,8000,11000),
				new Territory ("Allegade", 2, 2400, 1000,150,800,2000,6000,9000,12000),
				new Empty ("På besøg i fængsel"),
				new Territory ("Frederiksberg Alle", 3, 2800,2000,200,1000,3000,9000,12500,15000),
				new Labor ("Tuborg Squash"),
				new Territory ("Bulowsvej", 3, 2800,2000,200,1000,3000,9000,12500,15000),
				new Territory ("Gl. Kongevej", 3, 3200,2000,250,1250,3750,10000,14000,18000),
				new Fleet ("Mols-linien! En genvej i Danmark"),
				new Territory ("Bernstorffsvej", 4, 3600,2000,300,1400,4000,11000,15000,19000),
				new Luck ("Prøv lykken"),
				new Territory ("Hellerupvej", 4, 3600,2000,300,1400,4000,11000,15000,19000),
				new Territory ("Stranvejen",4, 4000,2000,350,1600,4400,12000,16000,20000),
				new Empty ("Parkering"),
				new Territory ("Trianglen",5,4400,3000,350,1800,5000,14000,17500,21000),
				new Luck ("Prøv lykken"),
				new Territory ("Østerbrogade",5,4400,3000,350,1800,5000,14000,17500,21000),
				new Territory ("Grønningen",5,4800,3000,400,2000,6000,15000,18500,22000),
				new Fleet ("Scanlines! Gedser - Rostock"),
				new Territory ("Bredgade",6,5200,3000,450,2200,6600,16000,19500,23000),
				new Territory ("Kgs. Nytorv",6,5200,3000,450,2200,6600,16000,19500,23000),
				new Labor ("Coca-Cola"),
				new Territory ("Østergade",6,5600,3000,500,2400,7200,17000,20500,24000),
				new Jail ("De fængsles"),
				new Territory ("Amagertorv",7,6000,4000,550,2600,7800,18000,22000,25000),
				new Territory ("Vimmelskaftet",7,6000,4000,550,2600,7800,18000,22000,25000),
				new Luck ("Prøv lykken"),
				new Territory ("Nygade",7,6400,4000,600,3000,9000,20000,24000,28000),
				new Fleet ("Scanlines! Rødby - Puttgarden"),
				new Luck ("Prøv lykken"),
				new Territory ("Frederiksberggade",8,7000,4000,700,3500,10000,22000,26000,30000),
				new Tax ("Ekstraordinær statsskat - betal",2000,"false"),
				new Territory ("Rådhuspladsen",8,8000,4000,1000,4000,12000,28000,34000,40000),
		};

		desktop_fields.Field list[] = new desktop_fields.Field[field.length];

		GUI.create(list);
		return field;
	}

	/**
	 * This method is used when a player lands on a field. landOnField defines what happens when a player lands on a field.
	 * @param
	 */
	public abstract void landOnField(Player player);  

	/*
	 * This method will sell all player assets
	 */
	public void sellAllFields(Player player) {

		for (int i = 0; i < field.length; i++) {

			if (field[i] instanceof Territory) {
				Territory territory = (Territory) field[i];
				if (territory.fieldowned && territory.fieldowner.equals(player)) {
					player.giveMoney(territory.fieldprice);
					territory.fieldowned = false;
					territory.fieldowner = null;
					player.setAssets(-territory.fieldprice);
					GUI.removeOwner(i+1);
					GUI.setBalance(player.getName(), player.getMoney());
				}
			}

			if (field[i] instanceof Labor) {
				Labor labor = (Labor) field[i];
				if (labor.fieldowned && labor.fieldowner.equals(player)) {
					player.giveMoney(labor.fieldprice);
					labor.fieldowned = false;
					labor.fieldowner = null;
					player.setAssets(-labor.fieldprice);
					GUI.removeOwner(i+1);
					GUI.setBalance(player.getName(), player.getMoney());
				}
			}

			if (field[i] instanceof Fleet) {
				Fleet fleet = (Fleet) field[i];
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
	public String getName() {
		return fieldname;
	}

	public Field(String name) {
		this.fieldname = name;
	}

	public static int getNumberOfFields() {
		return field.length;
	}
	/**
	 * Method gets the rent of the field.
	 * @return rentoffield
	 */
	public abstract int getRent();

	/**
	 * Method gets the price of the field.
	 * @return Priceoffield
	 */
	public abstract int getPrice();


}