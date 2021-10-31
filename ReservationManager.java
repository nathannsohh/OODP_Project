
import java.util.ArrayList;
import java.time.*;
import java.time.format.*;
import java.util.Date;
import java.util.List;

public class ReservationManager {

	private ArrayList<Reservation> reservations;


	public ReservationManager(){
		reservations = new ArrayList<Reservation>();
	}

	public void createReservation(String dateTime, int pax, Customer customer, int tableNumber) {
		reservations.add(new Reservation(dateTime, pax, customer, tableNumber));
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

	public void removeReservation(Customer customer, LocalDateTime dateTime) {
		int i = 0;
		while(i != reservations.size()){
			if(reservations.get(i).getCustomer() == customer && reservations.get(i).getDatetime() == dateTime){
				reservations.remove(i);
				System.out.println("Reservation has been removed.");
				break;
			}
		}
		if(i == reservations.size()){
			System.out.println("Removal failed: No such reservation found!");
		}
	}

	public boolean checkAvailability(LocalDateTime dateTime, int pax) {
		int i = 0, count = 0;
		while(i != reservations.size()){
			if(reservations.get(i).getDatetime() == dateTime && reservations.get(i).getPax() == pax) count++;
		}
		if(count == 2) return false;
		else return true;
	}


}