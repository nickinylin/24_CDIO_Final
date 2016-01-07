/**
 * Field
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package fields;


public abstract class Field {

	protected String fieldname;
	protected static Field[] fields;

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

	public static int getNumberOfFields() {
		return fields.length;
	}

	public void setName(String name) {
		this.fieldname = name;
	}


}