/**
 * an abstract declaration of a menu item in the menu
 * 
 * @author Rui Xiang
 * @version 1.0
 * @since 2021-11-07
 */

/**
 * The enumeration of possible type of food choices in the menu
 */
enum Type {
	DRINK, MAIN_COURSE, SIDE, DESSERT;
};

abstract public class MenuItem {
	/**
	 * Name of the menu item
	 */
	private String name;
	/**
	 * Description of the menu item
	 */
	private String description;
	/**
	 * Price of the menu item
	 */
	private float price;
	/**
	 * A unique reference of the menu item
	 */
	private String id;

	/**
	 * Constructor to the Menu Item class
	 * 
	 * @param name  name of the menu item
	 * @param desc  description of the menu item
	 * @param price Price of the menu item in float
	 * @param id    a unique reference string to the menu item
	 */
	public MenuItem(String name, String desc, float price, String id) {
		this.name = name;
		this.description = desc;
		this.price = price;
		this.id = id;
	};

	/**
	 * Gets the name of the menu item
	 * 
	 * @return name of the menu item
	 */
	public String getName() {
		return name;
	}

	/**
	 * Change the name of the menu item
	 * 
	 * @param name the new name of menu item
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description of the menu item
	 * 
	 * @return description of the menu item
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Change the description of the menu item
	 * 
	 * @param description the new description of menu item
	 */

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the price of the menu item in float
	 * 
	 * @return price of the menu item in float
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Change the price of the menu item
	 * 
	 * @param price the new price of the menu item
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Gets the unique reference string of the menu item
	 * 
	 * @return unique reference string of the menu item
	 */
	public String getId() {
		return id;
	}

	/**
	 * Change the unique reference string of the menu item
	 * 
	 * @param id the new unique reference string of menu item
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * check if the current class is a alacarte object or not
	 * 
	 * @return check if the current class is a alacarte object or not
	 */
	abstract public boolean isAlaCarte();
}