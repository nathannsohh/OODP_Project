
public class AlaCarte extends MenuItem {

	private Type type;

	public AlaCarte(String name, String desc, float price, Type type, String id) {
		super(name, desc, price, id);
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	@Override
	public void checkAlacarte() {
		System.out.println("Package");
	}

}