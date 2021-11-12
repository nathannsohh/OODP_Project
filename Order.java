import java.util.*;
import java.time.*;

/**
 * Represents an order tagged to a specific table. Each table can only have a
 * maximum of 1 order at any point.
 * 
 * @author Gregory Ong
 * @version 1.0
 * @since 2021-11-06
 */
public class Order {
	/**
	 * The staff that created this order.
	 */
	private Staff staff;
	/**
	 * The list of order items in this order.
	 */
	private ArrayList<OrderItem> orderItems;
	/**
	 * The date and time that this order is created.
	 */
	private LocalDateTime datetime;
	/**
	 * The subtotal of all the order items in this order.
	 */
	private float subtotal;
	/**
	 * The price of this order before Government Service Tax (GST).
	 */
	private float priceBefGST;
	/**
	 * The tablenumber of this order.
	 */
	private int tableNumber;
	/**
	 * The customer that placed this order.
	 */
	private Customer customer;

	/**
	 * Class constructor that creates an order with attributes staff, datetime,
	 * table number and customer.
	 * 
	 * @param staff       The staff that created this order.
	 * @param datetime    The date and time that this order is created.
	 * @param tableNumber The tablenumber of this order.
	 * @param customer    The customer that placed this order.
	 */
	public Order(Staff staff, LocalDateTime datetime, int tableNumber, Customer customer) {
		orderItems = new ArrayList<OrderItem>();
		this.staff = staff;
		this.datetime = datetime;
		this.tableNumber = tableNumber;
		this.customer = customer;
	}

	/**
	 * Gets the date and time that this order is created.
	 * 
	 * @return The date and time of this order.
	 */
	public LocalDateTime getDatetime() {
		return this.datetime;
	}

	/**
	 * Gets the price of this order before Government Service Tax (GST).
	 * 
	 * @return The price of this order before Government Service Tax (GST).
	 */
	public float getPriceBefGST() {
		return priceBefGST;
	}

	/**
	 * Gets the table number of this order.
	 * 
	 * @return The table number of this order.
	 */
	public int getTableNumber() {
		return tableNumber;
	}

	/**
	 * Gets this list of order items in this order.
	 * 
	 * @return The list of order items in this order.
	 */
	public ArrayList<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	/**
	 * Adds items into the list of order items.
	 * 
	 * @param item     The order item that is added to the order.
	 * @param quantity The quantity of this order item that is added to the order.
	 */
	public void addItem(MenuItem item, int quantity) {
		OrderItem orderitem = new OrderItem(item, quantity);
		// to check if the item is found in the list of order item.
		boolean found = false;
		// If list of order items is empty, just add the order item to the list.
		if (orderItems.size() == 0)
			orderItems.add(orderitem);
		// else search the list and update the corresponding quantity of order item if
		// there
		// is currently the same order item in the list of order items. If not, just add
		// it to the list of order items.
		else {
			// Go through the list of order items and try to find if there is the same order
			// item ordered.
			for (int i = 0; i < orderItems.size(); i++) {
				// Search by name of order item.
				if (orderItems.get(i).getMenuItem().getName().equals(item.getName())) {
					// If there is the same order item ordered, update the quantity of order item.
					orderItems.get(i).setQuantity(orderItems.get(i).getQuantity() + quantity);
					found = true;
					break;
				}
			}
			if (found == false) {
				// If item is not found in the list of order items, just add it to the list.
				orderItems.add(orderitem);
			}
		}
	}

