public class Table {

	private int tableNumber;
	private int capacity;
	private boolean available;

	public Table(int tableNumber, int capacity, boolean available){
		this.tableNumber = tableNumber;
		this.capacity = capacity;
		this.available = available;
	}

	public int getTableNumber() {
		return tableNumber;
	}
	public int getCapacity() {
		return capacity;
	}
	public boolean getAvailable() {
		return available;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
}