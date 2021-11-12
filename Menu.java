import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Menu the class that manages the menu items to be served in the restaurant
 * 
 * @author Rui Xiang
 * @version 1.0
 * @since 2021-11-07
 */

public class Menu {
	/**
	 * An array list of Menu item object containing ala carte to be referenced in
	 * the restaurant.
	 */
	ArrayList<MenuItem> alaCarteItems;
	/**
	 * An array list of Menu item object containing promotional items to be
	 * referenced in the restaurant.
	 */
	ArrayList<MenuItem> promotionItems;

	/**
	 * A counter used to generate unique id for new alacarte items created
	 */
	int alaCarteCounter = 1;
	/**
	 * A counter used to generate unique id for new promotional items created
	 */
	int promotionCounter = 1;

	/**
	 * 
	 * Creates the Menu in the restaurant.
	 */
	public Menu() {
		alaCarteItems = new ArrayList<MenuItem>();
		promotionItems = new ArrayList<MenuItem>();
		AlaCarte var1;
		Promotion promo;
		Type type;

		System.out.println("Reading menu data from alacarte.csv ...");
		File file = new File("alacarte.csv");
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String data = sc.nextLine();
				String[] temp = data.split(",");

				if (temp[4].equals("1")) {
					type = Type.MAIN_COURSE;
				} else if (temp[4].equals("2")) {
					type = Type.SIDE;
				} else if (temp[4].equals("3")) {
					type = Type.DRINK;
				} else {
					type = Type.DESSERT;
				}
				var1 = new AlaCarte(temp[1], temp[2], Float.parseFloat(temp[3]), type, temp[0]);
				alaCarteItems.add(var1);
				alaCarteCounter++;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Reading menu data from promotion.csv ...");
		File file2 = new File("promotion.csv");
		try {
			Scanner sc = new Scanner(file2);
			while (sc.hasNextLine()) {
				String data = sc.nextLine();
				String[] temp = data.split(",");

				promo = new Promotion(temp[1], temp[2], Float.parseFloat(temp[4]), temp[0]);

				String[] promo_item_ids = temp[3].split("-");

				for (int i = 0; i < promo_item_ids.length; i++) {
					AlaCarte tempItem = (AlaCarte) getMenuItem(promo_item_ids[i]);
					promo.addItem(tempItem);
				}
				promotionItems.add(promo);
				promotionCounter++;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Creates Ala carte objects in the restaurant.
	 * 
	 * @param name  The name of the alacarte item.
	 * @param desc  The description of the alacarte item.
	 * @param price The price of the alacarte item.
	 * @param type  The type of the alacarte item.
	 * @return The alacarte object created.
	 */
	public AlaCarte createAlaCarte(String name, String desc, Float price, Type type) {
		AlaCarte item = new AlaCarte(name, desc, price, type, Integer.toString(alaCarteCounter));
		alaCarteItems.add(item);
		alaCarteCounter++;
		return item;

	}

	/**
	 * Creates Promotion objects in the restaurant.
	 * 
	 * @param name  The name of the promotion item.
	 * @param desc  The description of the promotion item.
	 * @param price The price of the promotion item.
	 * @return The Promotion object created.
	 */
	public Promotion createPromotion(String name, String desc, Float price) {
		Promotion promo = new Promotion(name, desc, price, Integer.toString(promotionCounter + 100));
		promotionItems.add(promo);
		promotionCounter++;
		return promo;
	}

	/**
	 * remove the menu item referenced by menuItemID
	 * 
	 * @param menuItemId The unique reference string to the menu item
	 */
	public void removeMenuItem(String menuItemId) {
		int found = -1;
		int menuItemId_integer = Integer.parseInt(menuItemId);
		if (menuItemId_integer < 100) {

			for (int i = 0; i < promotionItems.size(); i++) {
				Promotion promoSet = (Promotion) promotionItems.get(i);
				ArrayList<String> temp = promoSet.getIDList();
				for (int j = 0; j < temp.size(); j++) {
					if (temp.contains(menuItemId)) {
						System.out.printf("This item exists in a Promotion!\nPlease remove it from %s first.\n",
								promoSet.getName());
						return;
					}
				}
			}
			for (int i = 0; i < alaCarteItems.size(); i++) {
				if (alaCarteItems.get(i).getId().equals(menuItemId))
					found = i;
			}
			if (found != -1) {
				alaCarteItems.remove(found);
				System.out.println("Item removed successfully");
			}

		} else {

			for (int i = 0; i < promotionItems.size(); i++) {
				if (promotionItems.get(i).getId() == menuItemId) {
					found = i;
				}
			}
			if (found != -1) {
				promotionItems.remove(found);
				System.out.println("Remove is Successful");
			}
		}
	};

	/**
	 * Checks if the given menu item referenced by the menuItemID exists.
	 * 
	 * @param menuItemID The unique reference string to the menu item.
	 * @return The boolean if menu item exist.
	 */
	public boolean isValidID(String menuItemID) {
		for (int i = 0; i < alaCarteItems.size(); i++) {
			if (alaCarteItems.get(i).getId().equals(menuItemID))
				return true;
		}
		for (int i = 0; i < promotionItems.size(); i++) {
			if (promotionItems.get(i).getId().equals(menuItemID)) {
				return true;
			}
		}
		return false;
	};

	/**
	 * Returns the given menu item referenced by the menuItemID.
	 * 
	 * @param menuItemID The unique reference string to the menu item.
	 * @return The menu item referenced by the menuItemID.
	 */
	public MenuItem getMenuItem(String menuItemID) {
		for (int i = 0; i < alaCarteItems.size(); i++) {
			if (alaCarteItems.get(i).getId().equals(menuItemID))
				return alaCarteItems.get(i);
		}
		for (int i = 0; i < promotionItems.size(); i++) {
			if (promotionItems.get(i).getId().equals(menuItemID)) {
				return promotionItems.get(i);
			}
		}
		return null;

	}

	/**
	 * Print all the alacarte item that exist in the menu.
	 */
	public void displayAlaCarte() {
		System.out.println(">> Ala Carte Items on Menu:");
		AlaCarte temp;
		System.out.println("ID          NAME                   PRICE");
		for (int i = 0; i < alaCarteItems.size(); i++) {

			temp = (AlaCarte) alaCarteItems.get(i);
			if (temp.getType() == Type.MAIN_COURSE) {
				System.out.printf("%-3s %-30s %.2f", temp.getId(), temp.getName(), temp.getPrice());
				System.out.println("");
			}
		}
		for (int i = 0; i < alaCarteItems.size(); i++) {

			temp = (AlaCarte) alaCarteItems.get(i);
			if (temp.getType() == Type.SIDE) {
				System.out.printf("%-3s %-30s %.2f", temp.getId(), temp.getName(), temp.getPrice());
				System.out.println("");
			}
		}
		for (int i = 0; i < alaCarteItems.size(); i++) {

			temp = (AlaCarte) alaCarteItems.get(i);
			if (temp.getType() == Type.DRINK) {
				System.out.printf("%-3s %-30s %.2f", temp.getId(), temp.getName(), temp.getPrice());
				System.out.println("");
			}
		}
		for (int i = 0; i < alaCarteItems.size(); i++) {

			temp = (AlaCarte) alaCarteItems.get(i);
			if (temp.getType() == Type.DESSERT) {
				System.out.printf("%-3s %-30s %.2f", temp.getId(), temp.getName(), temp.getPrice());
				System.out.println("");
			}
		}
		return;
	}

	/**
	 * Prints out all the Promotion that exist in the restaurant.
	 */
	public void displayPromotion() {
		System.out.println(">> Promotional Set Packages on Menu:");
		for (int i = 0; i < promotionItems.size(); i++) {
			Promotion temp = (Promotion) promotionItems.get(i);
			System.out.printf("%-3s %-30s %.2f\n", temp.getId(), temp.getName(), temp.getPrice());
		}
	}

	/**
	 * Print out all the menu item that exist in the restaurant.
	 */
	public void displayMenu() {
		displayAlaCarte();
		displayPromotion();
	}
}