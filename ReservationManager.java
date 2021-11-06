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

	public void createReservation(LocalDateTime dateTime, int pax, Customer customer, int tableNumber) {
		reservations.add(new Reservation(dateTime, pax, customer, tableNumber));
	}
	
	public boolean isValidDate(LocalDateTime datetime){
		LocalDateTime date = datetime;
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