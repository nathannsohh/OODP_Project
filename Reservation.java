import java.time.*;

public class Reservation {

	private LocalDateTime datetime;
	private int pax;
	private Customer customer;
	private int tableNumber;
	
	public Reservation(LocalDateTime dateTime, int pax, Customer customer, int tableNumber){
		this.datetime = dateTime;
		this.pax = pax;
		this.customer = customer;
		this.tableNumber = tableNumber;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}
	public int getPax() {
		return pax;
	}
	public Customer getCustomer() {
		return customer;
	}
	public int getTableNumber() {
		return tableNumber;
	}
	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}
	public void setPax(int pax) {
		this.pax = pax;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
}