/**
 * Field
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package fields;

import desktop_resources.GUI;
import game.Player;
import setup.Setup;


public abstract class Field {

	protected String fieldname;

	public Field(String name) {
		fieldname = name;
	}
	
	
	

	/**
	 * Method returns the name of the field.
	 * @return fieldName
	 */

	public final String getName() {
		return fieldname;
	}

	public void setName(String name) {
		this.fieldname = name;
	}


}