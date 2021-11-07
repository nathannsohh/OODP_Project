import java.util.ArrayList;
import java.time.*;
import java.time.format.*;

/**
 * ReservationManager is the class that manages all methods related to reservations in the restaurant, such as creating reservations and checking for existing reservations made.
 * @author Nathan
 * @version 1.0
 * @since 2021-11-07
 */

public class ReservationManager {

	/**
	 * An arraylist of Reservations made
	 */
	private ArrayList<Reservation> reservations;

	/** 
	 * Creates an empty arraylist of reservations
	*/
	public ReservationManager(){
		reservations = new ArrayList<Reservation>();
	}

	/**
	 * Gets the arraylist of reservations made
	 * @return the arraylist of reservations made
	 */
	public ArrayList<Reservation> getReservationList(){
		return reservations;
	}

	/**
	 * Creates a reservation for a customer and adds it into the arraylist of reservations made
	 * Reservations made have to be valid before adding to the arraylist of reservations
	 * @param dateTime the date and time of the reservation
	 * @param pax the number of pax for the reservation
	 * @param customer the customer who made the reservation
	 * @param tableNumber the assigned table number for the reservation
	 */
	public void createReservation(LocalDateTime dateTime, int pax, Customer customer, int tableNumber) {
		reservations.add(new Reservation(dateTime, pax, customer, tableNumber));
	}
	
	/**
	 * Checks whether the date and time for the reservation is within opening hours,
	 * whether the reservation is made within 14 days in advance,
	 * and whether the reservation is made for a date and time in the past
	 * @param datetime the date and time of the reservation
	 * @return the outcome of the check, where true is valid and false is invalid
	 */
	public boolean isValidDate(LocalDateTime datetime){
		LocalDateTime date = datetime;
		int hour = date.getHour();
		if(date.isAfter(LocalDateTime.now().plusDays(14))){
			System.out.println("Error: You can only reserve a table at most 14 days in advance.");
			return false;
		}
		else if(date.isBefore(LocalDateTime.now())){
			System.out.println("Error: Date inputted is in the past.");
			return false;
		}
		else if(hour < 11 || hour > 21){
			System.out.println("Error: Reservations can only be made within our opening hours of 11am to 9pm.");
			return false;
		}
		else return true;
	}

	/**
	 * Checks for an existing reservation made, 
	 * and displays the relevant reservation if it exists
	 * @param customer the name of the customer who made the reservation
	 * @param dateTime date and time of the reservation made
	 */
	public void checkReservation(String customer, LocalDateTime dateTime) { 
		int i = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		while(i != reservations.size()){
			if(reservations.get(i).getCustomer().getName().equals(customer) && reservations.get(i).getDatetime().isEqual(dateTime)){
				
				System.out.println("Reservation Details:");
				System.out.println("Reservation Name: " + reservations.get(i).getCustomer().getName());
				System.out.println("Date and Time: " + reservations.get(i).getDatetime().format(formatter));
				System.out.println("Pax: " + reservations.get(i).getPax());
				System.out.println("Table No.: " + reservations.get(i).getTableNumber());
				break;
			}
			i++;
		}
		if(i == reservations.size()){
			System.out.println("No such reservation found!");
		}

	}

	/**
	 * Removes an existing reservation if the customer cancels the reservation
	 * @param customer name of the customer who made the reservation
	 * @param dateTime date and time of the reservation made
	 * @return the outcome of the removal, where true if the existing reservation was removed
	 * 		   and false if no such reservation was made
	 */
	public boolean removeReservation(String customer, LocalDateTime dateTime) {
		int i = 0;
		while(i != reservations.size()){
			if(reservations.get(i).getCustomer().getName().equals(customer) && reservations.get(i).getDatetime().isEqual(dateTime)){
				reservations.remove(i);
				return true;
			}
			i++;
		}
		return false;
		
	}

	/**
	 * Deletes reservations that expires from the arraylist of reservations if the customer 
	 * does not show up 15mins past their reservation time
	 * @param manager the class that manages the tables in the restaurant
	 */
	public void deleteInvalidReservations(TableManager manager) {
		int i = 0;
		while(i < reservations.size()){
			if(LocalDateTime.now().isAfter(reservations.get(i).getDatetime().plusMinutes(15))){
				manager.setTableAvailability(reservations.get(i).getTableNumber(), true);
				reservations.remove(i);
			}
			else i++;
		}
	}

	
}