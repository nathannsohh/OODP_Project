import java.util.ArrayList;
import java.time.*;

public class TableManager {

	private Table[] tables;
	
	public TableManager(){ //use txt file to get tables
		tables = new Table[10];
		for(int i = 0; i < 10; i++){
			if(i==0||i==1) tables[i] = new Table(i+1, 2, true);
			else if(i==2||i==3) tables[i] = new Table(i+1, 4, true);
			else if(i==4||i==5) tables[i] = new Table(i+1, 6, true);
			else if(i==6||i==7) tables[i] = new Table(i+1, 8, true);
			else if(i==8||i==9) tables[i] = new Table(i+1, 10, true);
		}
	}

	public Table[] getTables(){
		return tables;
	}


	public int checkFutureAvailability(int pax, LocalDateTime datetime, ReservationManager manager) { 
		ArrayList<Reservation> reservationList = manager.getReservationList();
		ArrayList<Integer> tableNumList = new ArrayList<Integer>();
		for(int i = 0; i < reservationList.size(); i++){
			if(reservationList.get(i).getDatetime() == datetime) {
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
	public int checkCurrentAvailability(int pax) { 
		for(int i = 0; i < 10; i++){
			if(tables[i].getCapacity() >= pax && tables[i].getAvailable() == true) return tables[i].getTableNumber();
		}
		return -1;
	}

	public boolean isValidTableNumber(int tableNumber) {
		if(tableNumber > 10 || tableNumber < 1) return false;
		else return true;
	}

	public void setTableAvailability(int tableNumber, boolean avail){
		tables[tableNumber-1].setAvailable(avail);
	}
}