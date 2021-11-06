import java.util.ArrayList;

public class Promotion extends MenuItem {

	private ArrayList<AlaCarte> items;

	public Promotion(String name, String desc, float price, String id) {
		super(name, desc, price, id);
		this.items = new ArrayList<AlaCarte>();
	};

	public void addItem(AlaCarte item) {
		items.add(item);
	}

	public void removeItem(AlaCarte item) {
		if (!items.remove(item)) {
			System.out.println("Item does not exist in this promotion!");
		}
	}

	public void displayPromoItems() {
		System.out.println("inside display promoitems");
		for (int i = 0; i < items.size(); i++) {
			AlaCarte temp = items.get(i);
			System.out.println(temp.getName());
		}
	}

	public ArrayList<String> findElements() {
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < items.size(); i++) {
			temp.add(items.get(i).getId());
		}
		return temp;
	}

	@Override
	public boolean checkAlacarte() {
		return false;
	}

}