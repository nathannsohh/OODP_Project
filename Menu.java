
/**
 * Menu class that does creation, deletion, validation of menu items
 * has the following 
 * @
 * @
 */

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Menu {

	//
	ArrayList<MenuItem> alaCarteItems;
	ArrayList<MenuItem> promotionItems;

	int alaCarteCounter = 0;
	int promotionCounter = 0;
	// private Map<String, MenuItem> menuItems;

	public Menu() {
		alaCarteItems = new ArrayList<MenuItem>();
		promotionItems = new ArrayList<MenuItem>();
		AlaCarte var1;
		Promotion promo;
		Type type;

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
				} else {
					type = Type.MAIN_COURSE;
				}
				var1 = new AlaCarte(temp[1], temp[2], Float.parseFloat(temp[3]), type, temp[0]);
				alaCarteItems.add(var1);
				alaCarteCounter++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		File file2 = new File("promotion.csv");
		try {
			Scanner sc = new Scanner(file2);
			while (sc.hasNextLine()) {
				String data = sc.nextLine();
				String[] temp = data.split(",");

				promo = new Promotion(temp[1], temp[2], Float.parseFloat(temp[4]), temp[0]);

				String[] promo_item_ids = temp[3].split("-");

				// System.out.print(promo_item_ids[1]);
				for (int i = 0; i < promo_item_ids.length; i++) {
					AlaCarte tempItem = (AlaCarte) getMenuItem(promo_item_ids[i]);
					promo.addItem(tempItem);
				}
				promotionItems.add(promo);
				promotionCounter++;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void removeMenuItem(String menuItemId) {
		int found = -1;
		int menuItemId_integer = Integer.parseInt(menuItemId);
		if (menuItemId_integer < 100) {

			for (int i = 0; i < promotionItems.size(); i++) {
				Promotion promoSet = (Promotion) promotionItems.get(i);
				ArrayList<String> temp = promoSet.findElements();
				for (int j = 0; j < temp.size(); j++) {
					if (temp.contains(menuItemId)) {
						System.out.println("This item Exist in a Promotion!\nPlease remove the Promotion first");
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
				System.out.println("Removed Successful");
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

	public AlaCarte createAlaCarte(String name, String desc, Float price, Type type) {
		// String name, String desc, float price, Type type
		AlaCarte item = new AlaCarte(name, desc, price, type, Integer.toString(alaCarteCounter));
		alaCarteCounter++;
		return item;

	}

	public Promotion createPromotion(String name, String desc, Float price) {
		Promotion promo = new Promotion(name, desc, price, Integer.toString(promotionCounter + 100));
		promotionCounter++;
		return promo;
	}

	public void printAlaCartes() {
		AlaCarte temp;
		System.out.println("ID          NAME    PRICE");
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
				// System.out.println("ID NAME PRICE");
				System.out.printf("%-3s %-30s %.2f", temp.getId(), temp.getName(), temp.getPrice());
				System.out.println("");
			}
		}
		for (int i = 0; i < alaCarteItems.size(); i++) {

			temp = (AlaCarte) alaCarteItems.get(i);
			if (temp.getType() == Type.DRINK) {
				// System.out.println("ID NAME PRICE");
				System.out.printf("%-3s %-30s %.2f", temp.getId(), temp.getName(), temp.getPrice());
				System.out.println("");
			}
		}
		return;
	}

	public void displayMenu() {
		printAlaCartes();
		for (int i = 0; i < promotionItems.size(); i++) {
			Promotion temp = (Promotion) promotionItems.get(i);
			System.out.printf("%-3s %-30s %.2f\n", temp.getId(), temp.getName(), temp.getPrice());
		}
	}

	public void displayPromotion() {
		for (int i = 0; i < promotionItems.size(); i++) {
			Promotion temp = (Promotion) promotionItems.get(i);
			System.out.printf("%-3s %-30s %.2f\n", temp.getId(), temp.getName(), temp.getPrice());
		}

	}

	public static void main(String[] args) {
		// Menu m = new Menu();
		// m.displayMenu();
		// m.createNewMenuItem();
		// // m.removeMenuItem("6");
		// m.displayMenu();
		// m.createNewMenuItem();
		// // System.out.println("\n");

		// for (int i = 0; i < m.promotionItems.size(); i++) {
		// MenuItem temp = (Promotion) m.promotionItems.get(i);
		// System.out.println(temp.getName());
		// Promotion temp2 = (Promotion) m.promotionItems.get(i);
		// temp2.displayPromoItems();
		// // temp.;

		// }
		// temp.displayPromoItems();
		// m.displayMenu();

	}

}