	/**
	 * Removes items from the list of order items.
	 * 
	 * @param item     The order item that is removed from the order.
	 * @param quantity The quantity of this order item that is removed from the
	 *                 order.
	 */
	public void removeItem(MenuItem item, int quantity) {
		// to check if the item is found in the list of order item.
		boolean found = false;
		// If list of order items is empty
		if (orderItems.size() == 0)
			System.out.println("There are no items in this order yet! Nothing to remove.");
		else {
			// Go through the list of order items and find the corresponding order item
			// ordered.
			for (int i = 0; i < orderItems.size(); i++) {
				// Search by name of order item.
				if (orderItems.get(i).getMenuItem().getName().equals(item.getName())) {
					// If current quantity of order item in the order is less than the quantity that
					// is pending removal from the order.
					if ((orderItems.get(i).getQuantity() - quantity) < 0) {
						System.out.println("Insufficient quantity to remove!");
						found = true;
						break;
					} else {
						// If quantity of order item after removal is zero, remove the order item from
						// the list of order items.
						if (orderItems.get(i).getQuantity() - quantity == 0) {
							orderItems.remove(i);
							System.out.println(quantity + " " + item.getName() + " removed!");
							found = true;
							break;
						} else {
							// Update the quantity of order item corespondingly after removal from order.
							orderItems.get(i).setQuantity(orderItems.get(i).getQuantity() - quantity);
							System.out.println(quantity + " " + item.getName() + " removed!");
							found = true;
							break;
						}
					}
				}
			}
			if (found == false) {
				// If item is not found in the list of order items.
				System.out.println("No such item in the order!");
			}
		}
	}

	/**
	 * Displays the order items and their corresponding quantity.
	 */
	public void viewOrder() {
		// If the list of order items is empty
		if (orderItems.size() == 0)
			System.out.println("There are no items in this order yet!");
		else {
			// Else go through the list of order items and display the order items and their
			// corresponding quantity.
			System.out.println("Quantity         ID      Items");
			for (int j = 0; j < orderItems.size(); j++) {
				System.out.printf("%-16d %-8s%-30s \n", orderItems.get(j).getQuantity(),
						orderItems.get(j).getMenuItem().getId(), orderItems.get(j).getMenuItem().getName());
			}
		}
	}

	/**
	 * Prints the order invoice(receipt).
	 */
	public void printOrderInvoice() {
		float discount = 0;
		float price = 0;
		subtotal = 0;
		priceBefGST = 0;
		System.out.println("                     MacDonalds                        ");
		System.out.println("*******************************************************");
		System.out.println("Table Number: " + tableNumber);
		LocalDate date = datetime.toLocalDate();
		System.out.println(date);
		System.out.println("Served by: " + staff.getName());
		System.out.println("_______________________________________________________");
		// Check if list of order item is empty
		if (orderItems.size() == 0)
			System.out.println("No items ordered.");
		else {
			// else print out the quantity, the item name and price of the coressponding
			// order items in the list of order item.
			System.out.println("Quantity      Item                                Price");
			// Go through the list of order items
			for (int i = 0; i < orderItems.size(); i++) {
				System.out.printf("%-13d %-34s %6.2f\n", orderItems.get(i).getQuantity(),
						orderItems.get(i).getMenuItem().getName(),
						orderItems.get(i).getMenuItem().getPrice() * orderItems.get(i).getQuantity());
				subtotal += orderItems.get(i).getMenuItem().getPrice() * orderItems.get(i).getQuantity();
			}
			System.out.println("_______________________________________________________");
			System.out.printf("                                   Subtotal: %10.2f\n", subtotal);
			// Members are entitled to a 10% discount off their subtotal.
			if (customer.getMember() == true) {
				discount = (float) (subtotal * 0.10);
				System.out.printf("                                   Discount: %10.2f\n", discount);
			} else {
				System.out.println("                                Discount: 0.00");
			}
			// The price of this order before Government Service Tax (GST).
			priceBefGST = (float) ((subtotal - discount) * 1.10);
			System.out.printf("                         10%% Service Charge: %10.2f\n",
					(float) ((subtotal - discount) * 0.10));
			System.out.printf("                              7%% GST Charge: %10.2f\n", priceBefGST * 0.07);
			// final price of the order.
			price = (float) (priceBefGST * 1.07);
			System.out.printf("                                      Total: %10.2f\n", price);
		}
		System.out.println();
		System.out.println("*******************************************************");
		System.out.println("             Thank You For Dining With Us!             ");
		System.out.println("*******************************************************");
	}
}