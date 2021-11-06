/**
 * RestaurantApp is the application class for {@link Restaurant}
 * @author Nicole
 * @version 1.0
 * @since 2021-11-06
 */
public class RestaurantApp {
	/**
	 * Creates a {@link Restaurant} using test data, 
	 * and calls {@link Restaurant#runRRPSS}.
	 */
	public static void main(String[] args) 
	{
		Restaurant r = new Restaurant();
		r.runRRPSS();
	}
}