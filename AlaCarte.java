/**
 * @author Rui Xiang
 * @version 1.0
 * @since 2021-11-07
 */
public class AlaCarte extends MenuItem {

	private Type type;

	public AlaCarte(String name, String desc, float price, Type type, String id) {
		super(name, desc, price, id);
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public boolean isAlaCarte() {
		return true;
	}

}