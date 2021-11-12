/**
 * Represents a customer of the restaurant.
 * 
 * @author Gregory Ong
 * @version 1.0
 * @since 2021-11-06
 */
public class Customer {
	/**
	 * The first and last name of this customer.
	 */
	private String name;
	/**
	 * The contact number of this customer.
	 */
	private String contact;
	/**
	 * Shows whether this customer is a member.
	 */
	private boolean member;

	/**
	 * Class constructor that creates a customer with attributes name, contact and
	 * whether the customer is a member or not. The name should include both first
	 * and last name.
	 * 
	 * @param name    This customer's name.
	 * @param contact This customer's contact.
	 * @param member  Whether the customer is a member or not.
	 */
	public Customer(String name, String contact, boolean member) {
		this.name = name;
		this.contact = contact;
		this.member = member;
	}

	/**
	 * Gets the first and last name of this Student.
	 * 
	 * @return This customer's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets this customer's name. The name should include both first and last name.
	 * 
	 * @param name This customer's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets this customer's contact number.
	 * 
	 * @return This customer's contact number.
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * Sets this customer's contact number.
	 * 
	 * @param contact This customer's contact number.
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * Checks whether this customer is a member.
	 * 
	 * @return Whether this customer is a member. true for member, false for not
	 *         member.
	 */
	public boolean getMember() {
		return member;
	}

	/**
	 * Sets membership.
	 * 
	 * @param member Whether this a member. true for member, false for not member.
	 */
	public void setMember(boolean member) {
		this.member = member;
	}
}