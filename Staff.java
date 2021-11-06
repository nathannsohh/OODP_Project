import java.util.*;
import java.util.function.Supplier;
public class Staff {

	private String name;
	enum Gender{Male,Female};
	enum JobTitle{waiter,cashier,manager};
	
	public Staff(String name, int gender, JobTitle jobTitle){
		this.name=name;
	}

	public String getName(){
		return this.name;
	}
	
	public void setName(String s){
		this.name=s;
	}

	
}