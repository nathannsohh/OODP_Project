import java.util.ArrayList;
import java.time.*;
import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;

public class TableManager {

	private Table[] tables;
	
	public TableManager(){ //use txt file to get tables
		tables = new Table[10];
		String path = new File("").getAbsolutePath();
		String fileName = "\\tableData.txt";
		File file = new File(path + fileName);
		try {
			Scanner sc = new Scanner(file);
			int i = 0;
				while (sc.hasNextInt())
				{
					tables[i] = new Table(i+1, sc.nextInt(), true);
					i++;
				}
				sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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

	public void setReservedTablesOccupied(ReservationManager manager) { 
		for(int i = 0; i < manager.getReservationList().size(); i++){
			if(LocalDateTime.now().isAfter(manager.getReservationList().get(i).getDatetime()) && LocalDateTime.now().isBefore(manager.getReservationList().get(i).getDatetime().plusMinutes(15)))
				setTableAvailability(manager.getReservationList().get(i).getTableNumber(), false);
		}
	}
}

