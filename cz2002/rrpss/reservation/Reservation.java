package cz2002.rrpss.reservation;

import cz2002.rrpss.Customer;
import java.time.*;

/**
 * Represents a reservation made for the restaurant.
 * 
 * @author Nathan
 * @version 1.0
 * @since 2021-11-07
 */
public class Reservation {

	/**
	 * The date and time of the reservation.
	 */
	private LocalDateTime datetime;

	/**
	 * The number of people who will be coming for the reservation.
	 */
	private int pax;

	/**
	 * The customer who made the reservation.
	 */
	private Customer customer;

	/**
	 * The table number assigned for this reservation.
	 */
	private int tableNumber;

	/**
	 * Creates a reservation with the date and time, number of people, customer who
	 * made the reservation and the assigned table number
	 * 
	 * @param dateTime    The date and time of the reservation.
	 * @param pax         The number of people who will be coming for the
	 *                    reservation.
	 * @param customer    The customer who made the reservation.
	 * @param tableNumber The table number assigned for the reservation.
	 */
	public Reservation(LocalDateTime dateTime, int pax, Customer customer, int tableNumber) {
		this.datetime = dateTime;
		this.pax = pax;
		this.customer = customer;
		this.tableNumber = tableNumber;
	}

	/**
	 * Gets the date and time of the reservation.
	 * 
	 * @return date and time of the reservation.
	 */
	public LocalDateTime getDatetime() {
		return datetime;
	}

	/**
	 * Changes the date and time for the reservation.
	 * 
	 * @param datetime The new date and time for the reservation.
	 */
	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	/**
	 * Gets the number of pax for the reservation.
	 * 
	 * @return number of pax for the reservation.
	 */
	public int getPax() {
		return pax;
	}

	/**
	 * Changes the number of pax for the reservation.
	 * 
	 * @param pax The new number of pax for the reservation.
	 */
	public void setPax(int pax) {
		this.pax = pax;
	}

	/**
	 * Gets the Customer who made the reservation.
	 * 
	 * @return the customer who made the reservation.
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Changes the customer who made the reservation.
	 * 
	 * @param customer The new customer who made the reservation.
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Gets the table number assigned for this reservation.
	 * 
	 * @return the table number for the reservation.
	 */
	public int getTableNumber() {
		return tableNumber;
	}

	/**
	 * Changes the table number assigned to the reservation.
	 * 
	 * @param tableNumber The new assigned table number.
	 */
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
}