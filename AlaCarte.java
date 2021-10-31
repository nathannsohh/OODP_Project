public class AlaCarte extends MenuItem {

	private String type;
	public AlaCarte(String name, String desc, float price, String type) {};
	public static boolean isValidType(String type);
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}