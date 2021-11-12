
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
	public boolean removeItem(AlaCarte item) {
		int i = 0;
		while (i != items.size()) {
			if (items.get(i) == item) {
				items.remove(i);
				return true;
			}
			i++;
		}
		return false;
	}

	/**
	 * returns a list of all alacarte item's id in this promotion
	 * 
	 * @return list of all alacarte item's id in this promotion
	 */
	public ArrayList<String> getIDList() {
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
		System.out.printf(">> Items in %s promotion: \n", this.getName());
		System.out.println("ID          NAME");
		for (int i = 0; i < items.size(); i++) {
			System.out.printf("%-3s %-30s \n", items.get(i).getId(), items.get(i).getName());
		}
	}

	/**
	 * check if current object is a alacarte
	 */
	@Override
	public boolean isAlaCarte() {
		return false;
	}

}