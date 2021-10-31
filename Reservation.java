import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Reservation {

	private LocalDateTime datetime;
	private int pax;
	private Customer customer;
	private int tableNumber;
	
	public Reservation(String dateTime, int pax, Customer customer, int tableNumber){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		this.datetime = LocalDateTime.parse(dateTime, formatter);
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
	public void setDatetime(String datetime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		this.datetime = LocalDateTime.parse(datetime, formatter);
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