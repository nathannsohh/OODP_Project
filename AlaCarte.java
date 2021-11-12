/**
 * AlaCarte class that represents the alacarte menu items in the menu.
 * 
 * @author Rui Xiang
 * @version 1.0
 * @since 2021-11-07
 */

public class AlaCarte extends MenuItem {
	/**
	 * The type of food of this alacarte item.
	 */
	private Type type;

	/**
	 * Constructor of Alacarte object.
	 * 
	 * @param name  The name of the alacarte object.
	 * @param desc  The description of the alacarte object.
	 * @param price The price of the menu item.
	 * @param type  The type of alacarte object.
	 * @param id    The unique reference string of the alacarte object.
	 */
	public AlaCarte(String name, String desc, float price, Type type, String id) {
		super(name, desc, price, id);
		this.type = type;
	}

	/**
	 * Gets the type of alacarte object.
	 * 
	 * @return The type of alacarte object.
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Sets the type of alacarte object.
	 * 
	 * @param type The type of alacarte object.
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Returns true.
	 */
	@Override
	public boolean isAlaCarte() {
		return true;
	}

}