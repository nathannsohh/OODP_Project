import java.util.ArrayList;
import java.time.*;
import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;

/**
 * TableManager is the class that manages the tables and all methods related to the tables in the restaurant.
 * @author Nathan
 * @version 1.0
 * @since 2021-11-07
 */
public class TableManager {

	/**
	 * The list of tables in the restaurant.
	 */
	private Table[] tables;
	
	/**
	 * Creates the tables in the restaurant.
	 */
	public TableManager(){ 
		tables = new Table[10];
		String path = new File("").getAbsolutePath();
		String fileName = "\\tableData.txt";
		File file = new File(path + fileName);
		try {
			Scanner sc = new Scanner(file);
			int i = 0;
				while (sc.hasNextLine())
				{
					tables[i] = new Table(i+1, sc.nextInt(), true);
					i++;
				}
				sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Gets the list of all the tables in the restaurant.
	 * @return the list of tables in the restuarant.
	 */
	public Table[] getTables(){
		return tables;
	}

	/**
	 * Checks if a table is available for a specific date and time for a specific number of people based on the reservations that have been made.
	 * @param pax The size of the group dining in.
	 * @param datetime The date and time of the meal.
	 * @param manager The class that manages the reservations in the restaurant.
	 * @return the table number that is available, or -1 if there are no tables available at that time
	 */
	public int checkFutureAvailability(int pax, LocalDateTime datetime, ReservationManager manager) { 
		ArrayList<Reservation> reservationList = manager.getReservationList();
		ArrayList<Integer> tableNumList = new ArrayList<Integer>();
		for(int i = 0; i < reservationList.size(); i++){
			if(reservationList.get(i).getDatetime().isEqual(datetime)) {
				tableNumList.add(reservationList.get(i).getTableNumber());  // get list of tables that are reserved at datetime
			}
		}
		for(int i = 0; i < 10; i++){
			if(tableNumList.contains(i+1)){
				continue;
			}
			else {
				if(tables[i].getCapacity() >= pax) return tables[i].getTableNumber();
			}
		}
		return -1;
	}

	/**
	 * Checks if there is a table available currently for the number of pax wanting to eat at the restaurant
	 * @param pax The size of the group dining in.
	 * @return the table number of the available table, or -1 if there are no available tables currently.
	 */
	public int checkCurrentAvailability(int pax) { 
		for(int i = 0; i < 10; i++){
			if(tables[i].getCapacity() >= pax && tables[i].getAvailable() == true) return tables[i].getTableNumber();
		}
		return -1;
	}

	/**
	 * Checks if a table number exists in the restaurant.
	 * @param tableNumber The potential table number in the restaurant.
	 * @return the outcome of the check, where true if valid, or false if invalid.
	 */
	public boolean isValidTableNumber(int tableNumber) {
		if(tableNumber > 10 || tableNumber < 1) return false;
		else return true;
	}

	/**
	 * Changes the availability of a table in the restaurant.
	 * @param tableNumber The table number of the table who has its availabiliy changed.
	 * @param avail The new availability of the table. 
	 */
	public void setTableAvailability(int tableNumber, boolean avail){
		tables[tableNumber-1].setAvailable(avail);
	}

	/**
	 * Changes the availability of the reserved tables in the restaurant to reserved once the current time is a reservation timing.
	 * @param manager The class that manages the reservations in the restaurant.
	 */
	public void setReservedTablesOccupied(ReservationManager manager) { 
		for(int i = 0; i < manager.getReservationList().size(); i++){
			if(LocalDateTime.now().isAfter(manager.getReservationList().get(i).getDatetime()) && LocalDateTime.now().isBefore(manager.getReservationList().get(i).getDatetime().plusMinutes(15)))
				setTableAvailability(manager.getReservationList().get(i).getTableNumber(), false);
		}
	}
}

