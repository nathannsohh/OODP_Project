/**
 * RestaurantApp is the application class for {@link Restaurant}
 * @author Nicole
 * @version 1.00
 * @since 11/05/2021
 */
public class RRPSSapp {
	/**
	 * Creates a Restaurant using test data, 
	 * and calls {@link Restaurant#runRRPSS}.
	 */
	public static void main(String[] args) 
	{
		Restaurant r = new Restaurant("/staffData.txt", "/menuData.txt", "/tableData.txt");
		r.runRRPSS();
	}
}