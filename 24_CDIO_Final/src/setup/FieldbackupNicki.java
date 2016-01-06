package setup;

public class FieldbackupNicki {
	
	/**
	 * Field
	 * @author Gruppe 24
	 * @version 04/01-2016
	 **/

		protected String fieldname;


		/**
		 * creates the fields for both the GUI and gamelogic to interact with.
		 * @return Field[]
		 */
		

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
		public String getName() {
			return fieldname;
		}

		public Field(String name) {
			this.fieldname = name;
		}

		public static int getNumberOfFields() {
			return fields.length;
		}



	}

}
