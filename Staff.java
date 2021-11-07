import java.util.*;
import java.util.function.Supplier;
enum Gender{MALE, FEMALE};
enum JobTitle{WAITER, CASHIER, MANAGER};
public class Staff {

	private String name;
	private Gender gender;
	private JobTitle jobTitle;
	private String employeeID;
	
	public Staff(String name, Gender gender, String employeeID, JobTitle jobTitle){
		this.name=name;
		this.employeeID=employeeID;
		this.gender= gender;
		this.jobTitle=jobTitle;
	}

	
	/** 
	 * @return Gender
	 */
	public Gender getGender(){
		return gender;
	}

	
	/** 
	 * @return JobTitle
	 */
	public JobTitle getJobTitle(){
		return jobTitle;
	}

	
	/** 
	 * @return String
	 */
	public String getEmployeeID(){
		return employeeID;
	}
	
	/** 
	 * @return String
	 */
	public String getName(){
		return this.name;
	}
	
	
	/** 
	 * @param s
	 */
	public void setName(String s){
		this.name=s;
	}

	
}