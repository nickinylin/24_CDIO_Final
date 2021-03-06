/**
 * Field
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package fields;

public abstract class Field {

	protected String fieldname;

	public Field(String name) {
		fieldname = name;
	}
	

	/**
	 * Method returns the name of the field.
	 * @return fieldName
	 */
	public abstract String getFieldType();
	
	
	public String getName() {
		return fieldname;
	}

	
	public void setName(String name) {
		this.fieldname = name;
	}


}