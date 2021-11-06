import java.util.Date;
import java.util.List;

public class TableManager {

	private Table[] tables;

	public TableManager(){ //use txt file to get tables
		tables = new Table[10];
		for(int i = 0; i < 10; i++){
			if(i%5==0) tables[i] = new Table(i+1, 2, true);
			else if(i%5==1) tables[i] = new Table(i+1, 4, true);
			else if(i%5==2) tables[i] = new Table(i+1, 6, true);
			else if(i%5==3) tables[i] = new Table(i+1, 8, true);
			else if(i%5==4) tables[i] = new Table(i+1, 10, true);
		}


	public int checkAvailability(int pax, Date datetime, ReservationManager manager) { // return table num
		// TODO - implement TableManager.checkAvailability
		throw new UnsupportedOperationException();
	}
	public int checkAvailability(int pax) { // return table num
		// TODO - implement TableManager.checkAvailability
		throw new UnsupportedOperationException();
	}

	public boolean checkAvailability(int tableNumber) {
		return tables[tableNumber-1].getAvailable();
	}
	public boolean isValidTableNumber(int tableNumber) {
		if(tableNumber > 10 || tableNumber < 1) return false;
		else return true;
	}
}