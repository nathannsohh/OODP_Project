/**
 * Represents a single table in the restaurant.
 * 
 * @author Nathan
 * @version 1.0
 * @since 2021-11-07
 */
public class Table {

	/**
	 * The table number of this table.
	 */
	private int tableNumber;

	/**
	 * The seating capacity of this table.
	 */
	private int capacity;

	/**
	 * The availability of this table.
	 */
	private boolean available;

	/**
	 * Creates a table with its table number, seating capacity and availability.
	 * 
	 * @param tableNumber The table number of this table.
	 * @param capacity    The capacity of this table.
	 * @param available   The availability of this table.
	 */
	public Table(int tableNumber, int capacity, boolean available) {
		this.tableNumber = tableNumber;
		this.capacity = capacity;
		this.available = available;
	}

	/**
	 * Gets the table number of this table.
	 * 
	 * @return table number of this table.
	 */
	public int getTableNumber() {
		return tableNumber;
	}

	/**
	 * Change the table number of this table.
	 * 
	 * @param tableNumber The new table number for this table.
	 */
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	/**
	 * Gets the capacity of this table.
	 * 
	 * @return the capacity of this table.
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Change the seating capacity of this table.
	 * 
	 * @param capacity The new seating capacity of this table.
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Gets the availability of this table.
	 * 
	 * @return the availability of this table.
	 */
	public boolean getAvailable() {
		return available;
	}

	/**
	 * Changes the availability of this table.
	 * 
	 * @param available The new avaiability of this table.
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
}