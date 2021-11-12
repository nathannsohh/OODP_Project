package cz2002.rrpss;

/**
 * Represents a staff of the restaurant.
 * 
 * @author Gregory Ong
 * @version 1.0
 * @since 2021-11-06
 */
public class Staff {
	/**
	 * The enumeration of possible gender of staff.
	 */
	public enum Gender {
		MALE, FEMALE
	};

	/**
	 * The enumeration of possible job titles of staff.
	 */
	public enum JobTitle {
		WAITER, CASHIER, MANAGER
	};

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
	 * Class constructor that creates a staff with attributes name, gender,
	 * employeeID and job title. The name should include both first and last name.
	 * 
	 * @param name       This staff's name.
	 * @param gender     This staff's gender.
	 * @param employeeID This staff's employeeID.
	 * @param jobTitle   This staff's job title.
	 */
	public Staff(String name, Gender gender, String employeeID, JobTitle jobTitle) {
		this.name = name;
		this.employeeID = employeeID;
		this.gender = gender;
		this.jobTitle = jobTitle;
	}

	/**
	 * Gets the first and last name of this staff.
	 * 
	 * @return This staff's name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets this staff's name. The name should include both first and last name.
	 * 
	 * @param name This staff's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the gender of this staff.
	 * 
	 * @return Gender This staff's gender.
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * Sets the gender of this staff.
	 * 
	 * @return Gender This staff's gender.
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * Gets the job title of this staff.
	 */
	public JobTitle getJobTitle() {
		return jobTitle;
	}

	/**
	 * Sets the job title of this staff.
	 */
	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * Gets the employee ID of this staff.
	 * 
	 * @return This staff's employee ID.
	 */
	public String getEmployeeID() {
		return employeeID;
	}

	/**
	 * Sets the employee ID of this staff.
	 */
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
}
