import java.util.*;
import java.util.function.Supplier;
public class Staff {

	private String name;

	enum Gender{Male,Female};
	private String employeeId;
	enum JobTitle{waiter,cashier,manager};
	
	public Staff(String name, int gender, String employeeId, JobTitle jobTitle){
		this.name=name;
		this.employeeId=employeeId;


	public String getName(){
		return this.name;
	}
	
	public void setName(String s){
		this.name=s;
	}

	
	public void setEmployeeId(String s){
		this.employeeId=s;
	}
	
	public String getEmployeeId(){
		return this.employeeId;
	}
	


	public boolean assignTable(int quantity,TableManager tablemanager) {
		return tablemanager.checkAvailability(quantity);
	}

	public void createOrder(OrderManager ordermanager, Staff staff,String datetime,int OrderID) {
		ordermanager.createOrder(staff,datetime,OrderID);
	}
}