enum Type {
	DRINK, MAIN_COURSE, SIDE, DESSERT;
};

abstract public class MenuItem {

	private String name;
	private String description;
	private float price;
	private String id;

	public MenuItem(String name, String desc, float price, String id) {
		this.name = name;
		this.description = desc;
		this.price = price;
		this.id = id;
	};

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	abstract public boolean checkAlacarte();
}