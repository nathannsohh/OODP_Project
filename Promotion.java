
/**
 * A Promotional set of alacarte item thats also a menu item
 * @author Rui Xiang
 * @version 1.0
 * @since 2021-11-07
 */
import java.util.ArrayList;

public class Promotion extends MenuItem {

	/**
	 * a list of alacarte items
	 */
	private ArrayList<AlaCarte> items;

	/**
	 * Creates a table with its table number, seating capacity and availability.
	 * 
	 * @param name  name of the menu item
	 * @param desc  description of the menu item
	 * @param price price of the menu item of float type
	 * @param id    unqiue reference string of the menu item
	 */
	public Promotion(String name, String desc, float price, String id) {
		super(name, desc, price, id);
		this.items = new ArrayList<AlaCarte>();
	};

	/**
	 * add a new alacarte item to this promotional set
	 * 
	 * @param item the alacarte item to be added to the promotional set
	 */
	public void addItem(AlaCarte item) {
		items.add(item);
	}

	/**
	 * delete a new alacarte item to this promotional set
	 * 
	 * @param item the alacarte item to be added to the promotional set
	 */
	public void removeItem(AlaCarte item) {
		if (!items.remove(item)) {
			System.out.println("Item does not exist in this promotion!");
		}
	}

	/**
	 * returns a list of all alacarte item's id in this promotion
	 * 
	 * @return list of all alacarte item's id in this promotion
	 */
	public ArrayList<String> findElements() {
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < items.size(); i++) {
			temp.add(items.get(i).getId());
		}
		return temp;
	}

	/**
	 * print out all the alacarte items in the promotion
	 */
	public void displayPromotionItems() {
		// System.out.println("hello hehe");
		for (int i = 0; i < items.size(); i++) {
			System.out.println(items.get(i).getName());
		}
	}

	/**
	 * check if current objec is a alacarte
	 */
	@Override
	public boolean checkAlacarte() {
		return false;
	}

}