import java.util.ArrayList;
import java.time.*;
import java.time.format.*;

public class ReservationManager {

	private ArrayList<Reservation> reservations;


	public ReservationManager(){
		reservations = new ArrayList<Reservation>();
	}

	public ArrayList<Reservation> getReservationList(){
		return reservations;
	}

	public void createReservation(String dateTime, int pax, Customer customer, int tableNumber) {
		reservations.add(new Reservation(dateTime, pax, customer, tableNumber));
	}
	
	public boolean isvalidDate(String datetime){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime date = LocalDateTime.parse(datetime, formatter);
		int hour = date.getHour();
		if(date.isAfter(LocalDateTime.now().plusDays(14))){
			System.out.println("Error: You can only reserve a table at most 14 days in advance.");
			return false;
		}
		else if(hour < 11 || hour > 21){
			System.out.println("Error: Reservations can only be made within our opening hours of 11am to 9pm.");
			return false;
		}
		else return true;
	}

	public void checkReservation(Customer customer, LocalDateTime dateTime) {
		int i = 0;
		for(i = 0; i < reservations.size(); i++){
			if(reservations.get(i).getCustomer() == customer && reservations.get(i).getDatetime() == dateTime){
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
				System.out.println("Reservation Details:");
				System.out.println("Reservation Name: " + reservations.get(i).getCustomer().getName());
				System.out.println("Date and Time: " + reservations.get(i).getDatetime().format(formatter));
				System.out.println("Pax: " + reservations.get(i).getPax());
				System.out.println("Table No.: " + reservations.get(i).getTableNumber());
				break;
			}
		}
		if(i == reservations.size()){
			System.out.println("No such reservation found!");
		}

	}

	public boolean removeReservation(Customer customer, LocalDateTime dateTime) {
		int i = 0;
		while(i != reservations.size()){
			if(reservations.get(i).getCustomer() == customer && reservations.get(i).getDatetime() == dateTime){
				reservations.remove(i);
				return true;
			}
		}
		return false;
		
	}

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

	public void setReservedTablesOccupied(TableManager manager) {
		for(int i = 0; i < reservations.size(); i++){
			if(LocalDateTime.now().isAfter(reservations.get(i).getDatetime()) && LocalDateTime.now().isBefore(reservations.get(i).getDatetime().plusMinutes(15)))
				manager.setTableAvailability(reservations.get(i).getTableNumber(), false);
		}
	}
}