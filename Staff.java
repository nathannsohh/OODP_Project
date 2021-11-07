import java.util.*;
import java.util.function.Supplier;
/**
 * The enumeration of possible gender of staff.
 */
enum Gender{MALE, FEMALE};
/**
 * The enumeration of possible job titles of staff.
 */
enum JobTitle{WAITER, CASHIER, MANAGER};
/**
 * Represents a staff of the restaurant.
 * @author Gregory Ong
 * @version 1.0
 * @since 2021-11-06
 */
public class Staff {
	/**
	 * The first and last name of this staff.
	 */
	private String name;
	/**
	 * The gender of this staff.
	 */
	private Gender gender;
	/**
	 * The job title of this staff.
	 */
	private JobTitle jobTitle;
	/**
	 * The employeeID of this staff.
	 */
	private String employeeID;
	
	/**
	 * Class constructor that creates a staff with attributes name, gender, employeeID and job title.
	 * The name should include both first and last name.
	 * @param name This staff's name.
	 * @param gender This staff's gender.
	 * @param employeeID This staff's employeeID.
	 * @param jobTitle This staff's job title.
	 */
	public Staff(String name, Gender gender, String employeeID, JobTitle jobTitle){
		this.name=name;
		this.employeeID=employeeID;
		this.gender= gender;
		this.jobTitle=jobTitle;
	}

	
	/** 
	 * Gets the gender of this staff.
	 * @return Gender This staff's gender.
	 */
	public Gender getGender(){
		return gender;
	}

	
	/** 
	 * Gets the job title of this staff.
	 * @return This staff's job title.
	 */
	public JobTitle getJobTitle(){
		return jobTitle;
	}

	
	/** 
	 * Gets the employee ID of this staff.
	 * @return This staff's employee ID.
	 */
	public String getEmployeeID(){
		return employeeID;
	}
	
	/** 
	 * Gets the first and last name of this staff.
	 * @return This staff's name. 
	 */
	public String getName(){
		return this.name;
	}
	
	
	/** 
	 * Sets this staff's name. The name should include both first and last name.
	 * @param name This staff's name.
	 */
	public void setName(String name){
		this.name=name;
	}
}
